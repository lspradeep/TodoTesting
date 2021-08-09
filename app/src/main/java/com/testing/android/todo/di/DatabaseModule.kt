package com.testing.android.todo.di

import android.content.Context
import androidx.room.Room
import com.testing.android.todo.data.localdb.TodoDB
import com.testing.android.todo.data.localdb.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TodoDB {
        return Room.databaseBuilder(
            appContext,
            TodoDB::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesTodoDao(database: TodoDB): TodoDao {
        return database.todoDao()
    }
}