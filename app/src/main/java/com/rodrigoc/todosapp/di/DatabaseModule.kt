package com.rodrigoc.todosapp.di

import android.content.Context
import androidx.room.Room
import com.rodrigoc.todosapp.ToDoApplication
import com.rodrigoc.todosapp.data.TodoTaskDatabase
import com.rodrigoc.todosapp.util.Constants.DATABASE_NAME
import com.rodrigoc.todosapp.util.Constants.DATABASE_TABLE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) =
        Room.databaseBuilder(
            context,
            TodoTaskDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provides(database: TodoTaskDatabase) = database.taskDao()
}