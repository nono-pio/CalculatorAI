package com.exemple.main;

import java.util.Arrays;

import com.exemple.latex.*;
import com.exemple.math.ParentClass.*;
import com.exemple.math.ParentsElements.AritmeticSequence;
import com.exemple.math.ParentsElements.Equation;
import com.exemple.math.elements.*;
import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;
import com.exemple.math.tools.MathN;

public class Main {

	public static void main(String[] args) {
		
		Element elem2 = new Product(new Element[] {
				new Variable("x"),
				new Addition(new Variable("a_n"), new Variable("b_5")),
				new Addition(new Variable("a_n"), new Variable("b_5")),
				new Number(6),
		});
		
		Element elem = new Power(
				new Power(new Addition(new Variable("a"), new Variable("b")), new Number(6)),
				new Number(2)
				);
		
		System.out.println(elem);
		System.out.println(elem.simplify());
		//System.out.println(addSim);
		//System.out.println("x = " + x + " = " + x.toValue());
		System.out.println();
		elem.simplify().forEach((e, p) -> System.out.println(e.getType() + " : " + e));

		//LaTex latex = new LaTex(elem.toLaTeX() + " = \\\\" + elem.simplify().toLaTeX());
		
		//new Frame("Title", latex, 1000, 500, 50);
	}
}
