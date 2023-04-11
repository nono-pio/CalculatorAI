package com.exemple.math.tools;

import java.util.ArrayList;

import com.exemple.math.ParentClass.Element;

public final class Tools {
	
	public static ArrayList<Element[]> getCouples(ArrayList<Element[]> couplesList, ArrayList<Element> base)
	{ return getCouples(new Element[couplesList.size()], couplesList, 0, base); }
	
	public static ArrayList<Element[]> getCouples(Element[] curPath, ArrayList<Element[]> paths, int index, ArrayList<Element> base)
	{
		if (index >= paths.size())
		{
			ArrayList<Element[]> r = new ArrayList<>();
			Element[] path = new Element[base.size() + curPath.length];
			System.arraycopy(base.toArray(new Element[base.size()]), 0, path, 0, base.size());  
			System.arraycopy(curPath, 0, path, base.size(), curPath.length); 
			r.add(path);
			return r;
		}
		ArrayList<Element[]> couples = new ArrayList<Element[]>();
		for (Element path : paths.get(index)) {
			curPath[index] = path;
			couples.addAll(getCouples(curPath, paths, index + 1, base));
		}
		
		return couples;
	}
	
	public static Element[] cloneElementArray(Element[] realValues)
	{
		Element[] newValues = new Element[realValues.length];
		for (int i = 0; i < newValues.length; i++) {
			newValues[i] = realValues[i].clone();
		}
		return newValues;
	}

	public static Element[] mergeArray(Element[] array1, Element[] array2)
	{
		Element[] result = new Element[array1.length + array2.length];
		System.arraycopy(array1, 0, result, 0, array1.length);
		System.arraycopy(array2, 0, result, array1.length, array2.length);
		return result;
	}
}
