package com.exemple.main;

import com.exemple.latex.*;
import com.exemple.math.ParentClass.*;
import com.exemple.math.ParentsElements.AritmeticSequence;
import com.exemple.math.elements.*;
import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;

public class Main {

	public static void main(String[] args) {
		
		Addition add = new Addition(
				new Product(new Number(5), new Variable("x")),
				new Addition(new Number(9), new Variable("x")),
				new Number(6));
		
		System.out.println(add);
		System.out.println(add.simplify());

		//LaTex latex = new LaTex(pro.toLaTeX() +" = \\\\"+pro.developing().toLaTeX());
		
		//new Frame("Title", latex, 1000, 500, 50);
	}
}
