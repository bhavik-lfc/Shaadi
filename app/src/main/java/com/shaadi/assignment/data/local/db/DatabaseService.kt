package com.shaadi.assignment.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shaadi.assignment.data.local.db.dao.InboxUserDao
import com.shaadi.assignment.data.local.db.entity.InboxUser
import com.shaadi.assignment.data.local.db.typeconverters.RoomConverters
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        InboxUser::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun inboxUserDao(): InboxUserDao

}