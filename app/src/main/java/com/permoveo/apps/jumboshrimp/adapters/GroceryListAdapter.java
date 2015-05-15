package com.permoveo.apps.jumboshrimp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.permoveo.apps.jumboshrimp.R;
import com.permoveo.apps.jumboshrimp.model.GroceryItem;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by byfieldj on 5/14/15.
 */
public class GroceryListAdapter extends ArrayAdapter<GroceryItem> {

    private Context mContext;
    private List<GroceryItem> groceryItems;
    private LayoutInflater mInflater;

    public GroceryListAdapter(Context context, List<GroceryItem> items) {
        super(context, R.layout.item_grocery_list, items);

        groceryItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return groceryItems.size();
    }

    static class ViewHolder {

        @InjectView(R.id.tvItemName)
        TextView title;

        @InjectView(R.id.tvItemPrice)
        TextView price;


        @InjectView(R.id.tvItemQuantity)
        TextView quantity;

        public ViewHolder(View v){
            ButterKnife.inject(this, v);
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_grocery_list, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }

        else
        holder = (ViewHolder)convertView.getTag();

        holder.title.setText(groceryItems.get(position).getItemName());
        holder.price.setText("" +groceryItems.get(position).getPrice());
        holder.quantity.setText(""+groceryItems.get(position).getQuantity());

        return convertView;
    }
}
