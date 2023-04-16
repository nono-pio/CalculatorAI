package com.exemple.test;

import com.exemple.math.elements.Addition;
import com.exemple.math.math.AdditionExtention;
import com.exemple.math.numbers.*;

public class Test {

	public static void main(String[] args) {
		
		Addition[] adds = new Addition[] {
				new Addition(
						new Variable("a"), new Variable("b")
						),
				new Addition(
						new Variable("a"), new Variable("a")
						)
		};
		
		
		System.out.println(AdditionExtention.Product(adds, null));
	}
}
