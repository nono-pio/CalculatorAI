package com.exemple.math.elements;

import java.util.Arrays;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.numbers.Number;
import com.exemple.math.tools.MathN;

/*
 * a = value ; b = sign
 * Sign(a, b) = a * arg(b) = a * e^(ib) = a * (cos(b) + i sin(b))
 * Sign(a, 0) = a
 * Sign(a, PI) = Sign(a, true) = -a
*/
public class Sign extends Element{
    
    public Element value;
    public Element sign;

    public Sign(Element value, Element sign)
    {
        this.value = value;
        this.sign = sign;
    }
    public Sign(Element value, boolean negate)
    {
        this.value = value;
        this.sign = negate ? MathN.PI : new Number(0);
    }

    public Number getSigne()
    {
        return new Number(sign.toValue().isEqual(MathN.PI)? -1 : 1);
    }
    public boolean isNegate() { return getSigne().value == -1; }

    public ElementType getType() { return ElementType.Sign; } 
    public Number toValue() { return MathN.mult(value.toValue(), getSigne()); }
    public Element recipFunction(int[] path, Element curRecip)
    {
        if (path[0] == 1) throw new RuntimeException("The signe cannot be a variable");
        Element x = path[0] == 0 ? value : sign;
        Element a = path[0] == 1 ? value : sign;
        return x.recipFunction(newPath(path), new Division(curRecip, new Sign(new Number(1), a)));
    }
    public Element[] getValues() { return new Element[] { value, sign }; }
	public void setValues(Element[] values) {
		this.value = values[0];
		this.sign = values[1];
		
	}

	public String toString(ElementType parentType, boolean isLaTeX) { return (isNegate()? " - " : "") + value.toString(getType(), isLaTeX); }
	public Element clone() { return new Sign(value.clone(), sign.clone()); }
	public Element clonedSimplify() {
		
		if (sign.getType() == ElementType.Number && ((Number) sign).isEqual(new Number(0))) return value;
		
		if (value.getType() == ElementType.Number && ((Number) value).isEqual(new Number(0))) return new Number(0);
		
		if (value.getType() == ElementType.Product)
		{
			Product pro = (Product) value;
			Element[] values = pro.getValues();
			Element[] newValues;
			newValues = Arrays.copyOf(values, values.length + 1);
			newValues[newValues.length - 1] = new Number(-1);
			pro.setValues(newValues);
			return pro.clonedSimplify();
		}
		
		return this;
	}
	
	public Product toProduct() { return new Product(getSigne(), value); }
}
