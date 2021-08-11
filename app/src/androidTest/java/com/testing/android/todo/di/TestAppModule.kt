package com.testing.android.todo.di

import android.content.Context
import androidx.room.Room
import com.testing.android.todo.data.localdb.TodoDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Named("test_todo_db")
    fun provideInMemoryDb(@ApplicationContext context: Context): TodoDB {
        return Room.inMemoryDatabaseBuilder(
            context, TodoDB::class.java
        ).allowMainThreadQueries()
            .build()
    }
}