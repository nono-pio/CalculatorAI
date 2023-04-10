package com.exemple.math.tools;

public class ErrorMessage {

    public static RuntimeException VariableNotSet(String variable) {
        return new RuntimeException("The variable "+ variable +" do not have any value");
    }
    public static RuntimeException DividedByZero() {
        return new RuntimeException("Cannot divided by zero");
    }
    public static RuntimeException NoVariable(String variable) {
        return new RuntimeException("The variable "+ variable +" doesn't exist");
    }
    public static RuntimeException NumberTypeNotExisting(String numberType) {
        return new RuntimeException("The type of number " + numberType + " doesn't exist");
    }
    public static RuntimeException NumberRecip() {
        return new RuntimeException("A number doesn't have reciprocal");
    }
    public static RuntimeException NotImplemented() {
        return new RuntimeException("Not implemented");
    }
    
}