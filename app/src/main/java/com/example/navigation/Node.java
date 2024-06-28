package com.example.navigation;

import java.sql.ResultSet;
import java.util.*;

public class Node {
    // Previous node in the path
    Node previous = null;
    // X and Y coordinates of the node
    public int x;
    public  int y;

    // Constructor for the node
    public Node(int x,int y){
        this.x = x;
        this.y = y;
    }

    // Override toString method to return a string representation of the node
    @Override
    public String toString(){
        return "("+x+","+y+")";
    }

    // Static arrays for direction vectors (up, down, left, right, and diagonals)
    public static int[] dir_x = {-1, 1, 0, 0, -1, -1, 1, 1};
    public static int[] dir_y = {0, 0, -1, 1, -1, 1, -1, 1};

    // Method to optimize the route
    public static List<Node> optimize(List<Node> points,int[][] space, HashMap<String, Node> productMap, List<String> goldenEggs ){
        List<Node> result = null;
        int routeLength = 0;
        List<Node> checkout = new ArrayList<>();
        checkout.add(productMap.get("CA3"));
        checkout.add(productMap.get("S3"));
        if (points.size()<6){
            // Use brute force algorithm for small number of points
            result = findOptimalRouteBruteForce(points,space, productMap, checkout);
        }else{
            // Use nearest neighbor algorithm for large number of points
            result = NearestNeighbor(points, space, checkout, productMap.get("EN"), productMap.get("EX"));
        }
        if (countGoldenEggs(getallRoute(result,space,productMap,goldenEggs), productMap,goldenEggs)<1){
            // If no golden eggs are found, try adding each golden egg to the route
            int mindist = Integer.MAX_VALUE;
            List<Node> finalRoute = null;
            for (String egg: goldenEggs){
                points.add(productMap.get(egg));
                if (points.size()<7 ){
                    result = findOptimalRouteBruteForce(points,space, productMap, checkout);
                }else{
                    result = NearestNeighbor(points, space, checkout, productMap.get("EN"), productMap.get("EX"));
                }
                if (mindist>getRouteLength(space,result)){
                    mindist = getRouteLength(space,result);
                    finalRoute = result;
                }
                points.remove(points.size()-1);
            }
            result = finalRoute;
        }
        //routeLength = getRouteLength(space, result);
        //getallRoute(result, space, productMap, goldenEggs);
        return result;
    }

    // Method to calculate distance between two nodes
    public static int dist(int[][] s, Node start, Node end){
        // Use BFS to find the shortest distance between start and end nodes
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[s.length][s[0].length];
        int cur_dist = 0;
        q.offer(start);
        visited[start.x][start.y] = true;
        while (!q.isEmpty()){
            int size =  q.size();
            for(int j = 0; j < size; j++){
                Node current = q.poll();
                if (current.x == end.x && current.y == end.y) return cur_dist;
                if (s[current.x][current.y]==0 || current==start) {
                    for (int i = 0; i < 8; i++) {
                        int newX = current.x + dir_x[i];
                        int newY = current.y + dir_y[i];
                        if (isValid(newX, newY, s, visited)) {
                            q.offer(new Node(newX, newY));
                            visited[newX][newY] = true;
                        }
                    }
                }
            }
            cur_dist++;
        }

        return cur_dist;
    }

    // Method to check if a node is valid (within bounds and not visited)
    public static boolean isValid(int x, int y, int[][] matrix, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length &&
                !visited[x][y];
    }

