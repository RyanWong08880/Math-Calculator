# Math Calculator

A command-line mathematical calculation tool built with Java and Maven.

## Features

- **System of Linear Equations**: Solve 2x2 and 3x3 systems
- **Quadratic Formula**: Solves quadratic equations (handles real and complex roots)
- **Quadratic Form Conversion**: Converts standard form to vertex form
- **Synthetic Division**: Performs polynomial synthetic division
- **Binomial Theorem**: Expands binomial expressions
- **Interactive CLI**: Command-line interface with help system
- **Comprehensive Tests**: Full unit test suite with JUnit

## Project Structure

```
.
├── src/
│   ├── main/java/com/mathcalc/
│   │   ├── Main.java                 # CLI entry point and command dispatcher
│   │   ├── MathOperations.java       # All mathematical operations
│   │   ├── Command.java              # @Command annotation
│   │   └── MethodNotFoundException.java # Custom exception
│   └── test/java/com/mathcalc/
│       └── Tests.java                # Unit tests
├── pom.xml                           # Maven configuration
├── .gitignore                        # Git ignore rules
└── README.md                         # This file
```

## Building

### Prerequisites
- Java 11 or higher
- Maven 3.6.0 or higher

### Build Steps

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package application
mvn package

# Build uber JAR (all dependencies included)
mvn assembly:assembly
```

## Running

### Using Maven
```bash
mvn exec:java -Dexec.mainClass="com.mathcalc.Main"
```

### Using JAR
```bash
java -cp target/math-calculator-1.0.0.jar com.mathcalc.Main
```

## Available Commands

### System of Two Equations (2x2)
Solves: `ax + by = c` and `dx + ey = f`

**Usage:** `systemOfTwoEquationsAndTwoVariables a b c d e f` or `sys2 a b c d e f`

**Example:**
```
> sys2 3 1 15 1 2 10
Result: (4.0, 3.0)
```

### Quadratic Formula
Solves: `ax² + bx + c = 0`

**Usage:** `quadraticFormula a b c` or `quad a b c`

**Example:**
```
> quad 1 2 1
Result: -1.0, -1.0
```

### System of Three Equations (3x3)
Solves: `ax + by + cz = d`, `ex + fy + gz = h`, `ix + jy + kz = l`

**Usage:** `systemOfThreeEquationsAndThreeVariables a b c d e f g h i j k l` or `sys3 ...`

### Quadratic Standard to Vertex Form
Converts: `y = ax² + bx + c` to `y = a(x - h)² + k`

**Usage:** `quadraticStandardToVertexForm a b c` or `tovertex a b c`

**Example:**
```
> tovertex 1 9 8
Result: y = 1.0(x + 4.5) ^ 2 + -12.25
```

### Synthetic Division
Performs polynomial division by `(x - a)`

**Usage:** `syntheticDivision a coeff1 coeff2 ...` or `syn a coeff1 coeff2 ...`

**Example:**
```
> syn -7 1 4 56 43 -42
Result: [1.0, -3.0, 77.0, -496.0, 3430.0]
```

### Binomial Theorem
Expands: `(a + b)^n`

**Usage:** `binomialTheorem n` or `pascal n` or `binom n`

**Example:**
```
> binom 2
Result: 1(a^2)(b^0) + 2(a^1)(b^1) + 1(a^0)(b^2)
```

### Other Commands
- `help`, `?`, `h` - Display help information
- `test`, `runtests` - Run unit tests
- `quit`, `exit` - Exit the application

## Code Quality Improvements

Recent refactoring includes:

✓ **Package Structure**: Organized code into `com.mathcalc` package  
✓ **Separation of Concerns**: Mathematical operations separated from CLI logic  
✓ **Documentation**: Comprehensive Javadoc comments throughout  
✓ **Exception Handling**: Custom exceptions and improved error messages  
✓ **Performance**: Optimized binomial coefficient calculation with caching  
✓ **Testing**: All commands covered by unit tests  
✓ **Build System**: Proper Maven configuration with plugins  
✓ **Naming**: Clear, descriptive method and variable names  

## Testing

Run all tests:
```bash
mvn test
```

Or run tests from within the application:
```
> test
```

Current test coverage:
- 7 tests for 2x2 systems
- 6 tests for quadratic formula
- 2 tests for 3x3 systems
- 5 tests for standard-to-vertex form
- 4 tests for synthetic division
- 4 tests for binomial theorem

**Total: 28 unit tests**

## Dependencies

- **JUnit 4.13.2** - Testing framework (test scope)

## Author Notes

The calculator uses reflection to dynamically discover and execute commands, allowing for easy extension of functionality without modifying the dispatcher logic.

Mathematical operations are implemented with appropriate algorithms:
- Gaussian elimination for linear systems
- Quadratic formula for polynomial roots
- Pascal's triangle with caching for binomial expansion
