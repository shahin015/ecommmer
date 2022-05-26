package com.developr.me_commars.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.os.Bundle;

import com.developr.me_commars.R;
import com.developr.me_commars.databinding.ActivityMainBinding;
import com.developr.me_commars.databinding.ActivityProductDetaileBinding;
import com.squareup.picasso.Picasso;

public class ProductDetaile extends AppCompatActivity {

    ActivityProductDetaileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityProductDetaileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        int id = getIntent().getIntExtra("id", 0);
        double prize = getIntent().getDoubleExtra("prize", 0);
        Picasso.get().load(image).into(binding.prouctImage);
        //  getSupportActionBar().setTitle(name);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.Descritption.setText(name);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}