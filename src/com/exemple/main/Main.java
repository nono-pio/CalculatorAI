package com.exemple.main;

import java.util.Arrays;

import com.exemple.latex.*;
import com.exemple.math.ParentClass.*;
import com.exemple.math.ParentsElements.AritmeticSequence;
import com.exemple.math.ParentsElements.Equation;
import com.exemple.math.elements.*;
import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;

public class Main {

	public static void main(String[] args) {
		
		Addition add = new Addition(new Element[] {
				new Product(new Variable("g"), new Variable("x")),
				new Product(new Variable("x"), new Variable("g")),
				new Number(10)
				});

		//Element addSim = add.simplify();
		
		//System.out.println(add);
		//System.out.println(addSim);
		//System.out.println("x = " + x + " = " + x.toValue());
		//System.out.println();
		//add.simplify().forEach((e, p) -> System.out.println(e.getType() + " : " + e));

		//LaTex latex = new LaTex(pro.toLaTeX() +" = \\\\"+pro.developing().toLaTeX());
		
		//new Frame("Title", latex, 1000, 500, 50);
	}
}
