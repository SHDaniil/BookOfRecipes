package com.daniil.bookofrecipes.ui.recipedetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.daniil.bookofrecipes.data.repository.RecipeRepository


class RecipeDetailViewModel @ViewModelInject constructor(
    private val repository: RecipeRepository
): ViewModel(){

    private val _id = MutableLiveData<String>()

    private val _recipe = _id.switchMap { id ->
        repository.getRecipe(id)
    }
    val recipe = _recipe


    fun start(id: String) {
        _id.value = id
    }
}