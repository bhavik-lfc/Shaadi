package com.shaadi.assignment.data.local.db.dao

import androidx.room.*
import com.shaadi.assignment.data.local.db.entity.InboxUser

@Dao
interface InboxUserDao {

    @Query("SELECT * FROM inbox_user")
    fun getAll(): List<InboxUser>

    @Insert
    fun insert(entity: InboxUser)

    @Delete
    fun delete(entity: InboxUser)

}