package com.rafaltrzcinski.godtnoandroid.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.rafaltrzcinski.godtnoandroid.R
import com.rafaltrzcinski.godtnoandroid.domain.model.Recipe
import com.rafaltrzcinski.godtnoandroid.viewmodel.RecipeListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val recipesAdapter: RecipesListAdapter by lazy { RecipesListAdapter() }

    private val recipeListViewModel: RecipeListViewModel by lazy {
        ViewModelProviders.of(this).get(RecipeListViewModel::class.java)
    }

    private val cachedRecipesObserver: Observer<List<Recipe>> =
            Observer {
                it?.let {
                    if (it.isNotEmpty()) recipesAdapter.loadItems(it)
                    else recipeListViewModel.getRemoteRecipes()
                    refreshLayout.isRefreshing = false
                }
            }

    private val remoteRecipesObserver: Observer<List<Recipe>> =
            Observer {
                it?.let {
                    recipeListViewModel.cacheRecipes(it)
                    refreshLayout.isRefreshing = false
                } ?: showConnectionError()
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshLayout.setOnRefreshListener {
            val items = recipeListViewModel.getCachedRecipes()
            if (items.isNotEmpty()) recipesAdapter.loadItems(items)
            else recipeListViewModel.getRemoteRecipes()
            refreshLayout.isRefreshing = false
        }

        initRecyclerView()
        observeOnCachedRecipes()
        observeOnRemoteRecipes()
        recipeListViewModel.cacheRecipes()
    }

    private fun initRecyclerView() {
        recipesList.apply {
            adapter = recipesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

        private fun observeOnCachedRecipes() {
        recipeListViewModel.getRecipesFromCache().observe(this, cachedRecipesObserver)
    }

    private fun observeOnRemoteRecipes() {
        recipeListViewModel.getRemoteRecipes().observe(this, remoteRecipesObserver)
    }

    private fun showConnectionError() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.connection_error))
            setMessage(getString(R.string.connection_error_instruction))
            setPositiveButton(getString(android.R.string.ok)) { _, _ -> }
        }.show()
    }
}
