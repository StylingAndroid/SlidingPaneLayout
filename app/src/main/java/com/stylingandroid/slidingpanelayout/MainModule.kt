package com.stylingandroid.slidingpanelayout

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun providesListItems(): List<String> = (1..LIST_SIZE).map { "Item $it" }

    const val LIST_SIZE = 10
}
