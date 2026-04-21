package com.mmfsin.ved.di

import com.mmfsin.ved.data.repository.DilemmasRepository
import com.mmfsin.ved.domain.interfaces.IDilemmasRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideDilemmasRepository(): IDilemmasRepository = DilemmasRepository()
}