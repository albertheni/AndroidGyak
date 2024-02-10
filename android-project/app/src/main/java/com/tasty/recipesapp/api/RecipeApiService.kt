package com.tasty.recipesapp.api

import com.tasty.recipesapp.repository.recipe.model.RecipeDTO
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: 9d17b74e4dmsh1602864be361acdp1809d8jsnda1ca00c46be",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("tags") tags: String? = null
    ): RecipesDTO


    @GET("recipes/list-similarities")
    @Headers(
        "X-RapidAPI-Key: 9d17b74e4dmsh1602864be361acdp1809d8jsnda1ca00c46be",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getSimilarities(
        @Query("recipe_id") recipe_id: String
    ): RecipesDTO

}