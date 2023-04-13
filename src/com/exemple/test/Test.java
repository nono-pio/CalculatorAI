package com.exemple.test;

import java.util.ArrayList;
import java.util.Arrays;
import com.exemple.math.ParentClass.Element;
import com.exemple.math.elements.Addition;
import com.exemple.math.elements.Product;
import com.exemple.math.numbers.*;
import com.exemple.math.numbers.Number;

public class Test {

	public static void main(String[] args) {
		
		Element[] adds = new Element[] {
				new Variable("a"),
				new Variable("b"),
				new Variable("c")
		};
		
		//int n = 3;
		//float coef = 1;
		//for (int k = 0; k <= n; k++) {
			
		//	System.out.print(coef + " * ");
		//	System.out.print(adds[0] + "^" + k + " * ");
		//	System.out.println(adds[1] + "^" + (n - k) + " + " );
			
		//	coef *= (n - k)/ (float) (k + 1);
		//}
		
		int n = 2;
		
		Element[] add = new Element[n];
		for (int i = 0; i < n; i++) {
			add[i] = new Addition(adds);
		}
		System.out.println(new Product(add).simplify());
		
		ArrayList<ArrayList<Integer>> k = getCoefs(n, n);
		System.out.println(k);
		for (ArrayList<Integer> arrayList : k) {
			int pro = 1;
			for (Integer i : arrayList) {
				pro *= fac(i);
			}
			System.out.print(fac(n) / pro + " ");
			System.out.println();
		}
	}
	
	public static int fac(int i)
	{
		if (i == 0 || i == 1) return 1;
		else return i * fac(i-1);
	}
	
	public static ArrayList<ArrayList<Integer>> getCoefs(int n, int max)
	{
		ArrayList<ArrayList<Integer>> coefs = new ArrayList<ArrayList<Integer>>();
		if (max >= n)
		{
			ArrayList<Integer> coef1 = new ArrayList<Integer>();
			coef1.add(n);
			coefs.add(coef1);
		}
		
		int start = max < n? max : n - 1; 
		for (int i = start; i > 0; i--) {
			int dif = n - i;
			ArrayList<ArrayList<Integer>> subCoefs = getCoefs(dif, i);
			for (ArrayList<Integer> arrayList : subCoefs) {
				arrayList.add(0, i);
			}
			coefs.addAll(subCoefs);
		}
		
		return coefs;
	}
	
}
