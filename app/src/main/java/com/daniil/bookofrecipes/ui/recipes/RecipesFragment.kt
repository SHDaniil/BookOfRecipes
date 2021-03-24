package com.daniil.bookofrecipes.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniil.bookofrecipes.R
import com.daniil.bookofrecipes.databinding.FragmentRecipesBinding
import com.daniil.bookofrecipes.utils.Resource
import com.daniil.bookofrecipes.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class RecipesFragment : Fragment(), RecipesAdapter.RecipeItemListener {

    private var binding: FragmentRecipesBinding by autoCleared()
    private val viewModel: RecipesViewModel by viewModels()
    private lateinit var adapter: RecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = RecipesAdapter(this)
        binding.recipeRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recipeRecycleView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedRecipe(recipeId: String) {
        val bundle = bundleOf("id" to recipeId)
        findNavController().navigate(
            R.id.action_recipesFragment_to_recipeDetailFragment,
            bundle
        )
    }

}