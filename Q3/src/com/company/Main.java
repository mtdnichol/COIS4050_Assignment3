package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double[][] rates = new double[2][3];

        //i = start year, j = end year
//        rates[0][1] = .1;
//        rates[1][2] = .2;
//        rates[0][2] = .12;

        rates = new double[][]{{0.00, 0.01, 0.04, 0.01},{0.00, 0.2, 0.3, 0.3},{0.00, .9, .9, .9}};

        int maxStartYear = rates.length;
        int maxEndYear = rates[0].length;

        //Holds the results of the best interest rate achievable for a given year i
        double[] bestRates = new double[maxEndYear];

        // Algorithm
        //      - Calculate best interest rate on a given year by assessing the current term, going back and referencing
        //        the current term against the product of previous terms within the current terms range
        //      - Add the subsequent result to the results array at the given year
        for (int i = 0; i < maxStartYear; i++) {
            for (int j = 0; j < maxEndYear; j++) {
                if (i >= j) //End year cannot be greater than or equal to start year
                    continue;

                System.out.println(i + "->" + j + ": " + rates[i][j]); //Debug purposes

                double predefinedRate = rates[i][j]; //Predefined rate in initial array

                if (bestRates[j - 1] < predefinedRate) { //Check if the rate outlined in best rates array is better than the predefined rate
                    bestRates[j - 1] = predefinedRate; //If worse, insert predefined rate
                }

                double calculatedRate = 1;

                for (int k = 0; k < j; k++) { //Calculate the rate for the given year provided the history of best rates
                    calculatedRate *= bestRates[k];
                }

                if (calculatedRate > predefinedRate) { //If the calculated rate is better than the predefined, insert the calculated rate
                    bestRates[j - 1] = calculatedRate;
                }

                printArr(bestRates); //Debug purposes
            }
        }

        Scanner reader = new Scanner(System.in);

        //User testing
        while (true) {
            System.out.println("Minimum investment term is 1 year, and can be up to " + (maxEndYear - 1) + " years.");
            System.out.print("How many years will you hold your investments?: ");
            int userTerm = reader.nextInt();

            if (userTerm < 1 || userTerm > (maxEndYear - 1)) { //Ensures the user enters a year within the expected range
                System.out.println("\nYou entered a value that is out of the expected range");
            }
            System.out.println("\n----Optimal Solution----");
            calculateRate(bestRates, userTerm); //Function that calculates and outputs the optimal solution

            System.out.println("\n");
        }
    }

    public static void calculateRate (double[] rates, int term) {
        double initialInvestment = 1; //Mach initial investment

        for (int i = 0; i < term; i++) {
            System.out.println("Year " + (i+1) + " rate: " + rates[i]);
            initialInvestment *= (1 + rates[i]);
        }

        System.out.println("Your investment has turned into " + initialInvestment);
        System.out.println("This is an increase of " + ((initialInvestment - 1)/1)*100 + "%");
    }

    //Function to output array contents for debug purposes
    public static void printArr(double[] arr) {
        for (double i : arr)
            System.out.print(i + " ");

        System.out.println("\n");
    }
}
