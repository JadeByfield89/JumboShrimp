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

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        mRecipes = recipes;
        mContext = context;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

<<<<<<< HEAD
    static class ViewHolder {

        @InjectView(R.id.ivRecipePhoto)
        public ImageView recipePhoto;

        @InjectView(R.id.tvRecipeName)
        public TextView recipeTitle;

        public ViewHolder(View v) {
            ButterKnife.inject(this, v);
        }
=======
    private class ViewHolder{
        private WidthSquareImageView recipePhoto;
        private TextView recipeTitle;
>>>>>>> eac43bf28871c4a0602b1bcb16f593fadd9054de
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.grid_item_recipe, parent, false);
<<<<<<< HEAD
            holder = new ViewHolder(convertView);
=======
            holder.recipePhoto = (WidthSquareImageView) convertView.findViewById(R.id.ivRecipePhoto);
            holder.recipeTitle = (TextView) convertView.findViewById(R.id.tvRecipeName);
>>>>>>> eac43bf28871c4a0602b1bcb16f593fadd9054de

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
