package com.daniil.bookofrecipes.data.remote

import javax.inject.Inject

class RecipeRemoteDataSource @Inject constructor(
    private val recipeService: RecipeService
) : BaseDataSource() {

    suspend fun getRecipes() = getResult { recipeService.getAllRecipes() }
}