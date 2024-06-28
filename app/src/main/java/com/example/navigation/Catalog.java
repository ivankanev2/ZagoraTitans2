package com.example.navigation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Catalog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Catalog extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // Parameters passed to the fragment
    private String mParam1;
    private String mParam2;

    public Catalog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Catalog.
     */

    public static Catalog newInstance(String param1, String param2) {
        Catalog fragment = new Catalog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Retrieve parameters passed to the fragment
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        // Get the search button from the layout
        ImageButton search = view.findViewById(R.id.imageButton5);
        // Set an onClick listener for the search button
        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Start the SearchActivity when the search button is clicked
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}