package com.exemple.math.forms;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.elements.Power;
import com.exemple.math.elements.Product;
import com.exemple.math.numbers.Number;

public class Monome {

	Element coef;
	Element variable;
	int degree;
	
	public Monome(Element coef, Element variable, int degree) {
		this.coef = coef;
		this.variable = variable;
		this.degree = degree;
	}
	
	public Element toElement()
	{
		return new Product(coef, new Power(variable, new Number(degree)));
	}

	@Override
	public String toString() {
		return coef + "*" + variable + "^" + degree;
	}
}
