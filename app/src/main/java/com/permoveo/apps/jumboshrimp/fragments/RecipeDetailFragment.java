package com.permoveo.apps.jumboshrimp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.adapters.RecipeAdapter;
import com.permoveo.apps.jumboshrimp.listeners.OnApiRequestCompletedListener;
import com.permoveo.apps.jumboshrimp.model.Ingredient;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.permoveo.apps.jumboshrimp.providers.BigOvenDataSourceProvider;
import com.permoveo.apps.jumboshrimp.utils.FragmentUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RecipeDetailFragment extends Fragment implements OnApiRequestCompletedListener {

    public static final String TAG = RecipeSearchFragment.class.getSimpleName();

    @InjectView(R.id.ivRecipePhoto)
    ImageView mRecipePhoto;

    @InjectView(R.id.tvRecipeName)
    TextView mRecipeTitle;

    @InjectView(R.id.llDescription)
    LinearLayout mDescriptionLayout;

    @InjectView(R.id.tvDescription)
    TextView mDescription;

    @InjectView(R.id.llIngredients)
    LinearLayout mIngredientsLayout;

    @InjectView(R.id.tvIngredients)
    TextView mIngredients;

    @InjectView(R.id.llInstructions)
    LinearLayout mInstructionsLayout;

    @InjectView(R.id.tvInstructions)
    TextView mInstructions;

    private Recipe mRecipe;
    private BigOvenDataSourceProvider mProvider;

    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setRecipe(recipe);
        return fragment;
    }

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.inject(this, v);

        displayRecipeBasicInfo();
        if (mRecipe.getDescription().isEmpty()) {
            getRecipe();
        } else {
            displayRecipeDetails();
        }

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void getRecipe() {
        // Show progress dialog
        FragmentUtil.showProgressDialog(getActivity());

        mProvider = new BigOvenDataSourceProvider(getActivity());
        mProvider.setListener(this);
        mProvider.getRecipe(mRecipe.getRecipeID());
    }

    private void displayRecipeBasicInfo() {
        if (!mRecipe.getRecipePhotoUrl().isEmpty()) {
            Picasso.with(getActivity()).load(mRecipe.getRecipePhotoUrl()).into(mRecipePhoto);
        }
        mRecipeTitle.setText(mRecipe.getRecipeName());
    }

    private void displayRecipeDetails() {
        // display description
        if (!mRecipe.getDescription().isEmpty()) {
            mDescriptionLayout.setVisibility(View.VISIBLE);
            mDescription.setText(mRecipe.getDescription());
        }

        // display instructions
        if (!mRecipe.getInstructions().isEmpty()) {
            mInstructionsLayout.setVisibility(View.VISIBLE);
            mInstructions.setText(mRecipe.getInstructions());
        }

        // display ingredients
        List<Ingredient> ingredients = mRecipe.getIngredients();
        if (ingredients != null && ingredients.size() > 0) {
            mIngredientsLayout.setVisibility(View.VISIBLE);

            String ingredientsText = "";
            for (Ingredient ingredient : ingredients) {
                String name = ingredient.getName();
                String displayQuantity = ingredient.getDisplayQuantity();
                String preparationNotes = ingredient.getPreparationNotes();
                String unit = ingredient.getUnit();

                if (!displayQuantity.isEmpty()) {
                    ingredientsText += displayQuantity + " ";
                }
                if (!unit.isEmpty()) {
                    ingredientsText += unit + " ";
                }
                ingredientsText += name;
                if (!preparationNotes.isEmpty()) {
                    ingredientsText += "; " + preparationNotes;
                }
                ingredientsText += "\n";
            }
            mIngredients.setText(ingredientsText);
        }
    }

    @Override
    public void onApiRequestSuccess(Recipe recipe) {

        // Clear progress dialog
        FragmentUtil.clearProgressDialog(getActivity());

        mRecipe = mProvider.getRecipe();
        displayRecipeDetails();
    }

    @Override
    public void onApiRequestError() {
        // Clear progress dialog
        FragmentUtil.clearProgressDialog(getActivity());
    }

}