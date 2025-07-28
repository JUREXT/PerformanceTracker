package com.programming.gradledagger.di

import com.programming.gradledagger.di.annotation.TestString
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestModule {

    @Provides
    @Singleton
    @TestString
    fun provideTestString(): String = "Dagger Test String"
}