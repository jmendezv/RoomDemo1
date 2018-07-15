package org.escoladeltreball.roomdemo1.dummy

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
        tableName = "item",
        indices = [Index(value = ["content"], unique = true)]
)
data class ItemEntity(
        @PrimaryKey @NotNull @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "content") val content: String,
        @ColumnInfo(name = "detail") val detail: String)