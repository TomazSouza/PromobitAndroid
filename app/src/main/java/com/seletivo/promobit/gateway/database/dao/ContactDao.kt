package com.seletivo.promobit.gateway.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seletivo.promobit.gateway.database.entity.ContactEntity

@Dao
abstract class ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertContact(contactEntity:  MutableList<ContactEntity>)

    @Query("SELECT * FROM contactEntity")
    abstract fun getAllContacts(): LiveData<MutableList<ContactEntity>>
}