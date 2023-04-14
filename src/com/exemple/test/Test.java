package com.exemple.test;

import java.util.ArrayList;
import java.util.Arrays;
import com.exemple.math.ParentClass.Element;
import com.exemple.math.elements.Addition;
import com.exemple.math.elements.Product;
import com.exemple.math.math.AdditionExtention;
import com.exemple.math.math.Factorial;
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
		int m = adds.length;
		
		for (int i = n; i > 0; i--) {
			int[] couple = new int[m];
			couple[0] = i;
			//System.out.println(Arrays.toString(couple));
		}
		
		ArrayList<int[]> kVector = getCouples(n, m);
		kVector.forEach((v) -> System.out.print(Arrays.toString(v)));
		System.out.println();
		
		int[] facArray = Factorial.factorialArray(n);
		for (int[] k : kVector) {
			System.out.print(Factorial.multinome(n, k, facArray) + " ");
			for (int i = 0; i < k.length; i++) {
				System.out.print(" * " + adds[i] + " ^ " + k[i]);
			}
			System.out.println(" + ");
		}
		
		System.out.println(AdditionExtention.Power(new Addition(adds), n));
	}
	
	public static ArrayList<int[]> getCouples(int n, int length)
	{
		ArrayList<int[]> couples = new ArrayList<>();
		if (n == 0)
		{
			couples.add(new int[length]);
			return couples;
		} else if (length == 1)
		{
			couples.add(new int[] {n});
			return couples;
		}
		for (int i = 0; i <= n; i++) {
			ArrayList<int[]> subCouples = getCouples(n - i, length - 1);
			for (int[] subCouple : subCouples) {
				int[] newSubCouple = Arrays.copyOf(subCouple, subCouple.length + 1);
				newSubCouple[newSubCouple.length - 1] = i;
				couples.add(newSubCouple);
			}
		}
		return couples;
	}
}
