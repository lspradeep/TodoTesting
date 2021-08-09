package com.testing.android.todo.di

import com.testing.android.todo.data.localdb.TodoDao
import com.testing.android.todo.repo.TodoRepo
import com.testing.android.todo.repo.TodoRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesTodoRepo(todoDao: TodoDao): TodoRepo {
        return TodoRepoImpl(todoDao)
    }
}