package com.exemple.math.math;

import java.util.ArrayList;
import java.util.Arrays;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.elements.Addition;
import com.exemple.math.elements.Power;
import com.exemple.math.elements.Product;
import com.exemple.math.numbers.Number;

public final class AdditionExtention {

	
	public static Addition Power(Addition add, int n) { // multinome by Newton
		
		Element[] elemAdd = add.getValues();
		ArrayList<int[]> kVector = getCouples(n, elemAdd.length);
		
		int[] facArray = Factorial.factorialArray(n);
		
		Element[] newElemAdd = new Element[kVector.size()];
		for (int i = 0; i < newElemAdd.length; i++) {
			int[] k = kVector.get(i);
			ArrayList<Element> product = new ArrayList<>();
			
			Number coef = new Number(Factorial.multinome(n, k, facArray));
			if (!coef.isEqual(new Number(1))) product.add(coef);
			
			for (int j = 0; j < k.length; j++) {
				if (k[j] == 0) continue;
				else if (k[j] == 1) product.add(elemAdd[j]);
				else product.add(new Power(elemAdd[j], new Number(k[j])));
			}
			
			if (product.size() == 1) newElemAdd[i] = product.get(0);
			else newElemAdd[i] = new Product(product.toArray(new Element[product.size()]));
		}
		
		return new Addition(newElemAdd);
	}
	
	private static ArrayList<int[]> getCouples(int n, int length)
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
