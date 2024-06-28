package com.example.navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/**
 * Adapter for the RecyclerView that displays cart items.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<ModelClass> cartItems;

    /*
            * Constructor for the adapter.
            * @param cartItems The list of cart items to be displayed.
     */
    public CartAdapter(List<ModelClass> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single cart item.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Get the current cart item.
        ModelClass item = cartItems.get(position);
        // Set the product name and category.
        holder.productName.setText(item.getNameList());
        holder.productCategory.setText(item.getCategoryList());

    }

    @Override
    // Return the total number of cart items.
    public int getItemCount() {
        return cartItems.size();
    }

    /**
     * ViewHolder for a single cart item.
     */

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productCategory;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views for the product name and category.
            productName = itemView.findViewById(R.id.cartProductName);
            productCategory = itemView.findViewById(R.id.cartProductCategory);
            // Initialize other views here
        }
    }
}