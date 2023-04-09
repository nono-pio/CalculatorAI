package com.exemple.math.ParentsElements;

import com.exemple.math.ParentClass.Element;
import com.exemple.math.ParentClass.ParentElement;
import com.exemple.math.numbers.Number;

public class AritmeticSequence extends ParentElement{

    public Element sequence;
    
    public AritmeticSequence(Element sequence)
    {
        super( new Element[] {sequence} );
        this.sequence = sequence;
    }

    public Number toValue() { return sequence.toValue(); }

    @Override
    public String toString() { return sequence.toString(); }

    @Override
    public Element[] getSequences() { return new Element[] {sequence}; }

}
