package com.developr.me_commars.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developr.me_commars.R;

import com.developr.me_commars.databinding.ActivityProductDetaileBinding;
import com.developr.me_commars.model.Product;
import com.developr.me_commars.utils.Constants;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetaile extends AppCompatActivity {

    ActivityProductDetaileBinding binding;
    Product currentpordct;

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

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);

        getProudct(id);

        Cart cart = TinyCartHelper.getCart();
        binding.Descritption.setText(name);

        binding.addtoCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.addItem(currentpordct,1);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId()==R.id.cartsss){
           startActivity(new Intent(this,CartMainActivity.class));
       }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }



    private  void getProudct(int id){

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, Constants.GET_PRODUCT_DETAILS_URL + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);

                    if (object.getString("status").equals("success")){
                        JSONObject proudct=object.getJSONObject("product");
                        String description=proudct.getString("description");
                        binding.Descritption.setText(Html.fromHtml(description));

                        String name=proudct.getString("name");
                        String image=proudct.getString("image");
                        String status=proudct.getString("status");
                        double prize=proudct.getDouble("price");
                        double discpont=proudct.getDouble("price_discount");
                        int stock=proudct.getInt("stock");
                        // String brief=object.getString("brief");
                        int id =proudct.getInt("id");
                        currentpordct=new Product(name,Constants.PRODUCTS_IMAGE_URL+image,status,prize,discpont,stock,id);



                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);



    }
}