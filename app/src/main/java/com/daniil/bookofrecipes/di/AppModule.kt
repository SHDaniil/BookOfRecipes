package com.daniil.bookofrecipes.di

import android.content.Context
import com.daniil.bookofrecipes.data.local.AppDatabase
import com.daniil.bookofrecipes.data.local.RecipeDao
import com.daniil.bookofrecipes.data.remote.RecipeRemoteDataSource
import com.daniil.bookofrecipes.data.remote.RecipeService
import com.daniil.bookofrecipes.data.repository.RecipeRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://test.kode-t.ru/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): RecipeService = retrofit.create(RecipeService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(recipeService: RecipeService) = RecipeRemoteDataSource(recipeService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.recipeDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RecipeRemoteDataSource,
                          localDataSource: RecipeDao) =
        RecipeRepository(remoteDataSource, localDataSource)
}