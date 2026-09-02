package com.mathcalc;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for mathematical operations.
 */
public class Tests {

    // System of Two Equations Tests
    @Test
    public void testSystemOfTwoEquationsAndTwoVariables1() {
        Main.params = new int[]{3, 1, 15, 1, 2, 10};
        assertEquals("(4.0, 3.0)", MathOperations.systemOfTwoEquationsAndTwoVariables(Main.params));
    }

    @Test
    public void testSystemOfTwoEquationsAndTwoVariables2() {
        Main.params = new int[]{2, 2, 18, 1, 3, 17};
        assertEquals("(5.0, 4.0)", MathOperations.systemOfTwoEquationsAndTwoVariables(Main.params));
    }

    @Test
    public void testSystemOfTwoEquationsAndTwoVariables3() {
        Main.params = new int[]{2, 3, 8, -2, 1, 0};
        assertEquals("(1.0, 2.0)", MathOperations.systemOfTwoEquationsAndTwoVariables(Main.params));
    }

    @Test
    public void testSystemOfTwoEquationsAndTwoVariables4() {
        Main.params = new int[]{1, -5, -23, 2, 1, -2};
        assertEquals("(-3.0, 4.0)", MathOperations.systemOfTwoEquationsAndTwoVariables(Main.params));
    }

    @Test
    public void testSystemOfTwoEquationsAndTwoVariables5() {
        Main.params = new int[]{4, -1, -1, 2, 1, 4};
        assertEquals("(0.5, 3.0)", MathOperations.systemOfTwoEquationsAndTwoVariables(Main.params));
    }

    @Test
    public void testSystemOfTwoEquationsAndTwoVariables6() {
        Main.params = new int[]{1, 2, 3, 2, 4, 6};
        assertEquals("(NaN, NaN)", MathOperations.systemOfTwoEquationsAndTwoVariables(Main.params));
    }

    @Test
    public void testSystemOfTwoEquationsAndTwoVariables7() {
        Main.params = new int[6];
        assertEquals("(NaN, NaN)", MathOperations.systemOfTwoEquationsAndTwoVariables(Main.params));
    }

    // Quadratic Formula Tests
    @Test
    public void testQuadraticFormula1() {
        Main.params = new int[]{1, 2, 1};
        assertEquals("-1.0, -1.0", MathOperations.quadraticFormula(Main.params));
    }

    @Test
    public void testQuadraticFormula2() {
        Main.params = new int[]{-2, 9, 35};
        assertEquals("-2.5, 7.0", MathOperations.quadraticFormula(Main.params));
    }

    @Test
    public void testQuadraticFormula3() {
        Main.params = new int[]{2, -21, 40};
        assertEquals("8.0, 2.5", MathOperations.quadraticFormula(Main.params));
    }

    @Test
    public void testQuadraticFormula4() {
        Main.params = new int[]{1, 0, 9};
        assertEquals("0.0 + 3.0i, 0.0 - 3.0i", MathOperations.quadraticFormula(Main.params));
    }

    @Test
    public void testQuadraticFormula5() {
        Main.params = new int[]{1, 0, 3};
        assertEquals("0.0 + 1.7320508075688772i, 0.0 - 1.7320508075688772i", MathOperations.quadraticFormula(Main.params));
    }

    @Test
    public void testQuadraticFormula6() {
        Main.params = new int[3];
        assertEquals("NaN, NaN", MathOperations.quadraticFormula(Main.params));
    }

    // System of Three Equations Tests
    @Test
    public void testSystemOfThreeEquationsAndThreeVariables1() {
        Main.params = new int[] {2, 1, 1, 11, 3, 4, 1, 19, 3, 6, 5, 43};
        assertEquals("(2.0, 2.0, 5.0)", MathOperations.systemOfThreeEquationsAndThreeVariables(Main.params));
    }

    @Test
    public void testSystemOfThreeEquationsAndThreeVariables2() {
        Main.params = new int[12];
        assertEquals("(NaN, NaN, NaN)", MathOperations.systemOfThreeEquationsAndThreeVariables(Main.params));
    }

    // Quadratic Standard to Vertex Form Tests
    @Test
    public void testQuadraticStandardToVertexForm1() {
        Main.params = new int[] {1, 9, 8};
        assertEquals("y = 1.0(x + 4.5) ^ 2 + -12.25", MathOperations.quadraticStandardToVertexForm(Main.params));
    }

    @Test
    public void testQuadraticStandardToVertexForm2() {
        Main.params = new int[] {1, -6, 3};
        assertEquals("y = 1.0(x + -3.0) ^ 2 + -6.0", MathOperations.quadraticStandardToVertexForm(Main.params));
    }

    @Test
    public void testQuadraticStandardToVertexForm3() {
        Main.params = new int[] {-2, 5, 0};
        assertEquals("y = -2.0(x + -1.25) ^ 2 + 3.125", MathOperations.quadraticStandardToVertexForm(Main.params));
    }

    @Test
    public void testQuadraticStandardToVertexForm4() {
        Main.params = new int[] {-4, -24, -15};
        assertEquals("y = -4.0(x + 3.0) ^ 2 + 21.0", MathOperations.quadraticStandardToVertexForm(Main.params));
    }

    @Test
    public void testQuadraticStandardToVertexForm5() {
        Main.params = new int[] {0, 0, 0};
        assertEquals("y = 0.0(x + NaN) ^ 2 + NaN", MathOperations.quadraticStandardToVertexForm(Main.params));
    }

    // Synthetic Division Tests
    @Test
    public void testSyntheticDivision1() {
        Main.params = new int[] {-7, 1, 4, 56, 43, -42};
        assertEquals("[1.0, -3.0, 77.0, -496.0, 3430.0]", MathOperations.syntheticDivision(Main.params));
    }

    @Test
    public void testSyntheticDivision2() {
        Main.params = new int[] {-3, 1, 6, 5, -17, -15};
        assertEquals("[1.0, 3.0, -4.0, -5.0, 0.0]", MathOperations.syntheticDivision(Main.params));
    }

    @Test
    public void testSyntheticDivision3() {
        Main.params = new int[] {3, 1, 6, 5, -17, -15};
        assertEquals("[1.0, 9.0, 32.0, 79.0, 222.0]", MathOperations.syntheticDivision(Main.params));
    }

    @Test
    public void testSyntheticDivision4() {
        Main.params = new int[2];
        assertEquals("[0.0]", MathOperations.syntheticDivision(Main.params));
    }

    // Binomial Theorem Tests
    @Test
    public void testBinomialTheorem1() {
        Main.params = new int[1];
        assertEquals("1(a^0)(b^0)", MathOperations.binomialTheorem(Main.params));
    }

    @Test
    public void testBinomialTheorem2() {
        Main.params = new int[]{2};
        assertEquals("1(a^2)(b^0) + 2(a^1)(b^1) + 1(a^0)(b^2)", MathOperations.binomialTheorem(Main.params));
    }

    @Test
    public void testBinomialTheorem3() {
        Main.params = new int[]{3};
        assertEquals("1(a^3)(b^0) + 3(a^2)(b^1) + 3(a^1)(b^2) + 1(a^0)(b^3)", MathOperations.binomialTheorem(Main.params));
    }

    @Test
    public void testBinomialTheorem4() {
        Main.params = new int[]{4};
        assertEquals("1(a^4)(b^0) + 4(a^3)(b^1) + 6(a^2)(b^2) + 4(a^1)(b^3) + 1(a^0)(b^4)", MathOperations.binomialTheorem(Main.params));
    }
}