    // Method to find the optimal route using brute force algorithm
    public static List<Node> findOptimalRouteBruteForce(List<Node> points, int[][] matrix, HashMap<String, Node> productMap, List<Node> checkout) {
        List<List<Node>> allPermutations = new ArrayList<>();
        permute(new ArrayList<>(points), 0, allPermutations);
        List<Node> bestRoute = null;
        int minDistance = Integer.MAX_VALUE;
        for (List<Node> perm : allPermutations) {
            perm.add(0,productMap.get("EN"));
            perm.add(productMap.get("EX"));
            int currentDistance = getRouteLength(matrix, perm );
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                bestRoute = perm;
            }
        }
        // choosing best checkout
        int mindist = Integer.MAX_VALUE;
        List<Node> bestCheckoutRoute = null;
        for (Node casa: checkout){
            bestRoute.add(bestRoute.size()-1,casa);
            int d = getRouteLength(matrix,bestRoute);
            if (mindist>d){
                mindist = d;
                bestCheckoutRoute = new ArrayList<>(bestRoute) ;
            }
            bestRoute.remove(bestRoute.size()-2);
        }
        bestRoute = bestCheckoutRoute;
        return bestRoute;
    }

    // Method to generate all permutations of a list
    public static void permute(List<Node> arr, int k, List<List<Node>> allPermutations) {
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            permute(arr, k + 1, allPermutations);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            // check length and add list only if length is lower than min
            allPermutations.add(new ArrayList<>(arr));
        }
    }

    // Method to calculate the length of a route
    public static int getRouteLength(int[][] space, List<Node> arr){
        int dist = 0;
        for (int i=0; i<arr.size()-1; i++){
            //System.out.println(i + " -" + dist(space, arr.get(i), arr.get(i+1)));
            dist += dist(space, arr.get(i), arr.get(i+1));
        }
        return dist;
    }


    // Method to find the nearest neighbor route
    public static List<Node> NearestNeighbor(List<Node> points, int[][] matrix, List<Node> checkout, Node entrance, Node exit ) {
        List<Node> route = new ArrayList<>();
        Set<Node> unvisited = new HashSet<>(points);
        Node current = entrance;
        route.add(current);
        //unvisited.remove(current);
        while (!unvisited.isEmpty()) {
            Node nearest = null;
            int shortestDistance = Integer.MAX_VALUE;
            for (Node p : unvisited) {
                int distance = dist(matrix, current, p);
                if (distance < shortestDistance) {
                    nearest = p;
                    shortestDistance = distance;
                }
            }
            route.add(nearest);
            unvisited.remove(nearest);
            current = nearest;
        }
        int mindist = Integer.MAX_VALUE;
        Node casaFinal = null;
        for (Node casa: checkout){
            int d = dist(matrix, route.get(route.size()-1), casa);
            if (mindist>d) {
                mindist = d;
                casaFinal = casa;
            }
        }
        route.add(casaFinal);
        route.add(exit);
        return route;
    }


    // Method to get the route from start to end node
    public static List<Node> getRoute(int[][] s, Node start, Node end, HashMap<String, Node> productMap,List<String> goldenEggs){

        Queue<Node> q = new LinkedList<>();
        List<List<Node>> routes = new ArrayList<>();
        List<Node> bestRoute = null;
        boolean[][] visited = new boolean[s.length][s[0].length];
        int cur_dist = 0;
        q.offer(start);
        visited[start.x][start.y] = true;
        while (routes.isEmpty()){
            int size =  q.size();
            for(int j = 0; j < size; j++){
                Node current = q.poll();

                for (int i = 0; i < 8; i++) {
                    int newX = current.x + dir_x[i];
                    int newY = current.y + dir_y[i];
                    if (newX == end.x && newY == end.y) {
                        // add route to the list of routes for choosing best one
                        Node lastNode = new Node(newX,newY);
                        lastNode.previous = current;
                        ArrayList<Node> currentRoute = new ArrayList<>();
                        currentRoute.add(lastNode);
                        while(lastNode.previous != null){
                            currentRoute.add(0,lastNode.previous);
                            lastNode = lastNode.previous;
                        }
                        routes.add(currentRoute);
                        continue;
                    }
                    if (isValid(newX, newY, s, visited) && (s[newX][newY]==0)) {
                        Node nextNode = new Node(newX,newY);
                        nextNode.previous = current;
                        q.offer(nextNode);
                        visited[newX][newY] = true;
                    }

                }

            }
            cur_dist++;
        }
        // getting best route from the list
        int maxNumOfEggs = 0;
        int minChangeDirections = Integer.MAX_VALUE;
        for (List<Node> r: routes){
            int numEggs = countGoldenEggs(r,productMap,goldenEggs);
            int changes = countDirectionChanges(r);

            if (numEggs>maxNumOfEggs){
                bestRoute = r;
                maxNumOfEggs = numEggs;
                minChangeDirections = changes;
            }
            if (numEggs>=maxNumOfEggs && changes<minChangeDirections){
                bestRoute = r;
                minChangeDirections = changes;
            }
        }
        return bestRoute;
    }

     /**
     * This method generates the overall route by combining the shortest paths between consecutive points.
     *
     * @param points      A list of points to visit
     * @param space       A 2D space representing the environment
     * @param productMap  A map of product names to their corresponding nodes
     * @param goldenEggs  A list of golden eggs to collect
     * @return            The overall route as a list of nodes
     */

    public static List<Node> getallRoute(List<Node> points, int[][] space,  HashMap<String,Node> productMap, List<String> goldenEggs){
        List<Node> allRoute = new ArrayList<>();
        for (int i = 1; i<points.size(); i++){
            // Get the shortest path between the current point and the next point
            allRoute.addAll(getRoute(space,points.get(i-1), points.get(i),productMap,goldenEggs));
            // Remove the last node of the current path to avoid duplicates
            if (i<points.size()-1) allRoute.remove(allRoute.size()-1);
        }

        return allRoute;
    }


    /**
     * This method counts the number of golden eggs collected along a given path.
     *
     * @param path       The path to check for golden eggs
     * @param nodeMap    A map of product names to their corresponding nodes
     * @param goldenEggs A list of golden eggs to collect
     * @return           The number of golden eggs collected
     */

    public static int countGoldenEggs(List<Node> path, HashMap<String, Node> nodeMap, List<String> goldenEggs) {
        int count = 0;
        for (Node node : path) {
            // Check if the current node corresponds to a golden egg
            if (nodeMap.entrySet().stream().anyMatch(entry -> goldenEggs.contains(entry.getKey()) && entry.getValue().equals(node))) {
                count++;
            }
        }
        return count;
    }


    /**
     * This method counts the number of direction changes along a given path.
     *
     * @param path  The path to check for direction changes
     * @return      The number of direction changes
     */

    public static int countDirectionChanges(List<Node> path) {
        if (path.size() < 3) return 0;

        int directionChanges = 0;
        for (int i = 2; i < path.size(); i++) {
            Node a = path.get(i - 2);
            Node b = path.get(i - 1);
            Node c = path.get(i);

            int dx1 = b.x - a.x;
            int dy1 = b.y - a.y;
            int dx2 = c.x - b.x;
            int dy2 = c.y - b.y;

            // Check if the direction has changed between the current and previous segments
            if (dx1 != dx2 || dy1 != dy2) {
                directionChanges++;
            }
        }

        return directionChanges;
    }

}