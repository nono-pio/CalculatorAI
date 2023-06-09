package com.exemple.math.elements;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.math.MathN;
import com.exemple.math.numbers.Number;
import com.exemple.math.tools.ErrorMessage;
import com.exemple.math.tools.StringFormat;

public class Division extends Element{

    public Element numerator;
    public Element denominator;

    public Division(Element numerator, Element denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public ElementType getType() { return ElementType.Division; }
    public Number toValue() { return MathN.div(numerator.toValue(), denominator.toValue()); }
    public Element[] getValues() { return new Element[] {numerator, denominator}; }
    public Element recipFunction(int[] path, Element curRecip)
    {
        Element newRecip;
        if (path[0] == 0)
        {
            newRecip = new Product(curRecip, denominator);

        } else
        {
            newRecip = new Division(numerator, curRecip);
        }

        return (path[0] == 0 ? numerator : denominator).recipFunction(newPath(path), newRecip);
    }
    public Element simplify() {
        
        Element numSim = numerator.simplify();
        if (numSim.getType() == ElementType.Number && ((Number) numSim).isZero()) return new Number(0);

        Element denSim = denominator.simplify();
        if (denSim.getType() == ElementType.Number)
        {
            Number denN = (Number) denSim;
            if (denN.isZero()) throw ErrorMessage.DividedByZero();
            else if (denN.isEqual(new Number(1))) return numSim;

            if (numSim.getType() == ElementType.Number) return MathN.div((Number) numSim, denN);
        }

        return new Division(numSim, denSim);
    }

    public String toLaTeX() { return "\\frac{" + numerator.toLaTeX() + "}{"+ denominator.toLaTeX() +"}"; }
	public void setValues(Element[] values) {
		this.numerator = values[0];
		this.denominator = values[1];
	}
	public String toString(ElementType parentType, boolean isLaTeX) {
		if (isLaTeX) return "\\frac{" + numerator.toLaTeX() + "}{"+ denominator.toLaTeX() +"}";
		else {
			String str = numerator.toString(getType(), isLaTeX) + "/" + denominator.toString(getType(), isLaTeX);
			if (parentType == null || parentType == ElementType.Addition) return str;
			else return StringFormat.bracket(str, isLaTeX);
		}
	}
	public Element clone() { return new Division(numerator.clone(), denominator.clone()); }

	@Override
	public Element clonedSimplify() {
		// TODO Auto-generated method stub
		return null;
	}
}
