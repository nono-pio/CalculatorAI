package com.exemple.math.elements;

import java.util.ArrayList;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.numbers.Number;
import com.exemple.math.tools.StringFormat;
import com.exemple.math.tools.Tools;


public class Product extends Element{

    public Element[] values;

    public Product(Element[] values) { this.values = values; }
    public Product(Element value1, Element value2) { this.values = new Element[]{ value1, value2 }; }
    public Product(Element value1, Element value2, Element value3) { this.values = new Element[]{ value1, value2, value3 }; }


    public ElementType getType() { return ElementType.Product; }
    public Number toValue() {
        Number prod = new Number(1);
        for (Element value : values) {
            prod.mult(value.toValue());
        }
        return prod;
    }
    public Element[] getValues() { return values; }
    public Element recipFunction(int[] path, Element curRecip) {
        
        Element[] div = new Element[values.length - 1];
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (i != path[0])
            {
                div[index] = values[i];
                index++;
            }
        }

        return values[path[0]].recipFunction(newPath(path), new Division(curRecip, new Product(div)));
    }
    public Element simplify() {
        Number cste = new Number(1);
        ArrayList<Element> rest = new ArrayList<Element>();
        for (Element child : values) {
            Element childSim = child.simplify();
            if ( childSim.getType() == ElementType.Number ) cste.mult((Number) childSim);
            else rest.add(childSim);
        }

        if (cste.isZero()) return new Number(0);
        if (rest.size() == 0) return cste;

        if (!cste.isEqual(new Number(1))) rest.add(cste);
        else if (rest.size() == 1) return rest.get(0);
        return new Product(rest.toArray(new Element[rest.size()]));
    }
    public Element developing() {
        ArrayList<Element> notAdd = new ArrayList<Element>();
        ArrayList<Element[]> addChildElement = new ArrayList<Element[]>();
        for (Element elem : values) {
			if (elem.getType() == ElementType.Addition)
				addChildElement.add(elem.getValues());
			else
				notAdd.add(elem);
		}
        
        ArrayList<Element[]> couples = Tools.getCouples(addChildElement, notAdd);
        ArrayList<Element> addition = new ArrayList<Element>();
        for (Element[] pro : couples) {
        	addition.add(new Product(pro).simplify());
		}
        return new Addition(addition.toArray(new Element[addition.size()]));
    }
    public Number getCst()
    {
    	for (Element element : values) {
			if (element.getType() == ElementType.Number) return (Number) element;
		}
    	return new Number(1);
    }
    public Element getRest()
    {
    	ArrayList<Element> rest = new ArrayList<Element>();
    	for (Element element : values) {
			if (element.getType() != ElementType.Number) rest.add(element);
		}
    	if (rest.size() == 1) return rest.get(0);
    	return new Product(rest.toArray(new Element[rest.size()]));
    }
	public void setValues(Element[] values) { this.values = values; }
	public String toString(ElementType parentType, boolean isLaTeX) {
		
		String str = StringFormat.arrayStr(values, isLaTeX? " \\cdot " : " * ", getType(), isLaTeX);
		
		if (parentType == null || parentType == ElementType.Addition) return str;
		else return StringFormat.bracket(str, isLaTeX);
	}
	public Element clone() { return new Product(Tools.cloneElementArray(values)); }
	public Element clonedSimplify() { return this; }
    
}
