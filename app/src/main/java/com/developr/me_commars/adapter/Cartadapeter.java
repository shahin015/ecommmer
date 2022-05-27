package com.developr.me_commars.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developr.me_commars.R;
import com.developr.me_commars.databinding.ItemCartBinding;
import com.developr.me_commars.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Cartadapeter extends RecyclerView.Adapter<Cartadapeter.CartViewHoleder> {

    Context context;
    ArrayList<Product>products;

    public Cartadapeter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public CartViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false);

        return new CartViewHoleder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHoleder holder, int position) {


        Picasso.get().load(products.get(position).getImage()).into(holder.binding.cartImage);
        holder.binding.name.setText(Html.fromHtml(products.get(position).getName()));
        holder.binding.prize.setText("BDT: "+products.get(position).getPrice());




    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CartViewHoleder extends RecyclerView.ViewHolder{


        ItemCartBinding binding;
        public CartViewHoleder(@NonNull View itemView) {
            super(itemView);
            binding=ItemCartBinding.bind(itemView);

        }
    }
}
