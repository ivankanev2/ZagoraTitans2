package com.example.navigation;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Map#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Map extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    // Empty constructor required for fragment
    public Map() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Map.
     */

    public static Map newInstance(String param1, String param2) {
        Map fragment = new Map();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get arguments passed to the fragment
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Find the PhotoView in the layout
        PhotoView photoView = view.findViewById(R.id.photo_view);

        // Load the image from drawable using Glide
        Glide.with(this)
                .load(R.drawable.layout)  // Load the image from drawable
                .into(photoView);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}