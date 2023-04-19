package com.exemple.math.string_converter;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ParentElement;
import com.exemple.math.ParentsElements.AritmeticSequence;
import com.exemple.math.ParentsElements.Equation;
import com.exemple.math.elements.Addition;
import com.exemple.math.elements.Division;
import com.exemple.math.elements.Power;
import com.exemple.math.elements.Product;
import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;
import com.exemple.math.tools.ErrorMessage;

public class StringConverter {

	public static final String numberPatern = "\\-?[0-9]+((\\.|,)[0-9]+)?";
	public static final String variablePatern = "[a-zA-Z]";
	
	public static ParentElement stringToElement(String string)
	{
		String newString = string.replace(" ", "");
		
		if (newString.contains("="))
		{
			Element[] values = getElements(newString, "=", 2);
			return new Equation(values[0], values[1]);
		}
		
		return new AritmeticSequence(convert(newString));
	}
	
	private static Element convert(String string) {
		
		if (string.isEmpty()) throw ErrorMessage.IndeterminedString(string); 
		
		//<--------------------- Operator --------------------------------------------------->
		if (string.charAt(0) == '-');
		
		else if (string.contains("+"))
			return new Addition(getElements(string, "\\+", 0));
		
		else if (string.contains("-"))
		{
			Element[] values = getElements(string, "\\-", 2);
			return new Addition(values[0], values[1], true);

		} else if (string.contains("*"))
			return new Product(getElements(string, "\\*", 0));
			
		else if (string.contains("/"))
		{
			Element[] values = getElements(string, "\\/", 0);
			return new Division(values[0], values[1]);
			
		} else if (string.contains("^"))
		{
			Element[] values = getElements(string, "\\^", 0);
			return new Power(values[0], values[1]);
		}
		//<-------------------------- Number / Variable ------------------------------->
		
		if (string.matches(numberPatern))
			return new Number(Float.valueOf(string.replace(",", ".")));
		
		else if (string.length() == 1 && string.matches(variablePatern))
			return new Variable(string);
		
		throw ErrorMessage.IndeterminedString(string);
	}
	
	private static Element[] getElements(String str, String separator, int elemCount)
	{
		String[] split = str.split(separator, elemCount);
		if (split.length == 0 || (split.length != elemCount && elemCount != 0)) throw ErrorMessage.IndeterminedString(str);
		
		Element[] elements = new Element[split.length];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = convert(split[i]);
		}
		
		return elements;
	}
}
