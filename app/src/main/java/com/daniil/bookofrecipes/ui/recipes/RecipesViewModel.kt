package com.daniil.bookofrecipes.ui.recipes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.daniil.bookofrecipes.data.repository.RecipeRepository

class RecipesViewModel @ViewModelInject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    val recipes = repository.getRecipes()
}