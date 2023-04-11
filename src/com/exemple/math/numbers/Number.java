package com.exemple.math.numbers;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.tools.ErrorMessage;

public class Number extends Element{
    
    public float value;
    public NumberType numberType;

    public Number(float realNumber)
    {
        value = realNumber;
        numberType = NumberType.Real;
    }

    public void add(Number num) { value += num.value; }
    public void sub(Number num) { value -= num.value; }
    public void mult(Number num) { value *= num.value; }
    public void div(Number num) { value /= num.value; }

    public boolean isEqual(Number num)
    {
        if ( value == 0 && num.value == 0 ) return true;
        return Math.abs((value - num.value) / (value + num.value)) * 2 <= 0.005; // if dif < 0.5%
    }
    public boolean isZero() { return isEqual(new Number(0)); }


    public ElementType getType() { return ElementType.Number; }
    public Number toValue() { return this; }
    public Element[] getValues() { return new Element[0]; }
    public Number reciprocal(int[] path, Number value) { throw ErrorMessage.NumberRecip(); }
    public Element recipFunction(int[] path, Element curRecip) { throw ErrorMessage.NumberRecip(); }

	@Override
	public boolean isEqual(Element elem) {
		System.out.println("fjsdhkjhdjdkd " + (elem.getType() == ElementType.Number));
		return elem.getType() == ElementType.Number && isEqual((Number) elem);
	}

	public void setValues(Element[] values) {}
	public String toString(ElementType parentType, boolean isLaTeX) { return String.valueOf(value); }
	public Element clone() { return new Number(value); }
	public Element clonedSimplify() { return this; }
	@Override
	public Element simplify() { return clone(); }
}