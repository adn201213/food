package com.adnan.lesson7fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button foodButton, appetizerButton,sweetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodButton=findViewById(R.id.main_btn_foodMenu);
        foodButton.setOnClickListener(this);
        appetizerButton=findViewById(R.id.main_btn_appetizersMenu);
        appetizerButton.setOnClickListener(this);
        sweetButton=findViewById(R.id.main_btn_sweetMenu);
        sweetButton.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame_layout,new FoodFragment())
                .commit();

        setTitle("Fresh Food");
    }
public void showFragment(Fragment fragment){

     getSupportFragmentManager().beginTransaction()
            .replace(R.id.main_frame_layout,fragment)
            .commit();

}
    @Override
    public void onClick(View v) {
      Fragment fragment=null;
        switch(v.getId())  {
          case R.id.main_btn_foodMenu:
           //   fragment=new FoodFragment();
              Toast.makeText(this, "food", Toast.LENGTH_SHORT).show();
              getSupportFragmentManager().beginTransaction()
                      .replace(R.id.main_frame_layout,new FoodFragment())
                      .commit();
              break;
          case R.id.main_btn_appetizersMenu:
            //  fragment=new ApetizerFragment();
              Toast.makeText(this, "appetizer", Toast.LENGTH_SHORT).show();
              getSupportFragmentManager().beginTransaction()
                      .replace(R.id.main_frame_layout,new ApetizerFragment())
                      .commit();
              break;
          case R.id.main_btn_sweetMenu:
             // fragment=new SweetFragment();
              Toast.makeText(this, "sweet", Toast.LENGTH_SHORT).show();
              getSupportFragmentManager().beginTransaction()
                      .replace(R.id.main_frame_layout,
                              new SweetFragment())
                      .commit();
              Toast.makeText(this, "welcome to sweet page", Toast.LENGTH_LONG).show();
              break;


      }
       // showFragment(fragment);
    }
}