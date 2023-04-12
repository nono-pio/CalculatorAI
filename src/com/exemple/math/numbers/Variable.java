package com.exemple.math.numbers;

import java.util.ArrayList;
import java.util.HashMap;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.tools.ErrorMessage;

public class Variable extends Element {

	public String variable;
    public VariableData variableData;

    public Variable(String variable)
    { 
        this.variable = variable;
    }

    public ElementType getType() { return ElementType.Variable; }
    public Number toValue() {
        if (variableData == null || variableData.value == null) throw ErrorMessage.VariableNotSet(variable);
        return variableData.value;
    }
    public Element[] getValues() { return new Element[0]; }
    public Number reciprocal(int[] path, Number value) {
        if (path.length == 0) return value;
        else throw ErrorMessage.NumberRecip();
    }
    public Element recipFunction(int[] path, Element curRecip) { return curRecip; }
	public Element clone() { return new Variable(variable); }

	@Override
	public boolean isEqual(Element elem) {
		return elem.getType() == ElementType.Variable && variable == ((Variable) elem).variable;
	}

	public void setValues(Element[] values) {}
	public String toString(ElementType parentType, boolean isLaTeX) { return variable; }
	
	@Override
	public void findVariable(HashMap<String, VariableData> variables, int[] curPath) {
		
        VariableData data = variables.get(variable);

        if (data == null) //no variable then create one / else update
        {
            data = new VariableData(null);
            data.variableCount = 1;
            data.paths = new ArrayList<int[]>();
        } else 
        {
            data.variableCount++;
        }
        
        variableData = data;
        data.paths.add(curPath.clone());
        variables.put(variable, data);
	}
	public Element clonedSimplify() { return this; }
	@Override
	public Element simplify() { return clone(); }
    
}
