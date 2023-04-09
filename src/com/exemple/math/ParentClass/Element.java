package com.exemple.math.ParentClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.Variable;
import com.exemple.math.numbers.VariableData;

public abstract class Element {
    
    // #region Abstract Method

    public abstract ElementType getType();

    public abstract Number toValue();
    public abstract Element recipFunction(int[] path, Element curRecip);

    public abstract Element[] getValues();
    public abstract String toString();
    public abstract String toLaTeX();

    public abstract Element simplify();
    public abstract Element developing();

    // #endregion

    public static int[] newPath(int[] curPath) { return Arrays.copyOfRange(curPath, 1, curPath.length); }

    public void findVariable(HashMap<String, VariableData> variables, int[] curPath)
    {
        if (getType() == ElementType.Variable)
        {
            Variable variableElement = (Variable) this;
            VariableData data = variables.get(variableElement.variable);

            if (data == null) //no variable then create one else update
            {
                data = new VariableData(null);
                data.variableCount = 1;
                data.paths = new ArrayList<int[]>();
            } else 
            {
                data.variableCount++;
            }
            
            variableElement.variableData = data;
            data.paths.add(curPath.clone());
            variables.put(variableElement.variable, data);
        } else
        {
            Element[] childs = getValues();

            int[] newPath = Arrays.copyOf(curPath, curPath.length + 1);

            for (int i = 0; i < childs.length; i++)
            {
                newPath[newPath.length - 1] = i;
                childs[i].findVariable(variables, newPath);
            }
        }
    }
    public boolean isConstant()
    {
        if (getType() == ElementType.Variable) return false;
    
        for (Element child : getValues()) { if (child.isConstant() == false) return false; }

        return true;
    }

    public void forEach(IElement func)
    {
        int[] path = new int[] {0};
        forEachChild(func, path);
    }
    public void forEachChild(IElement func, int[] path)
    {
        func.invoke(this, path);
        int[] newPath = Arrays.copyOf(path, path.length + 1);
        Element[] childs = getValues();
        for (int i = 0; i < childs.length; i++) {
            newPath[newPath.length - 1] = i;
            childs[i].forEachChild(func, newPath);
        }
    }
    public boolean isEqual(Element elem)
    {
    	if (getType() != elem.getType()) return false;
    	Element[] values = getValues();
    	Element[] values2 = elem.getValues();
    	if (values.length != values2.length) return false;
    	for (int i=0; i < values.length; i++)
    	{
    		if (!values[i].isEqual(values2[i])) return false;
    	}
    	return true;
    }
}