package com.adnan.lesson7fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder>{
    private ArrayList<Food> foods;
private Context context;
private RecyclerViewClick recyclerViewClick;


    public FoodAdapter(ArrayList<Food> foods, Context context,RecyclerViewClick recyclerViewClick) {
        this.foods = foods;
        this.context = context;
        this.recyclerViewClick=recyclerViewClick;
    }

    public FoodAdapter(FirestoreRecyclerOptions<Food> options) {
    }

    public class FoodHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public FoodHolder(@NonNull View itemView) {
            super(itemView);
   //         imageView=itemView.findViewById(R.id.fooditem_im_foodImage);
            textView=itemView.findViewById(R.id.fooditem_tv_foodName);

        }
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fooditem,parent,false);
        return new FoodHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodHolder holder, final int position) {

      Food food=foods.get(position);

          //  holder.imageView.setImageResource(food.getFoodImage());
            holder.textView.setText(food.getFoodName());

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       recyclerViewClick.onItemClick(position);
    }
});



    }

    @Override
    public int getItemCount()
    {
        return foods.size();
    }



}
