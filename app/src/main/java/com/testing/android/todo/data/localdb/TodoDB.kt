package com.testing.android.todo.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDB : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}