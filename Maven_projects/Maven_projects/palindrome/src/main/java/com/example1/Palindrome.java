package com.example1;
import java.util.*;
/**
 * Hello world!
 *
 */
public class Palindrome 
{
    public static void main( String[] args )
    {
     Scanner sc=new Scanner(System.in);
     System.out.print("Enter the String of palindrome");
     String s=sc.nextLine();
     StringBuilder sb=new StringBuilder(s);
     System.out.println(sb.reverse().toString().equals(s));

    }
}
