# Visitor Pattern & AST Analysis

---

## Project Overview

This project provides practical experience in implementing and verifying software design patterns (**Singleton** and **State**) using the **Visitor Pattern** for static analysis in Java. Through this assignment, I've developed a deeper understanding of Abstract Syntax Trees (**AST**) by implementing visitors that systematically analyze Java source code.

---

## Learning Objectives

- **Visitor Pattern Implementation**: Utilizing visitors to perform structured and efficient AST traversal.
- **Static Analysis**: Analyzing code structures programmatically to verify adherence to design pattern principles.
- **Software Design Patterns**: Recognizing and verifying correct implementations of Singleton and State patterns.
- **JUnit Testing**: Writing precise and targeted unit tests to validate implementation correctness.
- **Code Quality Assurance**: Applying coding standards with Checkstyle integration.

---

## Project Details

### Problem 1: Method Invocation Analysis
- Corrected visitor logic to handle nested method invocations.
- Enhanced visitor functionality to highlight specified method calls.

### Problem 2: Singleton Pattern Verification
Implemented `SingletonChecker` to verify:
- Private constructor presence.
- Public static instance getter method correctness.
- Existence and correctness of private static instance variables.
- Proper singleton instantiation logic.
- `ConstructorStatsVisitor` to statistically analyze constructor visibility.

### Problem 3: State Pattern Validation
- Implemented `StatePatternChecker` to ensure method consistency between abstract state and context classes.

---

## Testing Approach
- Provided targeted JUnit5 tests (`StudentTest.java`) focusing on critical aspects of the assignment.
- Prioritized unit tests that isolate and validate specific functionalities.

---

## Project Structure
```
project_root/
├── src/
│   ├── visitors/
│   │   ├── MethodInvocationPrinter.java
│   │   ├── SingletonChecker.java
│   │   ├── ConstructorStatsVisitor.java
│   │   └── StatePatternChecker.java
│   ├── ParserUtil.java
│   └── StudentTest.java
├── checkstyle/
│   └── google_checks_custom.xml
└── README.md
```

---

## Running the Project

### Environment
Ensure Java 12 or later:
```bash
java --version
```

### Checkstyle
Verify code quality:
```bash
java -jar checkstyle.jar -c ./google_checks_custom.xml src/
```

---

## Future Work

- Expand static analysis to detect other design patterns.
- Automate detailed reporting of static analysis results.

---

## License

Licensed under MIT – see [LICENSE](LICENSE) for details.

---

**[Back to Top](#visitor-pattern--ast-analysis)**

