package com.seletivo.promobit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
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

        binding.contactList = item
    }
}