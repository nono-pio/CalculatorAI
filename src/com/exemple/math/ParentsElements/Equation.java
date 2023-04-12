package com.exemple.math.ParentsElements;

import java.util.Arrays;
import com.exemple.math.ParentClass.*;
import com.exemple.math.numbers.*;
import com.exemple.math.numbers.Number;
import com.exemple.math.tools.ErrorMessage;

public class Equation extends ParentElement{
    
    public Element rightElement;
    public Element leftElement;

    public Equation(Element rightElement, Element leftElement)
    {
        super( new Element[] { rightElement, leftElement } );
        this.rightElement = rightElement;
        this.leftElement = leftElement; 
    }

    public Number[] solveFor(String variable)
    {
        VariableData varData = variables.get(variable);
        if (varData == null) throw ErrorMessage.NoVariable(variable);

        if (varData.variableCount == 1)
        {
            int[] path = varData.paths.get(0);
            Element[] sequence = getSequences();

            Element elementVar = sequence[path[0]];
            Number value = sequence[1 - path[0]].toValue();

            int[] newPath = Arrays.copyOfRange(path, 1, path.length);

            Number result = elementVar.recipFunction(newPath, value).toValue();

            return new Number[] {result};
        }

        return null;
    }

    public boolean isTrue() { return rightElement.toValue().isEqual(leftElement.toValue()); }

    @Override
    public String toString() { return rightElement.toString() + " = " + leftElement.toString(); }

    @Override
    public Element[] getSequences() { return new Element[] {rightElement, leftElement}; }

    public Element getRecipFunc(String variable)
    {

        VariableData data = variables.get(variable);
        if (data.variableCount != 1) return null;

        int[] path = data.paths.get(0);

        Element reciprocal = path[0] == 0 ? leftElement : rightElement;
        Element rest = path[0] == 0 ? rightElement : leftElement;

        return rest.recipFunction(Element.newPath(path), reciprocal);
    }

}