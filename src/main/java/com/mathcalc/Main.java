package com.mathcalc;

import java.util.*;
import java.lang.reflect.*;

/**
 * Math Calculator - A CLI-based mathematical calculation tool.
 * Provides various mathematical operations accessible through a command-line interface.
 *
 * Commands can be invoked using their method names or aliases.
 * Type 'help' or '?' to see available commands.
 * Type 'test' or 'runtests' to run the test suite.
 * Type 'quit' to exit.
 */
public class Main {
    /** Global parameters passed from user input to command methods */
    public static int[] params;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int commandCount = 0;

        System.out.println("=== Math Calculator ===");
        System.out.println("Type 'help' for available commands or 'quit' to exit.\n");

        for (; true; commandCount++) {
            System.out.println("Commands executed: " + commandCount);
            System.out.print("> ");

            // Read and parse user input
            String[] input;
            String command;

            try {
                input = readAndParseInput(scanner);
                if (input == null || input.length == 0) {
                    continue;
                }
                command = input[0];
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }

            // Handle quit command
            if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                scanner.close();
                break;
            }

            // Execute command
            try {
                executeCommand(command, input);
            } catch (MethodNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred");
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads a line from input and parses it into command and parameters.
     *
     * @param scanner input scanner
     * @return array of [command, param1, param2, ...]
     * @throws IllegalArgumentException if parameters are not valid integers
     */
    private static String[] readAndParseInput(Scanner scanner) throws IllegalArgumentException {
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            return null;
        }

        String[] parts = line.split("\\s+");
        String[] result = new String[parts.length];
        result[0] = parts[0];

        // Parse parameters as integers
        params = new int[parts.length - 1];
        for (int i = 1; i < parts.length; i++) {
            try {
                params[i - 1] = Integer.parseInt(parts[i]);
                result[i] = parts[i];
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("All parameters must be integers, got: " + parts[i]);
            }
        }

        return result;
    }

    /**
     * Executes a command by finding the corresponding method and invoking it.
     *
     * @param command command name or alias
     * @param input   full input array
     * @throws MethodNotFoundException if command is not found
     * @throws IllegalArgumentException if parameter count is incorrect
     */
    private static void executeCommand(String command, String[] input) 
            throws MethodNotFoundException, IllegalArgumentException {
        int paramCount = input.length - 1;

        // Handle help command
        if (command.equalsIgnoreCase("help") || command.equals("?") || command.equals("h")) {
            printHelp();
            return;
        }

        // Handle test command
        if (command.equalsIgnoreCase("test") || command.equalsIgnoreCase("runtests")) {
            runTests();
            return;
        }

        // Look for matching command in MathOperations
        Class<?> operationsClass = MathOperations.class;
        for (Method method : operationsClass.getMethods()) {
            Command commandAnnotation = method.getAnnotation(Command.class);
            if (commandAnnotation == null) {
                continue;
            }

            // Check if method name or alias matches
            if (method.getName().equals(command) || isAliasMatch(commandAnnotation, command)) {
                validateParameterCount(paramCount, commandAnnotation.numExpectedParams(), command);

                try {
                    Object result = method.invoke(null, (Object) params);
                    System.out.println("Result: " + result);
                    System.out.println();
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("Error executing command: " + e.getMessage(), e);
                }
            }
        }

        throw new MethodNotFoundException("Unknown command: '" + command + "'. Type 'help' for available commands.");
    }

    /**
     * Checks if a command alias matches the provided command string.
     *
     * @param annotation command annotation
     * @param command    command to check
     * @return true if command matches any alias
     */
    private static boolean isAliasMatch(Command annotation, String command) {
        for (String alias : annotation.aliases()) {
            if (alias.equals(command)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates that the number of parameters matches expectations.
     *
     * @param paramCount         actual parameter count
     * @param expectedParams     expected parameters (negative = "at least N")
     * @param command            command name for error messages
     * @throws IllegalArgumentException if validation fails
     */
    private static void validateParameterCount(int paramCount, int expectedParams, String command) 
            throws IllegalArgumentException {
        if (expectedParams >= 0) {
            if (paramCount != expectedParams) {
                throw new IllegalArgumentException(
                    String.format("Command '%s' expects %d parameters, got %d",
                                  command, expectedParams, paramCount));
            }
        } else {
            int minParams = -expectedParams;
            if (paramCount < minParams) {
                throw new IllegalArgumentException(
                    String.format("Command '%s' expects at least %d parameters, got %d",
                                  command, minParams, paramCount));
            }
        }
    }

    /**
     * Prints help information for all available commands.
     */
    private static void printHelp() {
        System.out.println("\n=== Available Commands ===\n");

        Class<?> operationsClass = MathOperations.class;
        for (Method method : operationsClass.getMethods()) {
            Command annotation = method.getAnnotation(Command.class);
            if (annotation == null) {
                continue;
            }

            System.out.println("Command: " + method.getName());
            System.out.println("  Parameters: " + 
                (annotation.numExpectedParams() < 0 
                    ? "at least " + (-annotation.numExpectedParams()) 
                    : annotation.numExpectedParams()));
            
            if (annotation.aliases().length > 0) {
                System.out.println("  Aliases: " + String.join(", ", annotation.aliases()));
            }
            
            System.out.println("  Description: " + annotation.desc());
            System.out.println();
        }
    }

    /**
     * Runs the unit test suite.
     * Note: Requires JUnit to be on the classpath (available via Maven).
     */
    private static void runTests() {
        try {
            // Use reflection to load JUnit dynamically
            Class<?> junitCore = Class.forName("org.junit.runner.JUnitCore");
            Class<?> testClass = Class.forName("com.mathcalc.Tests");
            
            Method runClasses = junitCore.getMethod("runClasses", Class.class);
            Object result = runClasses.invoke(null, testClass);

            Method getRunCount = result.getClass().getMethod("getRunCount");
            Method getFailureCount = result.getClass().getMethod("getFailureCount");
            Method getFailures = result.getClass().getMethod("getFailures");

            int total = (Integer) getRunCount.invoke(result);
            int failures = (Integer) getFailureCount.invoke(result);
            int successes = total - failures;

            System.out.println("\n=== Test Results ===");
            System.out.println("Status: " + (failures == 0 ? "✓ PASSED" : "✗ FAILED"));
            System.out.println("Total Tests: " + total);
            System.out.println("Passed: " + successes);
            System.out.println("Failed: " + failures);

            if (failures > 0) {
                System.out.println("\nFailures:");
                java.util.List<?> failureList = (java.util.List<?>) getFailures.invoke(result);
                for (Object failure : failureList) {
                    System.out.println("  - " + failure.toString());
                }
            }
            System.out.println();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: JUnit not available on classpath.");
            System.out.println("To run tests, build with Maven: mvn test");
        } catch (Exception e) {
            System.out.println("Error running tests: " + e.getMessage());
        }
    }
}
