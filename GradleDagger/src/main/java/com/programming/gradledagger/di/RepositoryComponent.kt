package com.programming.gradledagger.di

import com.programming.gradledagger.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TestModule::class,
    Test2Module::class
])
interface RepositoryComponent {
    fun inject(repository: Repository)
}