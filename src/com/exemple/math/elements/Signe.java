package com.exemple.math.elements;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.numbers.Number;
import com.exemple.math.tools.MathN;

/*
 * a = value ; b = sign
 * Signe(a, b) = a * arg(b) = a * e^(ib) = a * (cos(b) + i sin(b))
 * Signe(a, 0) = a
 * Signe(a, PI) = Signe(a, true) = -a
*/
public class Signe extends Element{
    
    public Element value;
    public Element signe;

    public Signe(Element value, Element signe)
    {
        this.value = value;
        this.signe = signe;
    }
    public Signe(Element value, boolean negate)
    {
        this.value = value;
        this.signe = negate ? MathN.PI : new Number(0);
    }

    public Number getSigne()
    {
        return new Number(signe.toValue().isEqual(MathN.PI)? -1 : 1);
    }
    public boolean isNegate() { return getSigne().value == -1; }

    public ElementType getType() { return ElementType.Signe; }
    public Number toValue() { return MathN.mult(value.toValue(), getSigne()); }
    public Element recipFunction(int[] path, Element curRecip)
    {
        if (path[0] == 1) throw new RuntimeException("Tne signe cannot be a variable");
        Element x = path[0] == 0 ? value : signe;
        Element a = path[0] == 1 ? value : signe;
        return x.recipFunction(newPath(path), new Division(curRecip, new Signe(new Number(1), a)));
    }
    public Element[] getValues() { return new Element[] { value, signe }; }
    public Element simplify() {
        
        Element signeSim = signe.simplify();
        Element valueSim = value.simplify();

        if (signeSim.getType() == ElementType.Number)
        {
            Number sigN = (Number) signeSim;
            if (sigN.isZero()) return valueSim;
        }

        if (valueSim.getType() == ElementType.Number)
        {
            Number valN = (Number) valueSim;
            if (valN.isZero()) return new Number(0);
            if ( signeSim.getType() == ElementType.Number ) return (new Signe(valueSim, signeSim)).toValue();
        }

        return new Signe(valueSim, signeSim);
    }
	public void setValues(Element[] values) {
		this.value = values[0];
		this.signe = values[1];
		
	}

	public String toString(ElementType parentType, boolean isLaTeX) { return (isNegate()? " - " : "") + value.toString(getType(), isLaTeX); }
	public Element clone() { return new Signe(value.clone(), signe.clone()); }
	@Override
	public Element clonedSimplify() {
		// TODO Auto-generated method stub
		return null;
	}
}
