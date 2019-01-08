package com.example.alirzaycefaydal.ticaret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView image_thsirts, image_sweather, image_female_dresses, image_sports,image_glasses,image_bags,image_hats,image_shoes,
    image_headphones,image_computers,image_watchs,image_mobilephones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        utils();


    }

    private void utils(){
        image_thsirts=findViewById(R.id.tshirts);
        image_sweather=findViewById(R.id.sweathers);
        image_female_dresses=findViewById(R.id.female_dresses);
        image_sports=findViewById(R.id.sports);
        image_glasses=findViewById(R.id.glasses);
        image_bags=findViewById(R.id.purses);
        image_hats=findViewById(R.id.hats);
        image_shoes=findViewById(R.id.shoes);
        image_headphones=findViewById(R.id.headphones);
        image_computers=findViewById(R.id.laptops);
        image_watchs=findViewById(R.id.watchs);
        image_mobilephones=findViewById(R.id.mobilesphones);

        image_thsirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thsirtIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                thsirtIntent.putExtra("category","Thsirts");
                startActivity(thsirtIntent);
            }
        });

        image_sweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sweatherIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                sweatherIntent.putExtra("category","sweather");
                startActivity(sweatherIntent);
            }
        });

        image_female_dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent female_dressesIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                female_dressesIntent.putExtra("category","female_dresses");
                startActivity(female_dressesIntent);
            }
        });

        image_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sportsIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                sportsIntent.putExtra("category","sports");
                startActivity(sportsIntent);
            }
        });

        image_glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent glassesIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                glassesIntent.putExtra("category","glasses");
                startActivity(glassesIntent);
            }
        });

        image_bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bagsIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                bagsIntent.putExtra("category","bags");
                startActivity(bagsIntent);
            }
        });

        image_hats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hatsIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                hatsIntent.putExtra("category","hats");
                startActivity(hatsIntent);
            }
        });

        image_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shoesIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                shoesIntent.putExtra("category","shoes");
                startActivity(shoesIntent);
            }
        });

        image_headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent headphonesIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                headphonesIntent.putExtra("category","headphones");
                startActivity(headphonesIntent);
            }
        });

        image_computers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent computersIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                computersIntent.putExtra("category","computers");
                startActivity(computersIntent);
            }
        });

        image_watchs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent watchsIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                watchsIntent.putExtra("category","watchs");
                startActivity(watchsIntent);
            }
        });

        image_mobilephones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mobilephonesIntent=new Intent(AdminCategoryActivity.this,AdminNewProductActivity.class);
                mobilephonesIntent.putExtra("category","mobilephones");
                startActivity(mobilephonesIntent);
            }
        });
    }
}
