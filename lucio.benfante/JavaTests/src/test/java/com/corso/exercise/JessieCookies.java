package com.corso.exercise;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class JessieCookies {

    /*
     * Complete the cookies function below.
     */
    static int cookiesSi(int k, int[] A) {
        Queue<Integer> cookies =
            new PriorityQueue<Integer>(A.length);
        for (int a: A) {
            cookies.add(a);
        }
        int result = 0;
        while (true) {
            Integer c1 = cookies.poll();
            if (c1 != null && c1 >= k) {
                break;
            }
            Integer c2 = cookies.poll();
            if (c2 == null) {
                result = -1;
                break;
            }
            cookies.add(c1 + 2 * c2);
            result++;
        }
        return result;
    }

    static int cookies2(int k, int[] A) {
        if (A.length == 0) return -1;
        int result = 0;
        while (result < A.length - 1 && A[result] < k) {
            int newCookie = A[result] + 2 * A[result + 1];
            // "insert sort"
            int i = result + 1;
            for (; i < A.length - 1; i++) {
                if (newCookie > A[i+1]) {
                    A[i] = A[i+1];
                } else {
                    A[i] = newCookie;
                    break;
                }
            }
            if (i == A.length-1) A[i] = newCookie;
            result++;
        }
        if (A[result] < k) return -1;
        return result;
    }
    
    static int cookies(int k, int[] A) {
        if (A.length == 0) return -1;
        Arrays.sort(A);
        int result = 0;
        while (result < A.length - 1 && A[result] < k) {
            int newCookie = A[result] + 2 * A[result + 1];
            // "insert sort"
            int i = ++result;
            while (i < A.length - 1) {
            	int nextValue = A[i+1];
                if (newCookie > nextValue) {
                    A[i++] = nextValue;
                } else {
                    break;
                }
            }
            A[i] = newCookie;
        }
        if (A[result] < k) return -1;
        return result;
    }
    
    static int cookiesSi2(int k, int[] A) {
        if (A.length == 0) return -1;
        Arrays.sort(A);
        int result = 0;
        int[] newCookies = new int[A.length];
        int startA = 0;
        int aSize = A.length;
        int startNewCookies = 0;
        int newCookiesSize = 0;
        int currMinSweetness = A[0];
        while(aSize+newCookiesSize > 1 && currMinSweetness < k ) {
            result++;
            int sweetness1 = 0;
            if (newCookiesSize == 0) {
                sweetness1 = A[startA++];      
                aSize--;
            } else if (aSize == 0) {
                sweetness1 = newCookies[startNewCookies++];      
                newCookiesSize--;
            } else {
                if (A[startA] <= newCookies[startNewCookies]) {
                    sweetness1 = A[startA++];
                    aSize--;
                } else {
                    sweetness1 = newCookies[startNewCookies++];
                    newCookiesSize--;
                }
            }
            int sweetness2 = 0;
            if (newCookiesSize == 0) {
                sweetness2 = A[startA++];
                aSize--;
            } else if (aSize == 0) {
                sweetness2 = newCookies[startNewCookies++];
                newCookiesSize--;
            } else {
                if (A[startA] <= newCookies[startNewCookies]) {
                    sweetness2 = A[startA++];
                    aSize--;
                } else {
                    sweetness2 = newCookies[startNewCookies++];
                    newCookiesSize--;
                }
            }
            int newSweetness = sweetness1 + (2*sweetness2);
            newCookies[startNewCookies+newCookiesSize] = newSweetness;
            newCookiesSize++;
            if (newCookiesSize == 0) {
                currMinSweetness = A[startA];
            } else if (aSize == 0) {
                currMinSweetness = newCookies[startNewCookies];
            } else {
                currMinSweetness = Math.min(A[startA], newCookies[startNewCookies]);
            }
        }
        if (currMinSweetness < k) return - 1;
        return result;
    }
    
    public static void main(String[] args) throws IOException {
    	int result = cookies(90, new int[] {13, 47, 74, 12, 89, 74, 18, 38});
    	System.out.println(result);
    }
}
