package com.adnan.lesson7fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SweetActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewName;
    private TextView textViewPrice;
    private TextView textViewDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweet);

        imageView=findViewById(R.id.sweetActivity_im_foodImage);
        textViewName=findViewById(R.id.sweetActivity_tv_foodName1);
        textViewPrice=findViewById(R.id.sweetActivity_tv_price);
        textViewDescription=findViewById(R.id.sweetActivity_tv_desc);
        if(getIntent()!=null){

            Sweet sweet= (Sweet) getIntent().getSerializableExtra("sweet");
           imageView.setImageResource(sweet.getSweetImage());

            imageView.setX(-1000);
            imageView.animate().translationXBy(1000);
            textViewName.setText(sweet.getSweetName());
            double price=sweet.getPrice();
            textViewPrice.append(String.valueOf(price));
    textViewDescription.setText(sweet.getDescription());
        }
    }
}