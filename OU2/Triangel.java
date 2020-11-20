package OU2;

public class Triangel {
    public static double calculateArea(double bass, double height) {
        return (bass * height) / 2;
    }

    public static double calculateCircumference(double a, double b, double c) {
        return a + b + c;
    }

    public static double calculateInnerRadius(double a, double b, double c) {
        double s = 0.5 * (a + b + c);
        return Math.sqrt(((s - a) * (s - b) * (s - c))/s);
    }

    public static double calculateOuterRadius(double a, double b, double c) {
        double s = 0.5 * (a + b + c);
        return (a * b * c)/( 4 * Math.sqrt(s * (s - a) * (s - b) * (s - c)));
    }

    public static double calculateBisektrisLength(double b, double c, double a) {
        return (2 * b * c * Math.cos(a / 2)) / (b + c);
    }

    public static double bisektis(double b, double c, double alfa) {
        double p = 2 * b * c * Math.cos (alfa / 2);
        double bis = p / (b + c);

        return bis;
    }
}
