package com.exemple.math.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.elements.Product;
import com.exemple.math.numbers.Number;

public class ElementCoef {
	
	public ArrayList<Element> elements;
	public ArrayList<Number> coefs;
	
	public ElementCoef()
	{
		elements = new ArrayList<>();
		coefs = new ArrayList<>();
	}
	
	public void add(Number coef, Element value)
	{
		int index = -1;
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).isEqual(value))
			{
				index = i;
				break;
			}
		}
		
		if (index == -1)
		{
			elements.add(value);
			coefs.add(coef);
		} else
			coefs.get(index).add(coef);
	}
	
	public int size()
	{
		return elements.size();
	}
	
	public Element getElement(int index)
	{
		if (elements.get(index).getType() == ElementType.Product)
		{
			Element[] values = elements.get(index).getValues();
			Element[] newValues = Arrays.copyOf(values, values.length + 1);
			newValues[newValues.length - 1] = coefs.get(index);
			return new Product(newValues).clonedSimplify();
		}
		return new Product(coefs.get(index), elements.get(index)).clonedSimplify();
	}
	
	public ArrayList<Element> getElements()
	{
		ArrayList<Element> newElements = new ArrayList<Element>();
		for (int i = 0; i < elements.size(); i++) {
			newElements.add(getElement(i));
		}
		return newElements;
	}

}
