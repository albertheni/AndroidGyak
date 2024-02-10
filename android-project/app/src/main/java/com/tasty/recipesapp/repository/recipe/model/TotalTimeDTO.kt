package com.tasty.recipesapp.repository.recipe.model

data class TotalTimeDTO(
    val tier: String? = null,
    val displayTier: String? = null
)

fun TotalTimeDTO.toModel() = TotalTimeModel(
    tier = this.tier ?: "",
    displayTier = this.displayTier ?: ""
)

fun List<TotalTimeDTO>.toModelList() = this.map { it.toModel() }
