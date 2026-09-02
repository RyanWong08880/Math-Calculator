//math helper
import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;


import static java.lang.Math.*;

@Target (ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface A {
  public int numExpectedParams() default 0;
  public String[] aliases() default {};
  public String desc() default "???";
}

class MethodNotFoundException extends Exception {
  public MethodNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}

class Main {
  //instance variable to avoid repetitive formal parameters
  public static int[] params;

  public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
    for (int cmdCount = 0; true; cmdCount++) {
      //print command count
      System.out.println("Number of commands called so far: " + cmdCount);

      //store user input
      String command = null; // the first arguemnt
      String[] input = null; // the rest of the arguments
      for (boolean badParams = true; badParams;) {
        try {
          input = scanner.nextLine().split(" ");
          command = input[0];
          params = new int[input.length - 1];
          for (int j = 1; j < input.length; j++) {
            params[j - 1] = Integer.parseInt(input[j]);
          }
          if (input.length > 0) {
            input = Arrays.copyOfRange(input, 1, input.length);
          }
          badParams = false;
        } catch (Exception e) {
          System.out.println("There must be at least one argument. Parameters must be integers.");
        }
      }

      //process the command & output the answer accordingly
      if (command.equals("quit")) {
        scanner.close();
        break;
      } else {
        try {
          boolean found = false;
          Class c = Class.forName("Main");
          for (Method i : c.getMethods()) {
            if (i.getName().equals(command) || (i.getAnnotation(A.class) != null && Arrays.asList(i.getAnnotation(A.class).aliases()).contains(command))) {
              int numExpectedParams = i.getAnnotation(A.class).numExpectedParams();
              if (input.length == numExpectedParams || (numExpectedParams < 0 && input.length >= abs(numExpectedParams))) {
                System.out.println((String) (i.invoke(null, new Object[0])));
              } else {
                throw new IllegalArgumentException("Valid command, but expected " + (numExpectedParams >= 0? numExpectedParams : "at least " + (-numExpectedParams)) + " parameters and got " + input.length + " parameters instead.");
              }
              found = true;
              break;
            }
          }
          if (!found) {
            throw new MethodNotFoundException("Invalid command.");
          }
        } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
        } catch (MethodNotFoundException e) {
          System.out.println(e.getMessage());
        } catch (Exception e) {
          System.out.println("error idk");
          e.printStackTrace();
        }
      }
    }
  }

  @A (numExpectedParams = 0, aliases = {"?", "h"}, desc = "Prints help")
  public static String help() {
    try {
      Class c = Class.forName("Main");
      for (Method i : c.getMethods()) {
        System.out.println();
        A a = i.getAnnotation(A.class);
        
        if (a == null) continue;

        System.out.println("Command name: " + i.getName());
        System.out.println("Number of Expected Parameters: " + a.numExpectedParams());
        String temp = "";
        for (String j : a.aliases()) {
          temp += (j + ", ");
        }
        System.out.println("Aliases: " + temp.substring(0, temp.length() - 2));
        System.out.println("What this command does: " + a.desc());
      }
    } catch (Exception e) {
      System.out.println("something went wrong");
    }
    return "";
    
  }

  @A(numExpectedParams = 0, aliases = "test", desc = "Runs tests.")
  public static String runTests() {
    try {
      Result result = JUnitCore.runClasses(Class.forName("Tests"));
      int numTests = result.getRunCount();
      int failureCount = result.getFailureCount();
      System.out.println(String.format("\nRunning Test: %s", failureCount == 0 ? "PASSED" : "FAILED"));
      System.out.println("  Tests           : " + numTests);
      System.out.println("  Tests Successful: " + (numTests - failureCount));
      System.out.println("  Tests Failed    : " + failureCount);
      for (Failure failure : result.getFailures()) {
        System.out.println("    " + failure.toString());
      }
    } catch (Exception e) {
      ;
    }
    return "";
  }

  @A (numExpectedParams = 6, aliases = {"sys2"}, desc = "Solve for x and y in the equations (ax + by = c) and  (dx + ey = f)")
  public static String systemOfTwoEquationsAndTwoVariables() {
    double x = (double) (params[1] * params[5] - params[4] * params[2]) / (params[1] * params[3] - params[4] * params[0]);
    double y = (params[2] - params[0] * x) / params[1];
    return "(" + x + ", " + y + ")";
  }

  @A(numExpectedParams = 3, aliases = {"quad"}, desc = "For the equation a * x ^ 2 + bx + c = 0, solve for x")
  public static String quadraticFormula() {
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

  @A (numExpectedParams = 12, aliases = {"sys3"}, desc = "Solve for x, y, and z in the equations (ax + by + cz = d), (ex + fy + gz = h), and (ix + jy + kz = l)")
  public static String systemOfThreeEquationsAndThreeVariables(){
    try {
      double a = params[0];
      double b = params[1];
      double c = params[2];
      double d = params[3];
      double e = params[4];
      double f = params[5];
      double g = params[6];
      double h = params[7];
      double i = params[8];
      double j = params[9];
      double k = params[10];
      double l = params[11];
      
      double y = (a * h * i * c + e * d * a * k + a * a * l * g + i * d * e * c - a * a * h * k - e * d * i * c - a * l * e * c - i * d * a * g) / (e * b * a * k - a * a * f * k + a * f * i * c - i * b * a * g + a * a * j * g - a * j * e * c);
      double z = (a * h - e * d + e * b * y - a * f * y) / (a * g - e * c);
      double x = (d - c * z - b * y) / a;
  
      return "(" + x + ", " + y + ", " + z + ")";
    } catch (Exception e) {
      return null;
    }
  }

  @A (numExpectedParams = 3, aliases = {"tovertex"}, desc = "Convert (y = ax^2 + bx + c) to (y = a(x - h)^2 + k)")
  public static String quadraticStandardToVertexForm() {
    double n = params[1] / (-2.0 * params[0]);
    return "y = " + (double) params[0] + "(x + " + (-n) + ") ^ 2 + " + (params[0] * pow(n, 2) + params[1] * n + params[2]);
  }

  @A (numExpectedParams = -2, aliases = {"syn"}, desc = "Given a number, a, followed by the coefficients of a standard form polynomial, P(x), print the resulting coefficients of P(x) / (x - a)")
  public static String syntheticDivision() {
    double[] result = new double[params.length - 1];
    double temp = 0;

    for (int i = 1; i <= result.length; i++) {
      temp += params[i];
      result[i - 1] = temp;
      temp *= params[0];
    }

    return Arrays.toString(result);
  }

  @A (numExpectedParams = 1, aliases = {"pascal", "binom"}, desc = "Given a whole number, n, print the expanded form of (a + b)^n")
  public static String binomialTheorem() {
    if (params[0] != (int) params[0]) return "";
    int[] pascalsTriangle = new int[params[0] + 1];
    for (int i = 0; i < pascalsTriangle.length; i++) {
      pascalsTriangle[i] = pascal(params[0], i);
    }

    String output = "";
    for (int i = 0; i < pascalsTriangle.length; i++) {
      output += pascalsTriangle[i] + "(a^" + (params[0] - i) + ")(b^" + i + ") + ";
    }
    return output.equals("")? "1" : output.substring(0, output.length() - 3);
  }

  private static int pascal(int r, int c) {
    if (c == 0) {
      return 1;
    } else if (c > r) {
      return 0;
    } else {
      return pascal(r - 1, c) + pascal(r - 1, c - 1);
    }
  }  
}