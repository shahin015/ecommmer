package com.developr.me_commars.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developr.me_commars.R;
import com.developr.me_commars.adapter.CatogoryAdapter;
import com.developr.me_commars.adapter.ProudctAdapter;
import com.developr.me_commars.databinding.ActivityMainBinding;
import com.developr.me_commars.model.Catogory;
import com.developr.me_commars.model.Product;
import com.developr.me_commars.utils.Constants;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    CatogoryAdapter catogoryAdapter;
    ArrayList<Catogory>catogories;
    ProudctAdapter proudctAdapter;
   ArrayList <Product>product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());




       intCatogottus();
        intProudcts();
        intSlider();
        getCatarogory();




    }

    private void intSlider() {
//        binding.carousel.addData(new CarouselItem("https://cdn.icon-icons.com/icons2/2596/PNG/512/check_one_icon_155665.png","Poudct Frist"));
//        binding.carousel.addData(new CarouselItem("https://cdn.icon-icons.com/icons2/2596/PNG/512/check_one_icon_155665.png","Poudct Frist"));
//        binding.carousel.addData(new CarouselItem("https://cdn.icon-icons.com/icons2/2596/PNG/512/check_one_icon_155665.png","Poudct Frist"));
//        binding.carousel.addData(new CarouselItem("https://cdn.icon-icons.com/icons2/2596/PNG/512/check_one_icon_155665.png","Poudct Frist"));
//        binding.carousel.addData(new CarouselItem("https://cdn.icon-icons.com/icons2/2596/PNG/512/check_one_icon_155665.png","Poudct Frist"));


        getResentOffer();

    }

    void intCatogottus(){

        catogories=new ArrayList<>();

        catogoryAdapter=new CatogoryAdapter(this,catogories);
        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        binding.catogprysist.setLayoutManager(layoutManager);
        binding.catogprysist.setAdapter(catogoryAdapter);

    }

    void  intProudcts(){
        product=new ArrayList<>();
        getResentProudct();


        proudctAdapter=new ProudctAdapter(this,product);

        GridLayoutManager layoutManager=new GridLayoutManager(MainActivity.this,2);
        binding.producList.setLayoutManager(layoutManager);
        binding.producList.setAdapter(proudctAdapter);







    }



    private void getCatarogory(){
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);

        StringRequest request=new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("tag",response);

                try {
                    JSONObject manObj=new JSONObject(response);
                    if (manObj.getString("status").equals("success")){

                        JSONArray catagoriysArry=manObj.getJSONArray("categories");
                        for (int i=0;i<catagoriysArry.length();i++){

                            JSONObject object=catagoriysArry.getJSONObject(i);
                            String name=object.getString("name");
                            String icon=object.getString("icon");
                            String color=object.getString("color");
                            String brief=object.getString("brief");
                            int id =object.getInt("id");
                            Catogory catogory=new Catogory(name,Constants.CATEGORIES_IMAGE_URL+icon,color,brief,id);

                          ///  Toast.makeText(getApplicationContext(), Constants.CATEGORIES_IMAGE_URL+icon, Toast.LENGTH_SHORT).show();
                            catogories.add(catogory);

                        }

                        catogoryAdapter.notifyDataSetChanged();


                    }







                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("ex", String.valueOf(e));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);


    }




    private void getResentProudct(){
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        String url=Constants.GET_PRODUCTS_URL+"?count10";

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("tag",response);

                try {
                    JSONObject manObj=new JSONObject(response);
                    if (manObj.getString("status").equals("success")){

                        JSONArray catagoriysArry=manObj.getJSONArray("products");
                        for (int i=0;i<catagoriysArry.length();i++){

                            JSONObject object=catagoriysArry.getJSONObject(i);
                            String name=object.getString("name");
                            String image=object.getString("image");
                            String status=object.getString("status");
                            double prize=object.getDouble("price");
                            double discpont=object.getDouble("price_discount");
                            int stock=object.getInt("stock");
                           // String brief=object.getString("brief");
                            int id =object.getInt("id");
                            Product products=new Product(name,Constants.PRODUCTS_IMAGE_URL+image,status,prize,discpont,stock,id);

                            product.add(products);


                        }

                        proudctAdapter.notifyDataSetChanged();


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("ex", String.valueOf(e));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);


    }

    private void getResentOffer(){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, Constants.GET_OFFERS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Slider",response);
                try {
                    JSONObject object=new JSONObject(response);
                    if (object.getString("status").equals("success")){

                        JSONArray offerArry=object.getJSONArray("news_infos");
                        for (int i=0;i<offerArry.length(); i++){
                            JSONObject childObject=offerArry.getJSONObject(i);
                            binding.carousel.addData(new CarouselItem(Constants.NEWS_IMAGE_URL+childObject.getString("image"),childObject.getString("title")));


                        }





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