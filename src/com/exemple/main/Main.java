package com.exemple.main;

import com.exemple.latex.*;
import com.exemple.math.ParentClass.*;
import com.exemple.math.elements.*;
import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;

public class Main {

	public static void main(String[] args) {
		
		
		//Element div = new Division(new Addition(new Number(9), new Number(5)), new Product(new Variable("x"), new Number(1)));
		
		Product pro = new Product(new Addition(new Number(5), new Variable("x")), new Addition(new Number(2), new Variable("x")), new Variable("x"));
		System.out.println(pro);
		System.out.println(pro.developing());
		//System.out.println(div);
		LaTex latex = new LaTex(pro.toLaTeX() +" = "+pro.developing().toLaTeX());
		
		new Frame("Title", latex, 600, 500, 50);
	}
}
