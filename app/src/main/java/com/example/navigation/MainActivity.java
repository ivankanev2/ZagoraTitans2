package com.example.navigation;

// Import necessary classes
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

// Define the MainActivity class, which extends AppCompatActivity
public class MainActivity extends AppCompatActivity {

    // Override the onCreate method, which is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to activity_main.xml
        setContentView(R.layout.activity_main);

        // Find the ImageButton with the id imageButton10
        ImageButton switchButton = findViewById(R.id.imageButton10);
        // Set an onClickListener to the button
        switchButton.setOnClickListener(new View.OnClickListener() {
            // Override the onClick method, which is called when the button is clicked
            @Override
            public void onClick(View v) {
                // Call the switchFragment method, passing a new instance of the Catalog fragment
                switchFragment(new Catalog());
            }
        });

        // Find the ImageButton with the id imageButton11
        ImageButton switchButton2 = findViewById(R.id.imageButton11);
        // Set an onClickListener to the button
        switchButton2.setOnClickListener(new View.OnClickListener() {
            // Override the onClick method, which is called when the button is clicked
            @Override
            public void onClick(View v) {
                // Call the switchFragment method, passing a new instance of the Kart fragment
                switchFragment(new Kart());
            }
        });

        // Find the ImageButton with the id imageButton
        ImageButton switchButton3 = findViewById(R.id.imageButton);
        // Set an onClickListener to the button
        switchButton3.setOnClickListener(new View.OnClickListener() {
            // Override the onClick method, which is called when the button is clicked
            @Override
            public void onClick(View v) {
                // Call the switchFragment method, passing a new instance of the Map fragment
                switchFragment(new Map());
            }
        });
    }

    // Define the switchFragment method, which replaces the current fragment with a new one
    public void switchFragment(Fragment fragment) {
        // Get the FragmentManager instance
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Begin a new FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Replace the fragment in the fragmentContainerView with the new fragment
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        // Add the transaction to the back stack (optional)
        fragmentTransaction.addToBackStack(null);
        // Commit the transaction
        fragmentTransaction.commit();
    }
}