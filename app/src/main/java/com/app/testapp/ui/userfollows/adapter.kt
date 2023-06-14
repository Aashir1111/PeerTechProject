package com.app.testapp.ui.userfollows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.app.testapp.R
import com.app.testapp.common.loadImageFromUrl
import com.app.testapp.model.remote.UserModel


class FollowerAdapter: RecyclerView.Adapter<FollowerAdapter.MyViewHolder>() {
    var list = ArrayList<UserModel>()
    lateinit var listener: UserFollowsInterface

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.image)
        var pName: TextView = view.findViewById(R.id.name)

    }


    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.follow_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder, position: Int
    ) {


        var item = list[position]
        item.avatar_url?.let {
            holder.image.loadImageFromUrl(it)
        }
        holder.pName.text = item.login


        holder.itemView.setOnClickListener {
            listener.onUserClick(item)
        }



    }

    override fun getItemCount(): Int {
        return list.count()
    }




    fun setItem(listData: ArrayList<UserModel>){
        list.clear()
        list.addAll(listData)
        notifyDataSetChanged()
    }


}
