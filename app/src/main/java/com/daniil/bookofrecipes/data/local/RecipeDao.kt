package com.daniil.bookofrecipes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daniil.bookofrecipes.data.entities.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes_table")
    fun getAllRecipes() : LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes_table WHERE uuid = :id")
    fun getRecipe(id: String): LiveData<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes: List<Recipe>)
}