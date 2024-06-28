package com.example.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyHolder>{
    // Context for the adapter
    Context context;
    // ArrayList to store the data
    ArrayList<ModelClass> arrayList;
    // LayoutInflater for inflating the layout
    LayoutInflater layoutInflater;
    // Another ArrayList to store the search results
    ArrayList<ModelClass> searchList;
    // Singleton instance of the Cart class
    Cart cart = Cart.getInstance();


    // Constructor for the adapter
    public SearchAdapter(Context context, ArrayList<ModelClass> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SearchAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the item
        View view=layoutInflater.inflate(R.layout.item_file,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyHolder holder, int position) {
        // Set the product name and category
        holder.productName.setText(arrayList.get(position).getNameList());
        holder.productCategory.setText(arrayList.get(position).getCategoryList());
        // Set the image resource
        holder.img.setImageResource(arrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        // Return 0 if the array list is empty, otherwise return the size
        if(arrayList.isEmpty())
            return 0;
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        // TextViews for product name and category
        TextView productName, productCategory;
        // ImageView for the product image
        ImageView img;
        // ImageView for the add button
        ImageView add_button;
        // Button for showing the cart
        Button show_cart_button;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views
            productName=itemView.findViewById(R.id.text);
            productCategory=itemView.findViewById(R.id.text2);
            img=itemView.findViewById(R.id.image);

            //show_cart_button=itemView.findViewById(R.id.showCartButton);
            add_button=itemView.findViewById(R.id.AddButton);
            // Set an onClickListener for the add button
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the position of the item
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Get the item at the position
                        ModelClass item = arrayList.get(position);
                        // Add the item to the cart
                        cart.addItem(item);
                        // Get the size of the cart
                        int x = cart.getItems().size();
                        // Change the background color of the add button
                        add_button.setBackgroundColor(Color.parseColor("#ff0000"));
                    }
                }
            });
        }
    }


}
