package com.daniil.bookofrecipes.ui.recipedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.daniil.bookofrecipes.data.entities.Recipe
import com.daniil.bookofrecipes.databinding.FragmentRecipeDetailBinding
import com.daniil.bookofrecipes.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private var binding: FragmentRecipeDetailBinding by autoCleared()
    private val viewModel: RecipeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeId = arguments?.getString("id")
        viewModel.start(recipeId.toString())
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.recipe.observe(viewLifecycleOwner, Observer {
            bindRecipe(it)
            binding.progressBar.visibility = View.GONE
            binding.recipeCl.visibility = View.VISIBLE
        })
    }

    private fun bindRecipe(recipe: Recipe) {
        binding.recipeName.text = recipe.name
        binding.recipeDescription.text = recipe.description
        binding.recipeInstructions.text = recipe.instructions
        binding.recipeDifficulty.text = recipe.difficulty.toString()
        Glide.with(binding.root)
            .load(recipe.images[0])
            .transform(CircleCrop())
            .into(binding.recipeImage)
    }
}