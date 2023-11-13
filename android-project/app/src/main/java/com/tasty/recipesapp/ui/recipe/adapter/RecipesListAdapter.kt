package com.tasty.recipesapp.ui.recipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.databinding.RecipeListItemBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class RecipesListAdapter (
    private var recipeList : List<RecipeModel>,
    private val context: Context
):RecyclerView.Adapter<RecipesListAdapter.RecipeItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder {
        val binding = RecipeListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return RecipeItemViewHolder(binding)
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val currentRecipe = recipeList[position]

        holder.recipeTitleView.text = currentRecipe.name
        holder.recipeDescriptionView.text = currentRecipe.description
    }

    fun setData(newList: ArrayList<RecipeModel>){
        recipeList = newList
    }

    inner class RecipeItemViewHolder ( binding: RecipeListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            val recipeTitleView: TextView = binding.recipeItemTitleView
            val recipeDescriptionView: TextView = binding.recipeItemDescriptionView

    }
}