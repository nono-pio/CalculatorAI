package com.exemple.math.elements;

import java.util.Arrays;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.math.MathN;
import com.exemple.math.numbers.Number;
import com.exemple.math.tools.Tools;

public class Polynome extends Element {

	
	Element[] coefs;
	Element variable;
	
	public Polynome(Element variable, Element[] coefs) {
		this.coefs = coefs;
		this.variable = variable;
	}
	
	public ElementType getType() { return ElementType.Polynome; }
	public Number toValue() {
		Number result = new Number(0);
		Number varN = variable.toValue();
		for (int i = 0; i < coefs.length; i++) {
			result.add( MathN.mult(coefs[i].toValue(), MathN.pow(varN, new Number(coefs.length - i))));
		}
		return result;
	}

	@Override
	public Element recipFunction(int[] path, Element curRecip) {
		// TODO Auto-generated method stub
		return null;
	}
	public Element[] getValues() {
		Element[] values = new Element[coefs.length + 1];
		values[0] = variable;
		System.arraycopy(coefs, 0, values, 1, coefs.length);
		return values;
	}
	public void setValues(Element[] values) {
		variable = values[0];
		coefs = Arrays.copyOfRange(values, 1, values.length - 1);
	}
	public String toString(ElementType parentType, boolean isLaTeX) {
		StringBuilder str = new StringBuilder();
		return null;
	}
	public Element clone() { return new Polynome(variable.clone(), Tools.cloneElementArray(coefs)); }
	public Element clonedSimplify() {
		// TODO Auto-generated method stub
		return null;
	}

}
