package com.daniil.bookofrecipes.data.remote

import com.daniil.bookofrecipes.data.entities.RecipeList
import retrofit2.Response
import retrofit2.http.GET

interface RecipeService {
    @GET("recipes.json")
    suspend fun getAllRecipes() : Response<RecipeList>
}