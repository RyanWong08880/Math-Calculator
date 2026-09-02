package com.mathcalc;

import static java.lang.Math.*;

/**
 * Collection of mathematical operations for the calculator.
 * Each method can be called via the CLI using the @Command annotation.
 */
public class MathOperations {
    /** Cached Pascal's triangle values for binomial theorem optimization */
    private static int[][] pascalCache = new int[20][];

    /**
     * Solves a system of two linear equations with two variables.
     * Given: (ax + by = c) and (dx + ey = f)
     *
     * @param params array of [a, b, c, d, e, f]
     * @return string representation of (x, y)
     */
    @Command(numExpectedParams = 6, aliases = {"sys2"}, 
             desc = "Solve for x and y in the equations (ax + by = c) and (dx + ey = f)")
    public static String systemOfTwoEquationsAndTwoVariables(int[] params) {
        double x = (double) (params[1] * params[5] - params[4] * params[2]) 
                   / (params[1] * params[3] - params[4] * params[0]);
        double y = (params[2] - params[0] * x) / params[1];
        return "(" + x + ", " + y + ")";
    }

    /**
     * Solves a quadratic equation using the quadratic formula.
     * Given: ax² + bx + c = 0, solves for x
     *
     * @param params array of [a, b, c]
     * @return string representation of solutions (real or complex)
     */
    @Command(numExpectedParams = 3, aliases = {"quad"}, 
             desc = "For the equation a * x ^ 2 + bx + c = 0, solve for x")
    public static String quadraticFormula(int[] params) {
        double discriminant = params[1] * params[1] - 4 * params[0] * params[2];

        if (discriminant >= 0) {
            double x1 = (-params[1] + sqrt(discriminant)) / (2 * params[0]);
            double x2 = (-params[1] - sqrt(discriminant)) / (2 * params[0]);
            return x1 + ", " + x2;
        } else {
            double realPart = -params[1] / (2 * params[0]);
            double imaginaryPart = sqrt(-discriminant) / (2 * params[0]);
            return realPart + " + " + imaginaryPart + "i, " + realPart + " - " + imaginaryPart + "i";
        }
    }

    /**
     * Solves a system of three linear equations with three variables.
     * Given: (ax + by + cz = d), (ex + fy + gz = h), (ix + jy + kz = l)
     *
     * @param params array of [a, b, c, d, e, f, g, h, i, j, k, l]
     * @return string representation of (x, y, z)
     */
    @Command(numExpectedParams = 12, aliases = {"sys3"}, 
             desc = "Solve for x, y, and z in the equations (ax + by + cz = d), (ex + fy + gz = h), and (ix + jy + kz = l)")
    public static String systemOfThreeEquationsAndThreeVariables(int[] params) {
        try {
            double a = params[0], b = params[1], c = params[2], d = params[3];
            double e = params[4], f = params[5], g = params[6], h = params[7];
            double i = params[8], j = params[9], k = params[10], l = params[11];

            double y = (a * h * i * c + e * d * a * k + a * a * l * g + i * d * e * c 
                        - a * a * h * k - e * d * i * c - a * l * e * c - i * d * a * g) 
                       / (e * b * a * k - a * a * f * k + a * f * i * c - i * b * a * g + a * a * j * g - a * j * e * c);
            double z = (a * h - e * d + e * b * y - a * f * y) / (a * g - e * c);
            double x = (d - c * z - b * y) / a;

            return "(" + x + ", " + y + ", " + z + ")";
        } catch (Exception e) {
            return "(NaN, NaN, NaN)";
        }
    }

    /**
     * Converts a quadratic from standard form to vertex form.
     * Converts: y = ax² + bx + c  to  y = a(x - h)² + k
     *
     * @param params array of [a, b, c]
     * @return string representation of vertex form
     */
    @Command(numExpectedParams = 3, aliases = {"tovertex"}, 
             desc = "Convert (y = ax^2 + bx + c) to (y = a(x - h)^2 + k)")
    public static String quadraticStandardToVertexForm(int[] params) {
        double h = params[1] / (-2.0 * params[0]);
        double k = params[0] * pow(h, 2) + params[1] * h + params[2];
        return "y = " + (double) params[0] + "(x + " + (-h) + ") ^ 2 + " + k;
    }

    /**
     * Performs synthetic division on a polynomial.
     * Given: divisor 'a' and polynomial coefficients P(x)
     * Returns: coefficients of P(x) / (x - a)
     *
     * @param params array of [a, coefficient1, coefficient2, ...]
     * @return string representation of resulting coefficients
     */
    @Command(numExpectedParams = -2, aliases = {"syn"}, 
             desc = "Given a number, a, followed by the coefficients of a standard form polynomial, P(x), print the resulting coefficients of P(x) / (x - a)")
    public static String syntheticDivision(int[] params) {
        double[] result = new double[params.length - 1];
        double temp = 0;

        for (int i = 1; i <= result.length; i++) {
            temp += params[i];
            result[i - 1] = temp;
            temp *= params[0];
        }

        return java.util.Arrays.toString(result);
    }

    /**
     * Expands (a + b)^n using the binomial theorem.
     * Uses Pascal's triangle to calculate binomial coefficients.
     *
     * @param params array of [n]
     * @return string representation of expanded form
     */
    @Command(numExpectedParams = 1, aliases = {"pascal", "binom"}, 
             desc = "Given a whole number, n, print the expanded form of (a + b)^n")
    public static String binomialTheorem(int[] params) {
        if (params[0] < 0 || params[0] >= 20) {
            return "n must be between 0 and 19";
        }

        int n = params[0];
        int[] pascalsRow = getPascalRow(n);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < pascalsRow.length; i++) {
            output.append(pascalsRow[i])
                  .append("(a^").append(n - i)
                  .append(")(b^").append(i).append(")");
            if (i < pascalsRow.length - 1) {
                output.append(" + ");
            }
        }

        return output.length() == 0 ? "1" : output.toString();
    }

    /**
     * Gets the nth row of Pascal's triangle using caching.
     *
     * @param n row number
     * @return array of coefficients
     */
    private static int[] getPascalRow(int n) {
        if (pascalCache[n] != null) {
            return pascalCache[n];
        }

        int[] row = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            row[i] = calculateBinomialCoefficient(n, i);
        }
        pascalCache[n] = row;
        return row;
    }

    /**
     * Calculates binomial coefficient C(n, k) iteratively.
     *
     * @param n total items
     * @param k items chosen
     * @return binomial coefficient
     */
    private static int calculateBinomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        if (k > n) {
            return 0;
        }

        // Optimize by using the smaller k
        if (k > n - k) {
            k = n - k;
        }

        int result = 1;
        for (int i = 0; i < k; i++) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }
}
