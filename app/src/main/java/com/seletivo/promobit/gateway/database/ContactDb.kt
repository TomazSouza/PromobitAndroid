package com.seletivo.promobit.gateway.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seletivo.promobit.gateway.database.dao.ContactDao
import com.seletivo.promobit.gateway.database.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class ContactDb : RoomDatabase() {
    abstract fun contactDao() : ContactDao
}