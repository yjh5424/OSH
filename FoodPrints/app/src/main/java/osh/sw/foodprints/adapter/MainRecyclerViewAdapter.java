package osh.sw.foodprints.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import osh.sw.foodprints.FocusActivity;
import osh.sw.foodprints.R;
import osh.sw.foodprints.model.Food;

/**
 * Created by dsm2016 on 2017-07-29.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Food> arrayList;
    private Context context;
    private ArrayList<Food> saveItems;


    public MainRecyclerViewAdapter(Context context, ArrayList<Food> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food=arrayList.get(position);
        holder.foodNameTextView.setText(food.getFoodName());
        holder.foodStoreTextView.setText(food.getFoodStore());
        holder.ratingBar.setRating((food.getStarRecommend()));
        holder.foodImageView.setImageResource(food.getFoodImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, FocusActivity.class);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView foodImageView;
        private TextView foodNameTextView;
        private TextView foodStoreTextView;

        private CardView cardView;
        private RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);
            foodImageView=view.findViewById(R.id.foodImage);
            foodNameTextView=view.findViewById(R.id.foodNameTextView);
            foodStoreTextView=view.findViewById(R.id.foodStoreTextView);
            ratingBar=view.findViewById(R.id.ratingBar);
            cardView=view.findViewById(R.id.cardView);
        }


    }
}
