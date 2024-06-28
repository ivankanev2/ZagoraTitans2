package com.example.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Kart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Kart extends Fragment {

    // Empty constructor required for fragments
    public Kart() {
        // Required empty public constructor
    }

    // View object to hold the main view of the fragment
    View main_view;

    // Factory method to create a new instance of the fragment
    public static Kart newInstance(String param1, String param2) {
        Kart fragment = new Kart();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        main_view = inflater.inflate(R.layout.fragment_kart, container, false);

        // Initialize RecyclerView
        RecyclerView cartRecyclerView = main_view.findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get items from Cart and set up the adapter
        Cart cart = Cart.getInstance();
        List<ModelClass> cartItems = cart.getItems();
        CartAdapter cartAdapter = new CartAdapter(cartItems);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        cartRecyclerView.setLayoutManager(manager);
        cartRecyclerView.setAdapter(cartAdapter);

        // Find the "Show Route" button
        Button mapButton = main_view.findViewById(R.id.showRouteButton);

        // Set an onClickListener to the button
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add item IDs to the input list for the DrawMapActivity
                if (!cartItems.isEmpty()) {
                    for (ModelClass item : cartItems) {
                        DrawMapActivity.input.add(item.id);
                    }

                    // Create an intent to start the DrawMapActivity
                    Intent intent = new Intent(getActivity(), DrawMapActivity.class);
                    // Start the activity
                    startActivity(intent);
                }
            }
        });
        return main_view;
    }
}