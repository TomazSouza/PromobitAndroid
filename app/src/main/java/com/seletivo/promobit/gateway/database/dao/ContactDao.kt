package com.seletivo.promobit.gateway.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seletivo.promobit.enums.OrderBy
import com.seletivo.promobit.gateway.database.entity.ContactEntity

@Dao
abstract class ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertContact(contactEntity:  MutableList<ContactEntity>)

//    @Query("SELECT * FROM contactEntity ORDER BY name ASC")
//    abstract fun getAllContactsOrderByName(): LiveData<MutableList<ContactEntity>>
//
//    @Query("SELECT * FROM contactEntity ORDER BY company ASC")
//    abstract fun getAllContactsOrderByCompany(): LiveData<MutableList<ContactEntity>>

    @Query("SELECT * FROM contactEntity ORDER BY company ASC")
    abstract fun getAllContactsOrderByCompanyAsc(): LiveData<MutableList<ContactEntity>>

    @Query("SELECT * FROM contactEntity ORDER BY company DESC")
    abstract fun getAllContactsOrderByCompanyDesc(): LiveData<MutableList<ContactEntity>>

    @Query("SELECT * FROM contactEntity ORDER BY name ASC")
    abstract fun getAllContactsOrderByNameAsc(): LiveData<MutableList<ContactEntity>>

    @Query("SELECT * FROM contactEntity ORDER BY name DESC")
    abstract fun getAllContactsOrderByNameDesc(): LiveData<MutableList<ContactEntity>>

    fun getOrderWith(orderBy: OrderBy): LiveData<MutableList<ContactEntity>> {
        return when (orderBy) {
            OrderBy.COMPANY_ASC -> {
                getAllContactsOrderByCompanyAsc()
            }
            OrderBy.COMPANY_DESC -> {
                getAllContactsOrderByCompanyDesc()
            }
            OrderBy.NAME_DESC -> {
                getAllContactsOrderByNameDesc()
            }
            else -> {
                getAllContactsOrderByNameAsc()
            }
        }
    }

}