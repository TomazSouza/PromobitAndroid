package com.seletivo.promobit.gateway.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seletivo.promobit.gateway.database.entity.Contact

@Dao
abstract class ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(contact: Contact)

    @Query("SELECT * FROM contact")
    abstract fun getStatementList(): LiveData<MutableList<Contact>>
}