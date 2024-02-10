package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.recipe.adapter.RecipesListAdapter
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel
import com.tasty.recipesapp.ui.recipe.viewmodel.factory.RecipeViewModelFactory

class RecipeSimilarFragment : Fragment() {

    companion object {
        private val TAG = RecipesFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recipesAdapter: RecipesListAdapter
    private lateinit var viewModel: RecipeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        initRecyclerView()



        return binding.root
    }

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factory = RecipeViewModelFactory((activity?.application as App).repository)
        viewModel = ViewModelProvider(this, factory)[RecipeListViewModel::class.java]

//        context?.let{
//            viewModel.fetchRecipesData(it)
//        }

        viewModel.getSimilarRecipesFromApi(arguments?.getInt(BUNDLE_EXTRA_SELECTED_RECIPE_ID)!!.toString())

        Log.d("Valamilyen TAG",viewModel.similarList.value.toString())
        viewModel.similarList.observe(viewLifecycleOwner){ recipes ->
            recipesAdapter.setData(recipes)
            recipesAdapter.notifyItemRangeChanged(0,recipes.lastIndex)
        }
    }

    private fun initRecyclerView(){
        recipesAdapter = RecipesListAdapter(ArrayList(),requireContext(),
            onItemClickListener = {
                    recipe-> navigateToRecipeDetails(recipe)
            })
        binding.recyclerView.adapter = recipesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

    }

    private fun navigateToRecipeDetails(recipe: RecipeModel){
        findNavController().navigate(
            R.id.action_recipesFragment_to_recipeDetailFragment,
            bundleOf(
                BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id
            )
        )

    }

}