package com.example.simplecalculator.di

import com.example.simplecalculator.domain.CalculationService
import com.example.simplecalculator.domain.CalculationServiceContract
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
    fun provideCalculationService(): CalculationServiceContract {
        return CalculationService()
    }

}