package id.my.mufidz.apicalling.base

import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView
import id.my.mufidz.apicalling.model.User

abstract class BaseAdapter<Entity : Parcelable> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: MutableList<Entity> = mutableListOf()

    override fun getItemCount(): Int = list.size

    fun setData(item: MutableList<Entity>) {
        list = item
        notifyDataSetChanged()
    }

    fun clearData() {
        list = mutableListOf()
        notifyDataSetChanged()
    }
}