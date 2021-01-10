package com.shaadi.assignment.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shaadi.assignment.data.local.db.entity.InboxUser
import io.reactivex.Single

@Dao
interface InboxUserDao {

    @Query("SELECT * FROM inbox_user")
    fun getAll(): LiveData<List<InboxUser>>

    @Insert
    fun insert(entity: InboxUser): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: List<InboxUser>): Single<List<Long>>

    @Delete
    fun delete(entity: InboxUser): Single<Int>

    @Query("SELECT count(*) from inbox_user")
    fun count(): Single<Int>
}