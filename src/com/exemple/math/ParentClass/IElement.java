package com.exemple.math.ParentClass;

import com.exemple.math.tools.Position;

@FunctionalInterface
public interface IElement {
    void invoke(Element e, Position p);
}