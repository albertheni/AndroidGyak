package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.database.RecipeEntity
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.profile.ProfileViewModel
import com.tasty.recipesapp.ui.profile.ProfileViewModelFactory


class NewRecipeFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ProfileViewModelFactory((activity?.application as App).repository)
        val viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        binding.newRecipeSaveButton.setOnClickListener {
            val recipeModel = RecipeModel(
                viewModel.repository.getMyRecipes().size + 1,
                binding.newRecipeNameView.text.toString(),
                binding.newRecipeDescriptionView.text.toString(),
                binding.newRecipeImageUrlView.text.toString()
                //binding.newRecipeVideoUrlView.text.toString()

            )

//            val gson = Gson()
//            val jsonString = gson.toJson(recipeModel)
//            val recipeEntity = RecipeEntity(json=jsonString)

//            viewModel.insertRecipe(recipeEntity)
            viewModel.insertRecipe(recipeModel)
        }

        viewModel.insertResult.observe(viewLifecycleOwner){
            if(it){
                navigateBack()
            }else{
                Toast.makeText(context, "Recipe insert FAILED!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateBack(){
        findNavController().popBackStack()
    }
}