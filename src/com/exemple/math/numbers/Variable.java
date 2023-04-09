package com.exemple.math.numbers;

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
    public String toString() { return variable; }
    public Number reciprocal(int[] path, Number value) {
        if (path.length == 0) return value;
        else throw ErrorMessage.NumberRecip();
    }
    public Element recipFunction(int[] path, Element curRecip) { return curRecip; }
    public Element simplify() { return this; }
    public Element developing() { return this; }
    public String toLaTeX() { return toString(); }

	@Override
	public boolean isEqual(Element elem) {
		return elem.getType() == ElementType.Variable && variable == ((Variable) elem).variable;
	}
    
}
