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

}
