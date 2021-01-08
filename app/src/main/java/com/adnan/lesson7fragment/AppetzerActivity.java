package com.adnan.lesson7fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AppetzerActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewName;
    private TextView textViewPrice;
    private TextView textViewDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetzer);

        imageView=findViewById(R.id.appetizerActivity_im_foodImage);
        textViewName=findViewById(R.id.appetizerActivity_tv_foodName);
        textViewPrice=findViewById(R.id.appetizerActivity_tv_price);
        textViewDescription=findViewById(R.id.appetizerActivity_tv_desc);
        if(getIntent()!=null){

            Appetizer apetizer= (Appetizer) getIntent().getSerializableExtra("apetizer");
            imageView.setImageResource(apetizer.getAppetizerImage());

            imageView.setX(-1000);
            imageView.animate().translationXBy(1000);
            textViewName.setText(apetizer.getAppetizerName());
            double price=apetizer.getPrice();
            textViewPrice.append(String.valueOf(price));
            textViewDescription.setText(apetizer.getDescription());
        }


    }



}