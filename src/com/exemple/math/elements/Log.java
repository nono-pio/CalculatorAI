package com.exemple.math.elements;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.math.MathN;
import com.exemple.math.numbers.Number;
import com.exemple.math.tools.StringFormat;

public class Log extends Element {
	
	public Element base;
	public Element value;
	
	public Log(Element value) {
		this.base = MathN.E; 
		this.value = value;
	}
	public Log(Element base, Element value) {
		this.base = base;
		this.value = value;
	}

	public ElementType getType() { return ElementType.Log; }
	public Number toValue() { return MathN.log(base.toValue(), value.toValue()); }
	public Element recipFunction(int[] path, Element curRecip) {
		if (path[0] == 0)
		{
			Element newRecip = new Power(value, new Division(new Number(1), curRecip));
			return base.recipFunction(newPath(path), newRecip);
		} else
		{
			Element newRecip = new Power(base, curRecip);
			return value.recipFunction(newPath(path), newRecip);
		}
	}
	public Element[] getValues() { return new Element[] { base, value}; }
	public void setValues(Element[] values) {
		base = values[0];
		value = values[1];
	}
	public String toString(ElementType parentType, boolean isLaTeX) {
		String str;
		if (isLaTeX)
		{
			String strBase = "log_{\\small "+ base.toString(getType(), isLaTeX) +"}";
			str = strBase + "{" + StringFormat.bracket(value.toString(getType(), isLaTeX), isLaTeX) + "}";
		} else
			str = "log_" + StringFormat.bracket(base.toString(getType(), isLaTeX), isLaTeX) + StringFormat.bracket(value.toString(getType(), isLaTeX), isLaTeX);
		if (parentType == ElementType.Power)
			return StringFormat.bracket(str, isLaTeX);
		else return str;
	}
	public Element clone() { return new Log(base.clone(), value.clone()); }
	public Element clonedSimplify() { return this;
		
		
	}

}
