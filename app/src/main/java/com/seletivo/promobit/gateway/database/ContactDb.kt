package com.seletivo.promobit.gateway.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seletivo.promobit.gateway.database.dao.ContactDao
import com.seletivo.promobit.gateway.database.entity.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDb : RoomDatabase() {
    abstract fun getContactDao() : ContactDao
}