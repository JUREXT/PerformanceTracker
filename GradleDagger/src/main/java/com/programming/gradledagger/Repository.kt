package com.programming.gradledagger

import com.programming.gradledagger.di.DaggerRepositoryComponent
import com.programming.gradledagger.di.annotation.TestString
import javax.inject.Inject

class Repository {

    @Inject
    lateinit var aNumber: ANumber

    @Inject
    @TestString
    lateinit var testString: String

    init {
        // Inject this Repository instance using Dagger
        DaggerRepositoryComponent.create().inject(this)
    }

    fun init(): String {
        return "${aNumber.num} & $testString"
    }
}