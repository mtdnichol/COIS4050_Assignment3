package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] price = {10, 12, 3, 6, 5, 11, 7, 3};
        price = new int[]{5,4,3,2,1};
        price = new int[]{7,6,5,4,10};
        price = new int[]{5,4,3,2};
        price = new int[]{10,9,2,5,3,7,101,18,1};

        System.out.println("Price Array:");
        printArr(price);

        List<List<Integer>> resultsArray = new ArrayList<>(); //List of lists that stores peak indexes for each price till the start of the list

        for (int i = price.length - 1; i >= 0; i--) { //Iterate over the price list from P(n) ... P(1)
            List<Integer> currentResult = new ArrayList<>(); //Create a new results list for each index
            currentResult.add(i); //Add the current index to the results list
            int currVal = price[i]; //Set the current peak to the value of the current index

            for (int j = i; j >= 0; j--) { //Iterate from current index i, to start of the list
                if (price[j] < currVal) { //Check if the price is less than the current peak, if so, replace
                    currentResult.add(j); //Add the index to the current results list
                    currVal = price[j]; //Set current peak to new, smaller value
                }
            }

            resultsArray.add(currentResult); //Add the current results list to the list of results
        }

        int currentIndex = 0, bestLength = 0, bestIndex = 0; //Find the list that contains the longest sub-array
        for (List<Integer> list : resultsArray) {
            int currentLength = list.get(0) - list.get(list.size() - 1); //Calculate the length of the observed sub-array

            if (currentLength > bestLength) { //If current sub-array is longer than best, replace
                bestIndex = currentIndex;
                bestLength = currentLength;
            }

            currentIndex++; //Increment the index of the current list in resultsArray
        }

        if (bestLength < 1) {
            System.out.println("There is no increase in the given investment term.");
        } else {
            int startDate = resultsArray.get(bestIndex).get(resultsArray.get(bestIndex).size() - 1); //Start date obtained by end of list
            int endDate = resultsArray.get(bestIndex).get(0); //End date obtained by start of list

            System.out.println("Start date: " + startDate);
            System.out.println("End date: " + endDate);
            System.out.print("List of peak values: ");

            for (int i = resultsArray.get(bestIndex).size() - 1; i >=0; i--) { //Outputs the best list as prices
                System.out.print(price[resultsArray.get(bestIndex).get(i)] + ", ");
            }
        }
    }

    //Function to output array contents for debug purposes
    public static void printArr(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");

        System.out.println("\n");
    }
}
