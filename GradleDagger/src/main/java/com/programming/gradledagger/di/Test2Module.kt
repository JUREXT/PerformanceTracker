package com.programming.gradledagger.di

import com.programming.gradledagger.ANumber
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object Test2Module {

    @Provides
    @Singleton
    fun providesANumber(): ANumber = ANumber(num = 200)
}