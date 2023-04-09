package com.exemple.math.elements;

import java.util.ArrayList;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ElementType;
import com.exemple.math.numbers.Number;


public class Addition extends Element{

    public Element[] values;

    public Addition(Element[] values) { this.values = values; }
    public Addition(Element value1, Element value2) { this.values = new Element[] {value1, value2}; }
    public Addition(Element value1, Element value2, boolean subtract)
    {
        if (subtract)
            this.values = new Element[] {value1, new Signe(value2, true)};
        else
            this.values = new Element[] {value1, value2};
    }
    public Addition(Element value1, Element value2, Element value3) { this.values = new Element[] {value1, value2, value3}; }

    
    public Number toValue() {
        Number sum = new Number(0);
        for (Element value : values) {
            sum.add(value.toValue());
        }
        return sum;
    }
    public Element[] getValues() {return values;}
    public ElementType getType() { return ElementType.Addition; }
    public String toString() {
        StringBuilder str = new StringBuilder(values[0].toString());
        for (int i = 1; i < values.length; i++)
        {
            if ( values[i].getType() == ElementType.Signe && ((Signe) values[i]).isNegate())
                str.append(values[i].toString());
            else
                str.append(" + " + values[i].toString());
        }
        return "(" + str.toString() + ")";
    }
    public Element recipFunction(int[] path, Element curRecip) {
        Element[] newRecip = new Element[values.length];
        int index = 1;
        for (int i = 0; i < values.length; i++) {
            if (i != path[0])
            {
                newRecip[index] = new Signe(values[i], true);
                index++;
            }
        }
        newRecip[0] = curRecip;

        return values[path[0]].recipFunction(newPath(path), new Addition(newRecip));
    }
    public Element simplify()
    {
        Number cste = new Number(0);
        ArrayList<Element> rest = new ArrayList<Element>();
        for (Element child : values) {
            Element childSim = child.simplify();
            if (childSim.getType() == ElementType.Number)
                cste.add((Number) childSim);
            else
                rest.add(childSim.simplify());
        }

        if (rest.size() == 0) return cste;
        
        if (!cste.isZero()) rest.add(cste);
        return new Addition(rest.toArray( new Element[rest.size()]));
    }
    public Element developing() {
        return null;
    }
    public String toLaTeX() {
        StringBuilder str = new StringBuilder(values[0].toString());
        for (int i = 1; i < values.length; i++)
        {
            if ( values[i].getType() == ElementType.Signe && ((Signe) values[i]).isNegate())
                str.append(values[i].toLaTeX());
            else
                str.append(" + " + values[i].toLaTeX());
        }
        return "\\left(" + str.toString() + "\\right)";
    }
}
