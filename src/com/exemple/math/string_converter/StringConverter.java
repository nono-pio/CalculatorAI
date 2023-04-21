package com.exemple.math.string_converter;

import java.util.ArrayList;

import com.exemple.math.tools.ErrorMessage;
import com.exemple.math.ParentClass.Element;
import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;

public class StringConverter {

	public static final String numberPatern = "\\-?[0-9]+((\\.|,)[0-9]+)?";
	
	private String string;
	private ArrayList<Element> rootElement = new ArrayList<>();
	
	public StringConverter(String string)
	{
		this.string = string;
	}
	
	
	public Element toElement()
	{
		
		String str = string.replace(" ", ""); 
		
		StringBuilder curNumber = new StringBuilder();
		
		ElementNode rootNode = new ElementNode(PriorityOperator.Root);
		
		/*
		 * 1) isNumber -> false -> add
		 * 			   -> true -> append
		 * 2) isMathSymbol -> true -> add
		 * 				   -> false -> last isMathSymbol -> true -> continue
		 * 												 -> false -> add "*"
		 * 3) isLetter -> true -> add
		 * */
		
		boolean lastIsMathSymbol = true;
		
		
		for (char c : str.toCharArray()) {
			
			System.out.println(rootNode + " : " + rootElement);
			
			if (Character.isDigit(c) || ",.".indexOf(c) != -1) // if integer or , or .
			{
				curNumber.append(c);
				continue;
				
			} else if (!curNumber.isEmpty()) //if number isn't empty add to rootElem
			{
				String numStr = curNumber.toString();
				if (!numStr.matches(numberPatern)) throw ErrorMessage.IndeterminedString(numStr);
				rootElement.add(new Number(Float.valueOf(numStr.replace(',', '.'))));
				rootNode = rootNode.addChild(new ElementNode(rootElement.size() - 1));
				curNumber = new StringBuilder();
			}
			
			boolean isMathSymbol = true;
			if (c == '+') // if c is a math symbol
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Add));
				
			} else if (c == '-')
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Sub));
			}  else if (c == '*')
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Mult));
			} else if (c == '/')
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Div));
			} else if (c == '^')
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Pow));
			} else if (!lastIsMathSymbol)
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Mult));
				isMathSymbol = false;
				
			} else
			{
				isMathSymbol = false;
			}
			lastIsMathSymbol = isMathSymbol;
			
			if (Character.isAlphabetic(c))
			{
				rootElement.add(new Variable(String.valueOf(c)));
				rootNode = rootNode.addChild(new ElementNode(rootElement.size() - 1));
			}
		}
		
		if (!curNumber.isEmpty())
		{
			String numStr = curNumber.toString();
			if (!numStr.matches(numberPatern)) throw ErrorMessage.IndeterminedString(numStr);
			rootElement.add(new Number(Float.valueOf(numStr.replace(',', '.'))));
			rootNode = rootNode.addChild(new ElementNode(rootElement.size() - 1));
			curNumber = new StringBuilder();
		}
		
		return rootNode.toElement(rootElement);
	}
	
	@Override
	public String toString()
	{
		return string;
	}
}
