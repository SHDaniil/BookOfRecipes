package com.daniil.bookofrecipes.data.repository

import androidx.lifecycle.LiveData
import com.daniil.bookofrecipes.data.entities.Recipe
import com.daniil.bookofrecipes.data.local.RecipeDao
import com.daniil.bookofrecipes.data.remote.RecipeRemoteDataSource
import com.daniil.bookofrecipes.utils.performGetOperation
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val remoteDataSource: RecipeRemoteDataSource,
    private val localDataSource: RecipeDao
) {

    fun getRecipe(id: String): LiveData<Recipe> {
        return localDataSource.getRecipe(id)
    }

    fun getRecipes() = performGetOperation(
        databaseQuery = { localDataSource.getAllRecipes() },
        networkCall = { remoteDataSource.getRecipes() },
        saveCallResult = { localDataSource.insertAll(it.recipes) }
    )
}