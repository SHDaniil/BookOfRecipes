package com.daniil.bookofrecipes.ui.recipes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.daniil.bookofrecipes.data.entities.Recipe
import com.daniil.bookofrecipes.databinding.ItemRecipeBinding
import java.util.*

class RecipesAdapter(private val listener: RecipeItemListener) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    interface RecipeItemListener {
        fun onClickedRecipe(recipeId: String)
    }

    private val items = ArrayList<Recipe>()

    fun setItems(items: ArrayList<Recipe>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding: ItemRecipeBinding =
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size
}

class RecipeViewHolder(
    private val itemBinding: ItemRecipeBinding,
    private val listener: RecipesAdapter.RecipeItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var recipe: Recipe

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Recipe) {
        this.recipe = item
        itemBinding.recipeName.text = item.name
        itemBinding.recipeDescription.text = item.description
        Glide.with(itemBinding.root)
            .load(item.images[0])
            .transform(CircleCrop())
            .into(itemBinding.recipeImage)
    }

    override fun onClick(v: View?) {
        listener.onClickedRecipe(recipe.uuid)
    }
}