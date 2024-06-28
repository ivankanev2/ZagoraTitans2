package com.example.navigation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Space {

    public static void main(String[] args) {
        // Define the file name and path for the CSV file
        String fileNameDefined
                = "C:\\TheBigSchwarzRecreation-master\\data\\placement.csv";
        File file = new File(fileNameDefined);
        // Create a hashmap to store product nodes
        HashMap<String, Node> productMap = new HashMap<>();
        // Initialize an empty 2D array to represent the space
        int[][] space = new int[0][];
        // Initialize an array list to store golden eggs
        ArrayList goldenEggs = null;
        try {
            // Create a scanner to read the CSV file
            Scanner inputStream = new Scanner(file);
            // Skip the first line of the CSV file (header)
            inputStream.next();
            // Initialize variables to store maximum width and height
            int maxW = 0;
            int maxH = 0;
            // Define the golden eggs
            String[] eggs = {"P107", "P19", "P204", "P279", "P310"};
            goldenEggs = new ArrayList<>();
            for (String egg : eggs)
                goldenEggs.add(egg);
            // reading csv data and filing space matrix
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] obj = data.split(",");
                if (!goldenEggs.contains(obj[0].trim())) {
                    int x = Integer.parseInt(obj[1].trim());
                    int y = Integer.parseInt(obj[2].trim());
                    if (x > maxW) maxW = x;
                    if (y > maxH) maxH = y;
                }
            }

            // Initialize the space matrix with the maximum width and height
            space = new int[maxW + 1][maxH + 1];
            // Close the scanner and reopen the file to read again
            inputStream.close();
            inputStream = new Scanner(file);
            inputStream.next();

            // Read the CSV data again and fill the space matrix and product map
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] obj = data.split(",");
                int x = Integer.parseInt(obj[1].trim());
                int y = Integer.parseInt(obj[2].trim());
                space[x][y] = 1;
                productMap.put(obj[0].trim(), new Node(x, y));
                //System.out.println(obj[0] + "," + productMap.get(obj[0].trim()));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Define the input and output nodes
        Node[] inout =  { productMap.get("EN"),  productMap.get("EX")};


        // Define the start and end nodes
        Node start = new Node(0, 0);
        Node end = new Node(1, 3);

        // Define a list of nodes
        ArrayList<Node> list1 = new ArrayList<>();
        list1.add(start);
        list1.add(new Node(2, 5));
        list1.add(new Node(4, 13));
        list1.add(end);
        //System.out.println(list1 + " - " + Node.getRouteLength(space, list1));
        //System.out.println(getRoute(space, start, end,productMap,goldenEggs));
        //System.out.println(findPath(space,start,end));
        //System.out.println(Node.getallRoute(Node.findOptimalRouteBruteForce(list1,space), space,productMap,goldenEggs));
        //System.out.println(Node.getallRoute(list1, space,productMap, goldenEggs));
        //System.out.println(Node.NearestNeighbor(list1, space));



        // Print the optimized route
        System.out.println(Node.optimize(list1,space,productMap,goldenEggs));
    }





}