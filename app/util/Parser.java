package util;

public class Parser {
    public static int bound(int value,int min,int max) {
        return value < min ? min : value > max ? max : value;
    }
}
