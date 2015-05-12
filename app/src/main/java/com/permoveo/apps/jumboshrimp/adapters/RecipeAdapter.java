package com.permoveo.apps.jumboshrimp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.view.WidthSquareImageView;
import com.permoveo.apps.jumboshrimp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by byfieldj on 4/16/15.
 */
public class RecipeAdapter extends BaseAdapter {

    private List<Recipe> mRecipes;
    private Context mContext;
    private LayoutInflater mInflater;


    public RecipeAdapter(Context context) {
        mRecipes = new ArrayList<Recipe>();
        mContext = context;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addRecipes(List<Recipe> recipes) {
        mRecipes.addAll(recipes);
        notifyDataSetChanged();
    }

    public List<Recipe> getRecipes() {
        return mRecipes;
    }

    @Override
    public int getCount() {
        return mRecipes.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolder {

        @InjectView(R.id.ivRecipePhoto)
        public WidthSquareImageView recipePhoto;

        @InjectView(R.id.tvRecipeName)
        public TextView recipeTitle;

        public ViewHolder(View v) {
            ButterKnife.inject(this, v);
        }


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.grid_item_recipe, parent, false);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();


        //Load the recipe photo
        Picasso.with(mContext).load(mRecipes.get(position).getRecipePhotoUrl()).into(holder.recipePhoto);


        //Set recipe title
        holder.recipeTitle.setText(mRecipes.get(position).getRecipeName());


        return convertView;
    }
}
