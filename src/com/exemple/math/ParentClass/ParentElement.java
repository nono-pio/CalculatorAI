package com.exemple.math.ParentClass;

import java.util.HashMap;

import com.exemple.math.numbers.Number;
import com.exemple.math.numbers.VariableData;
import com.exemple.math.tools.ErrorMessage;


public abstract class ParentElement {
    
    public HashMap<String, VariableData> variables;

    public ParentElement(Element[] sequences) 
    {
        // find variable
        variables = new HashMap<String, VariableData>();

        for (int i = 0; i < sequences.length; i++) {
            int[] path = new int[] {i};
            sequences[i].findVariable(variables, path);
        }
    }

    public abstract String toString();
    public abstract Element[] getSequences();

    public void setVariable(String variable, Number value)
    {
        VariableData data = variables.get(variable);
        if (data == null) throw ErrorMessage.NoVariable(variable);
        data.value = value;
        VariableData dataStore = variables.put(variable, data);
        if (dataStore == null) throw ErrorMessage.NoVariable(variable);
    }

    public Element getChild(int[] path)
    {
        Element child = getSequences()[path[0]];
        for (int i = 1; i < path.length; i++) {
            Element[] childs = child.getValues();
            if ( childs.length - 1 < path[i] ) throw new RuntimeException("Path invalid");
            child = childs[path[i]];
        }
        return child;
    }

    public void forEach(IElement func)
    {
        Element[] sequences = getSequences();
        int[] path = new int[]{0};
        for (int i = 0; i < sequences.length; i++) {
            path[0] = i;
            sequences[i].forEachChild(func, path);
        }
    }
}
