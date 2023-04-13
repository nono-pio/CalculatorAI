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
        variableData = GlobalVariable.setVariable(variable, null);
    }
    public Variable(String variable, VariableData data)
    { 
        this.variable = variable;
        variableData = data;
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
	public Element clone() { return new Variable(variable, variableData); }

	@Override
	public boolean isEqual(Element elem) {
		return elem.getType() == ElementType.Variable && variable == ((Variable) elem).variable;
	}

	public void setValues(Element[] values) {}
	public String toString(ElementType parentType, boolean isLaTeX) { return variable; }
	
	public void setVariable(HashMap<String, VariableData> variables, int[] curPath, Number value) {
        variableData = GlobalVariable.setVariable(variable, null, variables);
        variableData.paths.add(curPath.clone());
        variableData.variableCount = variableData.paths.size();
        variableData.value = value;
	}
	public Element clonedSimplify() { return this; }
	@Override
	public Element simplify() { return clone(); }
    
}
