package com.tasty.recipesapp.repository.recipe

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.api.RecipeApiClient
import com.tasty.recipesapp.database.RecipeDao
import com.tasty.recipesapp.database.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.*
import org.json.JSONObject
import java.io.IOException

class RecipeRepository (private val recipeDao: RecipeDao) {

    private val TAG: String? = RecipeRepository::class.java.canonicalName
    private var recipesList: List<RecipeModel> = emptyList()
    private var myRecipesList: ArrayList<RecipeModel> = ArrayList()
    private var similarList: List<RecipeModel> = emptyList()
    fun getRecipes(context: Context):List<RecipeModel>{
        lateinit var jsonString: String
        try {
            jsonString=
                context.assets.open("recipes_all.json").bufferedReader().use {
                    it.readText()
                }
        }catch (ioException: IOException){
            Log.e(TAG,"Error occured while reading JSON file: $ioException")
        }
        val recipesResponse:RecipesDTO =
            Gson().fromJson(jsonString, object : TypeToken<RecipesDTO>(){}.type)

        recipesList = recipesResponse.results.toModelList()

        return recipesList
    }

    fun getRecipe(recipeId:Int): RecipeModel? {
        return  recipesList.find { it.id == recipeId }
    }

    fun insertRecipe(recipeModel: RecipeModel) = myRecipesList.add(recipeModel)
    fun deleteRecipe(recipeModel: RecipeModel) = myRecipesList.remove(recipeModel)
    fun getMyRecipes() = myRecipesList


    suspend fun insertRecipe(recipe: RecipeEntity) {
        val result = recipeDao.insertRecipe(recipe)
        Log.d(TAG, "Inserted result: $result")
    }
    suspend fun deleteRecipe(recipe: RecipeEntity) {
        val result = recipeDao.deleteRecipe(recipe)
        Log.d(TAG, "Deleted result: $result")
    }

    suspend fun getAllRecipes(): List<RecipeModel> {
        return recipeDao.getAllRecipes().map{
            val jsonObject = JSONObject(it.json)
            jsonObject.apply{put("id",it.internalId)}
            Gson().fromJson(jsonObject.toString(),RecipeDTO::class.java).toModel()

        }
    }

    private val recipeApiClient = RecipeApiClient()

    fun getMyRecipe(recipeId: Int):RecipeModel? = myRecipesList.find { it.id == recipeId }

    suspend fun getRecipesFromApi(
        from: String,
        size: String,
        tags: String? = null,
    ): List<RecipeModel> {
        recipesList =  recipeApiClient.recipeService.getRecipes(from, size, tags).results.toModelList();
        return recipesList

    }

    suspend fun getSimilarRecipes(
        recipe_id: String
    ): List<RecipeModel> {
        similarList =  recipeApiClient.recipeService.getSimilarities(recipe_id).results.toModelList();
        return similarList
    }


}