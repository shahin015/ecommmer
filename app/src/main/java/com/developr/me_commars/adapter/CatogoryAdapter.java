package com.developr.me_commars.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developr.me_commars.R;
import com.developr.me_commars.activitys.CategoryActivity;
import com.developr.me_commars.databinding.ItemCatagorysBinding;
import com.developr.me_commars.model.Catogory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatogoryAdapter extends RecyclerView.Adapter<CatogoryAdapter.CatogoryVideHolder>{

    Context context;
    ArrayList<Catogory>catogories;

    public CatogoryAdapter(Context context, ArrayList<Catogory> catogories) {
        this.context = context;
        this.catogories = catogories;
    }


    @NonNull
    @Override
    public CatogoryVideHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_catagorys,parent,false);


        return new CatogoryVideHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatogoryVideHolder holder, int position) {

        Catogory catogory=catogories.get(position);

        holder.binding.level.setText(Html.fromHtml(catogory.getName()));

        Picasso.get().load(catogory.getIcon()).into(holder.binding.image);
        holder.binding.image.setBackgroundColor(Color.parseColor(catogory.getColor()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CategoryActivity.class);
                intent.putExtra("catId",catogory.getId());
                intent.putExtra("categoryName",catogory.getName());
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return catogories.size();
    }

    public class CatogoryVideHolder extends RecyclerView.ViewHolder{

        ItemCatagorysBinding binding;

        public CatogoryVideHolder(@NonNull View itemView) {
            super(itemView);

            binding=ItemCatagorysBinding.bind(itemView);

        }
    }
}
