package com.tasty.recipesapp.ui.recipe.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeListViewModel(var repository : RecipeRepository) : ViewModel() {
    //private val repository = RecipeRepository
   // private val repository = (App.getAppContext() as App).repository

    var recipesList: MutableLiveData<List<RecipeModel>> = MutableLiveData()
    var similarList: MutableLiveData<List<RecipeModel>> = MutableLiveData()

    fun fetchRecipesData(context: Context) {
        val recipes = repository.getRecipes(context)
        recipesList.value = recipes
    }

    fun getSimilarRecipesFromApi(recipeId: String) {
        viewModelScope.launch {
            val recipes = withContext(Dispatchers.IO) {
                repository.getSimilarRecipes(recipeId)
            }
            similarList.value = recipes

            Log.d("RECIPE_API", recipes.toString())
        }
    }


    fun getAllRecipesFromApi() {
        viewModelScope.launch {
//            val recipes = RepositoryProvider.recipeRepository.getRecipesFromApi("0", "15")
//            recipes.forEach {
//                Log.d("RECIPE_API", it.toString())
//            }

            val recipes = withContext(Dispatchers.IO) {
                repository.getRecipesFromApi("0", "10")
            }

            recipesList.value = recipes
        }
    }
}