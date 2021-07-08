/*
* Michael Nichol
* SID: 0624004
*
* Question 1 for COIS4050 Assignment 1
*/

package com.company;

public class Main {
    public static void main(String[] args) {
        int[] price = {10, 12, 3, 6, 5, 11, 7};
        price = new int[]{7,6,5,4,10};
        price = new int[]{3,2,1};
        int[] change = new int[price.length];

        //To lower time complexity, transform price array into a change array
        //where each index represents the change in value from the previous day
        //to current day
        for (int i = 1; i <= price.length - 1; i++) {
            change[i] = price[i] - price[i - 1];
        }

        System.out.println("Price Array:");
        printArr(price);

        System.out.println("Change Array:");
        printArr(change);


        int sum = 0, currSum = 0; //sum and currSum represent the most profitable and currently observed sub-array, respectively
        int startDate = 0, endDate = 0; //startDate and endDate represent the indexes of the most profitable sub-array

        //Calculate the longest maximum subarray in O(n)
        for (int i = 0; i < change.length; i++) { //Iterate over the change array
            if (currSum + change[i] > 0) { //Check if the current sum plus the current index yields a negative value, making it unprofitable
                currSum = currSum + change[i]; //If still profitable, add to current sum
                endDate = i; //Change end date to current date
            } else { //If unprofitable, reset all values
                currSum = 0;
                startDate = i;
                endDate = i;
            }

            if (sum < currSum) //Update most profitable sum if less than current sum
                sum = currSum;
        }

        if (startDate == endDate) { //If the start and end dates are the same, there is no profitability in the array
            System.out.println("Over the given investment term, there was no way found to make money.");
        } else { //Otherwise, output all relevant information
            System.out.println("Investment strategy successful.");
            System.out.println("Maximum profit: " + sum);
            System.out.println("Start Term Position: " + startDate);
            System.out.println("End Term Position: " + endDate);
            System.out.println("\nVerify result: " + verifyResult(price, startDate, endDate)); //Verify the results using indexes on the price array
        }
    }

    //Function to output array contents for debug purposes
    public static void printArr(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");

        System.out.println("\n");
    }

    //Function to verify a result, given a start and end date
    public static int verifyResult(int[] arr, int startDate, int endDate) {
        return arr[endDate] - arr[startDate];
    }
}
