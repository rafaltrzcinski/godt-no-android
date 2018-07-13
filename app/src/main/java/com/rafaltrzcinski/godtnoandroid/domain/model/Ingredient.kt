package com.rafaltrzcinski.godtnoandroid.domain.model


data class Ingredient(
        val id: String?,
        val name: String,
        val elements: List<Element>)

data class Element(
        val name: String,
        val symbol: String?,
        val amount: Float? = 0f,
        val unitName: String?) {

    override fun toString() =
            if (amount == null || symbol.isNullOrBlank()) "$name $unitName\n"
            else "$name $amount $symbol\n"
}