package com.app.testapp.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import com.app.testapp.model.Error


val UI = Dispatchers.Main

inline fun CoroutineScope.ui(crossinline block: suspend () -> Unit) {
    launch(UI) { block.invoke() }
}

suspend inline fun <reified R> makeSuspendableRequest(method: () -> Deferred<Response<R>>): Result<R, Error> {
    return try {
        val result = method().await()
        val body = result.body()

        if (result.isSuccessful && body != null) {
            Success(body)
        } else if (result.isSuccessful  && body == null) {
            Success(R::class.java.newInstance())

        } else {
            val errorBody = result.errorBody()?.resolveErrorBody() ?: Error(result.code(), result.message())
            when (errorBody.code) {
                in 400..500 -> Failure(Error(errorBody.code, errorBody.message))
                else -> Failure(Error(errorBody.code, "ERROR"))
            }
        }

    } catch (e: Exception) {
        e.printStackTrace()
        Failure(Error(0, "ERROR"))
    }
}



fun ResponseBody.resolveErrorBody(): Error = try {

//    Gson().fromJson(this.charStream().readText(), Error::class.java)
    val j =  JSONObject(this.charStream().readText())
    val messge = j.get("message")

    Error(


    )
} catch (e: Exception) {
    Error()
}



sealed class Result<out T, out E>

data class Success<out Success>(val value: Success) : Result<Success, Nothing>()
data class Failure<out Failure>(val reason: Failure) : Result<Nothing, Failure>()
