package com.shaadi.assignment.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shaadi.assignment.data.local.db.typeconverters.InvitationStatus
import org.jetbrains.annotations.NotNull

@Entity(tableName = "inbox_user")
data class InboxUser(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Long,

    @ColumnInfo(name = "name")
    @NotNull
    val name: String,

    @ColumnInfo(name = "age")
    @NotNull
    val age: String,

    @ColumnInfo(name = "imgUrl")
    @NotNull
    val imgUrl: String,

    @ColumnInfo(name = "registeredOn")
    @NotNull
    val registeredOn: String,

    @ColumnInfo(name = "location")
    @NotNull
    val location: String,

    @ColumnInfo(name = "invitation_status")
    @NotNull
    val invitation_status: InvitationStatus

)