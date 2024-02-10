package com.tasty.recipesapp.ui.recipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App

class RecipeDetailViewModel(var repository : RecipeRepository) : ViewModel() {
    //private val repository = RecipeRepository

    //private val repository = (App.getAppContext() as App).repository

    var recipe: MutableLiveData<RecipeModel> = MutableLiveData()


    fun fetchRecipeData(recipeId: Int) {
        var recipe = repository.getRecipe(recipeId)

        if (recipe == null) {
            recipe = repository.getMyRecipe(recipeId)
        }
        this.recipe.value = recipe
    }
}
