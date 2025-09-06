// 2  AppModule.kt
package com.example.clockV2.di

import com.example.clockV2.data.TimeDateManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTimeDateManager(): TimeDateManager {
        return TimeDateManager()
    }
}
// 2  AppModule.kt
