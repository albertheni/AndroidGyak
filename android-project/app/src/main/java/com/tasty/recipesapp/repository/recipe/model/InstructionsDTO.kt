package com.tasty.recipesapp.repository.recipe.model

data class InstructionsDTO(
    var position: Int? = null,
    var display_text: String? = null
)

fun InstructionsDTO.toModel() = InstructionsModel(
        position = this.position,
        displayText = this.display_text
)

fun List<InstructionsDTO>.toModelList() = this.map { it.toModel() }