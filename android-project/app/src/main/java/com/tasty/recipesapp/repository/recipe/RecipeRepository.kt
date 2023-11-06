package com.tasty.recipesapp.repository.recipe

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
import com.tasty.recipesapp.repository.recipe.model.toModelList
import java.io.IOException

object RecipeRepository {
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

        return recipesResponse.results.toModelList()
    }
}