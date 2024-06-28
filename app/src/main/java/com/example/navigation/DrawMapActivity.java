package com.example.navigation;

import android.content.res.AssetManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewTreeObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DrawMapActivity extends AppCompatActivity {
    private MapView mapView;
    private static final int MAP_WIDTH = 640*2; // maxWidth
    private static final int MAP_HEIGHT = 1186*2; // mapHeight
    public static List<String> input = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_map);
        mapView = findViewById(R.id.photo_view);

        // Ensure the MapView is fully laid out before initializing the line view
        ViewTreeObserver vto = mapView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                initializeLineView();
            }
        });
    }

    private void initializeLineView() {

        String fileNameDefined = "placement.csv";
        AssetManager assetManager = getAssets();

        HashMap<String, Node> productMap = new HashMap<>();
        int[][] space = new int[0][];
        ArrayList goldenEggs = null;
        try {
            Scanner inputStream = new Scanner(assetManager.open(fileNameDefined));
            inputStream.next();
            int maxW = 0;
            int maxH = 0;
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
            space = new int[maxW + 1][maxH + 1];
            inputStream.close();
            inputStream = new Scanner(assetManager.open(fileNameDefined));
            inputStream.next();
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] obj = data.split(",");
                int x = Integer.parseInt(obj[1].trim());
                int y = Integer.parseInt(obj[2].trim());
                space[x][y] = 1;
                productMap.put(obj[0].trim(), new Node(x, y));
                //System.out.println(obj[0] + "," + productMap.get(obj[0].trim()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }




        List<Node> pts = new ArrayList<>();
        for (String product : input){
            pts.add(productMap.get(product));
        }
        List<Node> test = new ArrayList<>();
        test.add(new Node(0,0));
        test.add(new Node(40,20));
        long time1 = System.currentTimeMillis();
        List<Node> route = Node.getallRoute( Node.optimize(pts,space,productMap,goldenEggs), space, productMap, goldenEggs);
        long time2 = System.currentTimeMillis();
        long time3 = time2 - time1;
        System.out.println(time3);

        List<float[]> points = convertPoints(route, mapView.getWidth(), mapView.getHeight());
        mapView.setPoints(points)  ;
    }

    public List<float[]> convertPoints(List<Node> points, float mapWidth, float mapHeight) {
        List<float[]> result = new LinkedList<>();
        for (Node n : points) {
            float[] coords = new float[2];
            int kx = 70; // width of each individual box
            int ky = 73; // height of each individual box

            coords[1] = mapWidth - kx * n.x + 1850;
            coords[0] = mapHeight - ky * n.y  - 850;
            result.add(coords);
        }
        return result;
    }
}
