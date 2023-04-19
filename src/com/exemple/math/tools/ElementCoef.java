package com.exemple.math.tools;

import java.util.ArrayList;
import java.util.Arrays;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.elements.Power;
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
	
	public Element getElementProduct(int index)
	{
		Number coef = coefs.get(index);
		Element element = elements.get(index);
		
		if (coef.isEqual(new Number(1))) return element.clonedSimplify();
		if (element.getType() == ElementType.Product)
		{
			Element[] newValues = Tools.addToArray(element.getValues(), coef);
			return new Product(newValues).clonedSimplify();
		}
		return new Product(coef, element).clonedSimplify();
	}
	
	public Element getElementPower(int index)
	{
		if (coefs.get(index).isEqual(new Number(1)))
			return elements.get(index).clonedSimplify();
		return new Power(elements.get(index), coefs.get(index)).clonedSimplify();
	}
	
	public ArrayList<Element> getElementsProduct()
	{
		ArrayList<Element> newElements = new ArrayList<Element>();
		for (int i = 0; i < elements.size(); i++) {
			Element elem = getElementProduct(i);
			
			if (elem.getType() == ElementType.Number && ((Number) elem).isZero()) continue;
				
			newElements.add(elem);
		}
		return newElements;
	}

	public ArrayList<Element> getElementsPower()
	{
		ArrayList<Element> newElements = new ArrayList<Element>();
		for (int i = 0; i < elements.size(); i++) {
			Element elem = getElementProduct(i);
			
			if (elem.getType() == ElementType.Number && ((Number) elem).isEqual(new Number(1))) continue;
				
			newElements.add(elem);
		}
		return newElements;
	}
}
