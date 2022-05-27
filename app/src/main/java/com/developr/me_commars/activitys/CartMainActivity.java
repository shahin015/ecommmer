package com.developr.me_commars.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.developr.me_commars.R;
import com.developr.me_commars.adapter.Cartadapeter;
import com.developr.me_commars.databinding.ActivityCartMainBinding;
import com.developr.me_commars.model.Product;

import java.util.ArrayList;

public class CartMainActivity extends AppCompatActivity {

    ActivityCartMainBinding binding;

    Cartadapeter cartadapeter;
    ArrayList<Product>products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCartMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        products=new ArrayList<>();
        cartadapeter=new Cartadapeter(this,products);


        products.add(new Product("","","",45,45,45,45));

        binding.cartRecylerview.setLayoutManager(new LinearLayoutManager(this));
        binding.cartRecylerview.setAdapter(cartadapeter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}