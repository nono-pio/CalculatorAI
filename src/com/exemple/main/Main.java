package com.exemple.main;

import java.util.Arrays;

import com.exemple.latex.*;
import com.exemple.math.ParentClass.*;
import com.exemple.math.ParentsElements.AritmeticSequence;
import com.exemple.math.ParentsElements.Equation;
import com.exemple.math.elements.*;
import com.exemple.math.math.MathN;
import com.exemple.math.numbers.GlobalVariable;
import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;
import com.exemple.math.string_converter.StringConverter;

public class Main {

	public static void main(String[] args) {

		StringConverter str = new StringConverter(" a^2 + b*2 ");
		Element element = str.toElement();
		
		print(str + " = " + element);
		//element.forEach((e, p) -> print(e.getType()));
		
		//LaTex latex = new LaTex(pythagore.toLaTeX());
		
		//new Frame("Title", latex, 1000, 500, 50);
	}
	
	static <T> void print(T str) { System.out.println(str.toString()); }
	static void print(String str) { System.out.println(str); }
	
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
