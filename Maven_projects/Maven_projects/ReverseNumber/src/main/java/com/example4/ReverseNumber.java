package com.example4;
import java.util.*;
/**
 * Hello world!
 *
 */
public class ReverseNumber {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        int reversed = 0;
        
        while (number != 0) {
            int digit = number % 10;   
            reversed = reversed * 10 + digit; 
            number = number / 10; 
        }

        System.out.println("Reversed number: " + reversed);
        sc.close();
    }
}
