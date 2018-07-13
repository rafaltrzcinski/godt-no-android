package com.rafaltrzcinski.godtnoandroid.view

import android.support.v4.content.ContextCompat.*
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafaltrzcinski.godtnoandroid.R
import com.rafaltrzcinski.godtnoandroid.domain.model.Recipe
import com.rafaltrzcinski.godtnoandroid.utils.loadHtmlText
import com.rafaltrzcinski.godtnoandroid.utils.loadImageFromUrl
import kotlinx.android.synthetic.main.item_recipe.view.*


class RecipesListAdapter  : RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    private val items = mutableListOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipesListAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    fun loadItems(newItems: List<Recipe>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(recipe: Recipe) {
            itemView.recipeImage.loadImageFromUrl(recipe.images.first().url)
            itemView.recipeTitle.apply {
                text = recipe.title
                setToggleListener()
            }
                itemView.recipeDescription.text = recipe.description.loadHtmlText()
                itemView.recipeIngredients.apply {
                    text = recipe.getElementsString()
                    collapse()
                    setToggleListener()
                }
                itemView.toggleIngredients.setToggleListener()

            itemView.toggleIngredients.setBackgroundResource(R.drawable.ic_expand_more)
            }

        private fun View.setToggleListener() {
            this.setOnClickListener {
                itemView.recipeIngredients.toggle()
                itemView.toggleIngredients.setBackgroundResource(
                        if (itemView.recipeIngredients.isExpanded) R.drawable.ic_expand_more
                        else R.drawable.ic_expand_less
                )
            }
        }
    }
}