package com.adnan.lesson7fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SweetAdapter extends RecyclerView.Adapter<SweetAdapter.FoodHolder>{

   private ArrayList<Sweet> sweets;

private Context context;
    private RecyclerViewClick recyclerViewClick;

    public SweetAdapter(ArrayList<Sweet> sweets, Context context, RecyclerViewClick recyclerViewClick) {
        this.sweets = sweets;
        this.context = context;
        this.recyclerViewClick = recyclerViewClick;
    }

   


    public class FoodHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public FoodHolder(@NonNull View itemView) {
            super(itemView);
       //     imageView=itemView.findViewById(R.id.fooditem_im_foodImage);
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
    public void onBindViewHolder(@NonNull FoodHolder holder, final int position) {

    Sweet sweet=sweets.get(position);

            holder.imageView.setImageResource(sweet.getSweetImage());
            holder.textView.setText(sweet.getSweetName());

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
        return sweets.size();
    }



}
