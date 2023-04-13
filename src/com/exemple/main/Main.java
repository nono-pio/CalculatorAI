package com.exemple.main;

import java.util.Arrays;

import com.exemple.latex.*;
import com.exemple.math.ParentClass.*;
import com.exemple.math.ParentsElements.AritmeticSequence;
import com.exemple.math.ParentsElements.Equation;
import com.exemple.math.elements.*;
import com.exemple.math.numbers.GlobalVariable;
import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;
import com.exemple.math.tools.MathN;

public class Main {

	public static void main(String[] args) {
		
		Element pyt = new Addition( new Element[] {
				new Power(new Variable("b"), new Number(2)),
				new Power(new Variable("c"), new Number(2)),
		});
		
		Equation pythagore = new Equation(new Power(new Variable("a"), new Number(2)), pyt);
		
		pythagore.setVariable("b", new Number(6));
		
		//System.out.println(GlobalVariable.globalVariables);
		System.out.println(pythagore.variables);
		
		//Element a = pythagore.getRecipFunc("a");
		
		System.out.println();
		System.out.println(pythagore);
		//System.out.println(a);
		//System.out.println(a.toValue());
		//System.out.println(addSim);
		//System.out.println("x = " + x + " = " + x.toValue());
		//System.out.println();
		//elem.simplify().forEach((e, p) -> System.out.println(e.getType() + " : " + e));

		LaTex latex = new LaTex(pythagore.toLaTeX());
		
		new Frame("Title", latex, 1000, 500, 50);
	}
}
