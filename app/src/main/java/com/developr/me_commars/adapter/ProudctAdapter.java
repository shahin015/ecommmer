package com.developr.me_commars.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developr.me_commars.R;
import com.developr.me_commars.activitys.ProductDetaile;
import com.developr.me_commars.databinding.ItemProudctBinding;
import com.developr.me_commars.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProudctAdapter extends RecyclerView.Adapter<ProudctAdapter.ProudctViewHoldre>{

    Context context;
    ArrayList<Product>products;

    public ProudctAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProudctViewHoldre onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_proudct,parent,false);

        return new ProudctViewHoldre(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProudctViewHoldre holder, @SuppressLint("RecyclerView") int position) {
        Product product= products.get(position);

        Picasso.get().load(product.getImage()).into(holder.binding.image);
        holder.binding.levels.setText(product.getName());
        holder.binding.levels.setText("BDT "+product.getPrice());
        holder.binding.allitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, ProductDetaile.class);
               intent.putExtra("name",products.get(position).getName());
               intent.putExtra("image",products.get(position).getImage());
               intent.putExtra("id",products.get(position).getId());
               intent.putExtra("prize",products.get(position).getPrice());


                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProudctViewHoldre extends RecyclerView.ViewHolder {

        ItemProudctBinding binding;


        public ProudctViewHoldre(@NonNull View itemView) {
            super(itemView);

            binding=ItemProudctBinding.bind(itemView);

        }
    }
}
