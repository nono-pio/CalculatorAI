package com.exemple.math.forms;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.exemple.math.numbers.Number;
import com.exemple.math.tools.StringFormat;
import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.ParentClass.Form;
import com.exemple.math.elements.Addition;

public class Polynome extends Form {

	List<Monome> monomes;
	int degree;
	
	public Polynome(Element variable, Element[] values)
	{
		monomes = new ArrayList<Monome>();
		degree = values.length - 1;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null && !values[i].isEqual(Number.zero))
			{
				monomes.add( new Monome(values[i], variable, degree - i) );
			}
		}
	}
	
	public Element toElement() {
		return new Addition(monomes.stream().map(m -> m.toElement()).toList());
	}

	@Override
	public Element clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(ElementType parentType, boolean isLaTeX) {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder(monomes.get(0).toString());
		for (int i = 1; i < monomes.size(); i++) {
			str.append(" + " + monomes.get(i));
		}
		return str.toString();
	}
}
