package com.seletivo.promobit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.seletivo.promobit.R
import com.seletivo.promobit.adapter.commons.DataBoundListAdapter
import com.seletivo.promobit.databinding.RecyclerContactItemBinding
import com.seletivo.promobit.gateway.database.entity.ContactEntity
import com.seletivo.promobit.util.async.AppExecutors

class ListContactsAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors
) : DataBoundListAdapter<ContactEntity, RecyclerContactItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<ContactEntity>() {
        override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
            return oldItem.newContact == newItem.newContact
                    && oldItem.company == newItem.company && oldItem.photo == newItem.photo
        }
    }
) {

    override fun createBinding(parent: ViewGroup): RecyclerContactItemBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_contact_item,
            parent,
            false,
            dataBindingComponent
        )
    }

    override fun bind(binding: RecyclerContactItemBinding, item: ContactEntity) {
        binding.appCompatButton.setOnClickListener {
            val optionsMenu = PopupMenu(binding.root.context, binding.appCompatButton)
            optionsMenu.inflate(R.menu.option_menu)
            optionsMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_item_save -> {
                        Toast.makeText(binding.root.context, "Salvar", Toast.LENGTH_SHORT).show()
                        return@setOnMenuItemClickListener true
                    }
                    R.id.menu_item_delete -> {
                        Toast.makeText(binding.root.context, "Delete", Toast.LENGTH_SHORT).show()
                        return@setOnMenuItemClickListener true
                    }
                }
                return@setOnMenuItemClickListener false
            }
            optionsMenu.show()
        }
        binding.contactList = item
    }
}