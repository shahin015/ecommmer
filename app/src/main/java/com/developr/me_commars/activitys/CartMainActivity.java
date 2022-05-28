package com.developr.me_commars.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.developr.me_commars.adapter.Cartadapeter;
import com.developr.me_commars.databinding.ActivityCartMainBinding;
import com.developr.me_commars.model.Product;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;
import java.util.Map;

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
        Cart cart = TinyCartHelper.getCart();

        cart.getAllItemsWithQty();
        for (Map.Entry<Item, Integer> item:cart.getAllItemsWithQty().entrySet()){
            Product product= (Product) item.getKey();
            int quantaty=item.getValue();
            product.setQuanty(quantaty);
            products.add(product);
        }

        cartadapeter=new Cartadapeter(this, products, new Cartadapeter.Cartlesenaer() {
            @Override
            public void onQuntaychange() {
                binding.subtotoal.setText(String.format("BDT %.2f",cart.getTotalPrice()));

            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

        DividerItemDecoration itemDecoration=new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        binding.cartRecylerview.setLayoutManager(linearLayoutManager);
        binding.cartRecylerview.addItemDecoration(itemDecoration);
        binding.cartRecylerview.setAdapter(cartadapeter);
        binding.subtotoal.setText(String.format("BDT %.2f",cart.getTotalPrice()));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CartMainActivity.this,Checout.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}