package com.developr.me_commars.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developr.me_commars.R;
import com.developr.me_commars.databinding.ItemCartBinding;
import com.developr.me_commars.databinding.QuntatiDailogBinding;
import com.developr.me_commars.model.Product;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Cartadapeter extends RecyclerView.Adapter<Cartadapeter.CartViewHoleder> {

    Context context;
    ArrayList<Product>products;
    Cartlesenaer cartlesenaer;
    Cart cart ;


    public interface Cartlesenaer{
        public void onQuntaychange();
    }

    public Cartadapeter(Context context, ArrayList<Product> products, Cartlesenaer cartlesenaer) {
        this.context = context;
        this.products = products;
        this.cartlesenaer=cartlesenaer;
        cart = TinyCartHelper.getCart();
    }

    @NonNull
    @Override
    public CartViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false);

        return new CartViewHoleder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHoleder holder, @SuppressLint("RecyclerView") int position) {

        Product product = products.get(position);

        Picasso.get().load(products.get(position).getImage()).into(holder.binding.cartImage);
        holder.binding.name.setText(Html.fromHtml(products.get(position).getName()));
        holder.binding.prize.setText("BDT: "+products.get(position).getPrice());

        holder.binding.cuntaty.setText("items"+products.get(position).getQuanty());
        holder.binding.layoutsitem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                QuntatiDailogBinding quntatiDailogBinding=QuntatiDailogBinding.inflate(LayoutInflater.from(context));
                AlertDialog alertDialog=new AlertDialog.Builder(context)
                        .setView(quntatiDailogBinding.getRoot())

                        .create();
                quntatiDailogBinding.productName.setText(products.get(position).getName());
                quntatiDailogBinding.productStock.setText(""+products.get(position).getStorck());
                quntatiDailogBinding.quantity.setText(String.valueOf(products.get(position).getQuanty()));
                int stocl= Integer.parseInt(""+products.get(position).getStorck());

                quntatiDailogBinding.plusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int qunaty=products.get(position).getQuanty();
                        qunaty++;
                        if (qunaty>product.getStorck()){
                            Toast.makeText(context, "Max stock available: "+product.getStorck(), Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            product.setQuanty(qunaty);
                            quntatiDailogBinding.quantity.setText(String.valueOf(qunaty));
                            notifyDataSetChanged();
                            cart.updateItem(product,product.getQuanty());
                            cartlesenaer.onQuntaychange();


                        }




                    }
                }); quntatiDailogBinding.minusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int qunaty=products.get(position).getQuanty();
                        if (qunaty>1){
                            qunaty--;
                            product.setQuanty(qunaty);
                            quntatiDailogBinding.quantity.setText(String.valueOf(qunaty));
                            notifyDataSetChanged();
                            cart.updateItem(product,product.getQuanty());
                            cartlesenaer.onQuntaychange();

                        }



                    }
                });
                quntatiDailogBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        notifyDataSetChanged();
                        cart.updateItem(product,product.getQuanty());
                        cartlesenaer.onQuntaychange();


                    }
                });
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                alertDialog.show();


            }
        });



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
