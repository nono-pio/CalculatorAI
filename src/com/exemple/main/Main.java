package com.exemple.main;

import com.exemple.latex.*;
import com.exemple.math.ParentClass.*;
import com.exemple.math.elements.*;
import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;

public class Main {

	public static void main(String[] args) {
		
		
		//Element div = new Division(new Addition(new Number(9), new Number(5)), new Product(new Variable("x"), new Number(1)));
		
		Element e1 = new Addition(new Number(1), new Number(2.99f));
		Element e2 = new Addition(new Number(1), new Number(3));
		System.out.print(e1.isEqual(e2));
		
		//System.out.println(div);
		//LaTex latex = new LaTex(div.toLaTeX() +" = "+div.simplify().toLaTeX());
		
		//new Frame("Title", latex, 600, 500, 100);
	}
}
