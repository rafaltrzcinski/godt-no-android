package com.rafaltrzcinski.godtnoandroid.domain.model


data class Recipe(
        val title: String,
        val description: String,
        val images: List<Image>,
        val ingredients: List<Ingredient>) {

    fun getElementsString(): String {
        val resultString = StringBuilder()
        ingredients.forEach { resultString.append(it.elements.joinToString(separator = "")) }
        return "$resultString"
    }
}