package com.exemple.math.math;

import com.exemple.math.numbers.Number;

public class MathN { 
    
    public static Number add(Number a, Number b) { return new Number(a.value + b.value); }
    public static Number sub(Number a, Number b) { return new Number(a.value - b.value); }
    public static Number mult(Number a, Number b) { return new Number(a.value * b.value); }
    public static Number div(Number a, Number b) { return new Number(a.value / b.value); }
    public static Number pow(Number a, Number b) { return new Number((float) Math.pow(a.value, b.value)); }
    public static Number sqrt(Number a, Number b) { return new Number((float) Math.pow(a.value, 1 / b.value)); }
    public static Number log(Number a, Number b) { return new Number((float) (Math.log(b.value) / Math.log(a.value))); }
    public static Number sin(Number a) { return new Number((float) Math.sin(a.value)); }
    public static Number cos(Number a) { return new Number((float) Math.cos(a.value)); }
    public static Number tan(Number a) { return new Number((float) Math.tan(a.value)); }


    public static final Number PI = new Number( (float) Math.PI );
    public static final Number E = new Number( (float) Math.E );
}
