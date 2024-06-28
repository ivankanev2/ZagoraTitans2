package com.example.navigation;

import android.content.Intent;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.*;
import java.util.*;

public class SearchActivity extends AppCompatActivity {
    // Declaration of UI components and data structures
    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<ModelClass> arrayList = new ArrayList<>();
    ArrayList<ModelClass> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Called when the activity is first created
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        // Initialize UI components
        recyclerView=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.seachView);
        searchView.setIconified(false);
        searchView.clearFocus();

        // Variables for data processing
        int counter = 0;
        int length = 309;

        // File to read product data from
        String fileNameDefined = "product_master_data.csv";
        AssetManager assetManager = getAssets();

        // Arrays to store product data
        String[] id_list = new String[length];
        String[] name_list = new String[length];
        String[] category_list = new String[length];
        int[] image_list = new int[length];


        try {
            // Read the CSV file
            InputStreamReader stream = new InputStreamReader(assetManager.open(fileNameDefined));
            BufferedReader reader = new BufferedReader(stream);
            reader.readLine(); // Skip the header line
            Scanner inputStream = new Scanner(reader);

            // Read data line by line
            String id;
            String category;
            String name;

            //Splits the data into 3 segments(id,category,name) and then assigns it to 3 different variables
            while (inputStream.hasNext()) {
                //Goes through the data 1 step at a time
                String data = inputStream.nextLine();
                //Splits the date
                String[] obj = data.split(",");
                //Removing the golden eggs
                if(!Objects.equals(obj[1], "P107") && !Objects.equals(obj[1], "P19") && !Objects.equals(obj[1], "P204") && !Objects.equals(obj[1], "P279") && !Objects.equals(obj[1], "P310")){
                //Distributes the data to corresponding variables
                id = obj[0].trim();
                category = obj[1].trim();
                name = obj[2].trim();
                //Assigns the variables to the lists introduced earlier to save all the data
                id_list[counter] = id;
                name_list[counter] = name;
                category_list[counter] = category;
                counter++;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        //Long code but all it does is it goes through all possible categories option and assigns them a corresponding picture
        for(int j = 0; j<length; j++){
            if(Objects.equals(category_list[j], "Алкохол")) image_list[j] = R.drawable.alcohol;
            else if(Objects.equals(category_list[j], "Бира")) image_list[j] = R.drawable.beer;
            else if(Objects.equals(category_list[j], "Био")) image_list[j] = R.drawable.bio;
            else if(Objects.equals(category_list[j], "Масла")) image_list[j] = R.drawable.butter;
            else if(Objects.equals(category_list[j], "Консерви")) image_list[j] = R.drawable.cans;
            else if(Objects.equals(category_list[j], "Шоколад")) image_list[j] = R.drawable.chocolate;
            else if(Objects.equals(category_list[j], "Препарати")) image_list[j] = R.drawable.cleaning_products;
            else if(Objects.equals(category_list[j], "Кафе")) image_list[j] = R.drawable.coffee;
            else if(Objects.equals(category_list[j], "Бисквити")) image_list[j] = R.drawable.cookies;
            else if(Objects.equals(category_list[j], "Сушени продукти")) image_list[j] = R.drawable.dried_products;
            else if(Objects.equals(category_list[j], "Яйца")) image_list[j] = R.drawable.egg;
            else if(Objects.equals(category_list[j], "Енергийни напитки")) image_list[j] = R.drawable.energy_drinks;
            else if(Objects.equals(category_list[j], "Риба")) image_list[j] = R.drawable.seafood;
            else if(Objects.equals(category_list[j], "Брашно")) image_list[j] = R.drawable.flour;
            else if(Objects.equals(category_list[j], "Плодове")) image_list[j] = R.drawable.fruits;
            else if(Objects.equals(category_list[j], "Сладолед")) image_list[j] = R.drawable.ice_cream;
            else if(Objects.equals(category_list[j], "Майонеза")) image_list[j] = R.drawable.mayo;
            else if(Objects.equals(category_list[j], "Месо")) image_list[j] = R.drawable.meat;
            else if(Objects.equals(category_list[j], "Мляко")) image_list[j] = R.drawable.milk;
            else if(Objects.equals(category_list[j], "Млечни продукти")) image_list[j] = R.drawable.milk_products;
            else if(Objects.equals(category_list[j], "Мюсли")) image_list[j] = R.drawable.muesli;
            else if(Objects.equals(category_list[j], "Нехранителни стоки")) image_list[j] = R.drawable.non_food;
            else if(Objects.equals(category_list[j], "Ядки")) image_list[j] = R.drawable.nuts;
            else if(Objects.equals(category_list[j], "Олио")) image_list[j] = R.drawable.oil;
            else if(Objects.equals(category_list[j], "Други")) image_list[j] = R.drawable.others;
            else if(Objects.equals(category_list[j], "Паста")) image_list[j] = R.drawable.pasta;
            else if(Objects.equals(category_list[j], "Други напитки")) image_list[j] = R.drawable.plant_drinks;
            else if(Objects.equals(category_list[j], "Солени изделия")) image_list[j] = R.drawable.salty_snacks;
            else if(Objects.equals(category_list[j], "Сосове")) image_list[j] = R.drawable.sauce;
            else if(Objects.equals(category_list[j], "Сладки")) image_list[j] = R.drawable.sweets;
            else if(Objects.equals(category_list[j], "Чай")) image_list[j] = R.drawable.tea;
            else if(Objects.equals(category_list[j], "Неща за майстори")) image_list[j] = R.drawable.tools;
            else if(Objects.equals(category_list[j], "Играчки")) image_list[j] = R.drawable.toys;
            else if(Objects.equals(category_list[j], "Варива")) image_list[j] = R.drawable.variva;
            else if(Objects.equals(category_list[j], "Зеленчуци")) image_list[j] = R.drawable.vegetables;
            else if(Objects.equals(category_list[j], "Оцет")) image_list[j] = R.drawable.vinegar;
        }

        //Just for test in case stuff starts to break, always good to check your list are not empty
        System.out.println(Arrays.toString(name_list));
        System.out.println(Arrays.toString(id_list));
        System.out.println(Arrays.toString(category_list));
        System.out.println(Arrays.toString(image_list));

        //Creating a ModelClass which takes the values id,category,name.
        for (int i = 0; i < id_list.length; i++){
            ModelClass modelClass = new ModelClass();
            modelClass.setIdList(id_list[i]);
            modelClass.setNameList(name_list[i]);
            modelClass.setCategoryList(category_list[i]);
            modelClass.setImage(image_list[i]);
            arrayList.add(modelClass);
        }
        //Displaying the recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        //Creating our adapter
        SearchAdapter searchAdapter  = new SearchAdapter(SearchActivity.this,arrayList);
        recyclerView.setAdapter(searchAdapter);

        //Code for when the text inside the search engine changes or is submitted, basically when out user types something
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //creating a new arraylist which only contains objects that are currently searched
                searchList = new ArrayList<>();
                if (query.length() > 0) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        //creating our model classes which contain our 3 values and adding it to our searchList
                        if (arrayList.get(i).getNameList().toUpperCase().contains(query.toUpperCase()) || arrayList.get(i).getCategoryList().toUpperCase().contains(query.toUpperCase())) {
                            ModelClass modelClass = new ModelClass();
                            modelClass.setNameList(arrayList.get(i).getNameList());
                            modelClass.setCategoryList(arrayList.get(i).getCategoryList());
                            modelClass.setImage(arrayList.get(i).getImage());
                            searchList.add(modelClass);
                        }
                    }
                    //Setting the current layout to this one
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this, searchList);
                    recyclerView.setAdapter(searchAdapter);
                } else {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this, arrayList);
                    recyclerView.setAdapter(searchAdapter);
                }
                return false;
            }

            //Same function but is used when the text changes
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();
                if (newText.length()>0){
                    for(int i = 0; i< arrayList.size() ; i++){
                        if(arrayList.get(i).getNameList().toUpperCase().contains(newText.toUpperCase()) || arrayList.get(i).getCategoryList().toUpperCase().contains(newText.toUpperCase())){
                            ModelClass modelClass=new ModelClass();
                            modelClass.setNameList(arrayList.get(i).getNameList());
                            modelClass.setCategoryList(arrayList.get(i).getCategoryList());
                            modelClass.setImage(arrayList.get(i).getImage());
                            searchList.add(modelClass);
                        }
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    SearchAdapter searchAdapter  = new SearchAdapter(SearchActivity.this,searchList);
                    recyclerView.setAdapter(searchAdapter);
                }
                else{
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    SearchAdapter searchAdapter  = new SearchAdapter(SearchActivity.this,arrayList){
                        //@Override
                        public void onClick(View view, int position) {
                            ModelClass modelClass = arrayList.get(position);
                            cart.addItem(modelClass);
                        }
                    };
                    recyclerView.setAdapter(searchAdapter);

                }
                return false;
            }
        });
        // Button or trigger to show cart items
        Button showCartButton = findViewById(R.id.showCartButton);
        showCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starting the activity of the fragment that we are going to
                Kart kartFragment = Kart.newInstance(null, null);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, kartFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}