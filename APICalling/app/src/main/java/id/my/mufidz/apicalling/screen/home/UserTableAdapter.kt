package id.my.mufidz.apicalling.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.my.mufidz.apicalling.base.BaseAdapter
import id.my.mufidz.apicalling.base.BaseViewHolder
import id.my.mufidz.apicalling.databinding.ItemTableUserBinding
import id.my.mufidz.apicalling.model.User

class UserTableAdapter : BaseAdapter<User>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemTableUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ViewHolder).bind(list[position])
    }

    class ViewHolder(private val binding: ItemTableUserBinding) : BaseViewHolder<User>(binding.root) {
        override fun bind(item: User) {
            val (_, name, email, gender) = item
            binding.apply {
                tvEmail.text = email
                tvName.text = name
                tvGender.text = gender
            }
        }
    }
}