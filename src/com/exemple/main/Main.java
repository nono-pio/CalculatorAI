package com.exemple.main;

import com.exemple.latex.*;
import com.exemple.math.ParentClass.*;
import com.exemple.math.ParentsElements.AritmeticSequence;
import com.exemple.math.ParentsElements.Equation;
import com.exemple.math.elements.*;
import com.exemple.math.math.MathN;
import com.exemple.math.numbers.GlobalVariable;
import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;

public class Main {

	public static void main(String[] args) {

		
		System.out.println( new Product(x, y));
		System.out.println( new Product(y, x));
		
		
		LaTex latex = new LaTex(pythagore.toLaTeX());
		
		new Frame("Title", latex, 1000, 500, 50);
	}
	
	
	static Variable a = new Variable("a");
	static Variable b = new Variable("b");
	static Variable c = new Variable("c");
	static Variable x = new Variable("x");
	static Variable y = new Variable("y");
	static Variable z = new Variable("z");
	
	static Number n0 = new Number(0);
	static Number n1 = new Number(1);
	static Number n2 = new Number(2);
	static Number n5 = new Number(5);
	static Number n10 = new Number(10);
	static Number n20 = new Number(20);
	static Number n50 = new Number(50);
	
	static Element pythagoreP1 = new Power(a, n2);
	static Element pythagoreP2 = new Addition(new Power(b, n2), new Power(c, n2));
	
	static Equation pythagore = new Equation(pythagoreP1, pythagoreP2);
}
