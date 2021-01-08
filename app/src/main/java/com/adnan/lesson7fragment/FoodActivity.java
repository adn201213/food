package com.adnan.lesson7fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FoodActivity extends AppCompatActivity {
private ImageView imageView;
private TextView textViewName;
    private TextView textViewPrice;
    private TextView textViewDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        imageView=findViewById(R.id.foodActivity_im_foodImage);
        textViewName=findViewById(R.id.foodActivity_tv_foodName);
        textViewPrice=findViewById(R.id.foodActivity_tv_price);
        textViewDescription=findViewById(R.id.foodActivity_tv_desc);
        if(getIntent()!=null){

          Food food= (Food) getIntent().getSerializableExtra("food");
        //  imageView.setImageResource(food.getFoodImage());
            Picasso.get().load(food.getFoodImage()).into(imageView);
            imageView.setX(-1000);
            imageView.animate().translationXBy(1000);
            textViewName.setText(food.getFoodName());
           // double price=food.getPrice();
            textViewPrice.append(String.valueOf(food.getPrice()));
            textViewDescription.setText(food.getFoodDescription());
        }


    }
}