package com.example.alirzaycefaydal.ticaret;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder{
    TextView product_name, product_description,price;
    ImageView product_image;
    ItemClickListener itemClickListener;

    public ViewHolder(@NonNull final View itemView) {
        super(itemView);

        product_description=itemView.findViewById(R.id.single_product_description);
        product_name=itemView.findViewById(R.id.single_product_name);
        product_image=itemView.findViewById(R.id.single_product_image);
        price=itemView.findViewById(R.id.single_product_price);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(v,getAdapterPosition(),false);
            }
        });
    }

    private void setItemClickListener(ItemClickListener listener){
        this.itemClickListener=listener;
    }
}
