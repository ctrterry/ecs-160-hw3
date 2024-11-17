ECS 160 HW-Visitor

See description on canvas.

You are to do this assignment independently.

There are 3 parts to the assignment. Points may be deducted for not following submission requirements.

The goal of this assignment is to help you understand the VISITOR pattern. To do this, you will implement a script to analyze source code from Homework 1 for correct implementations of the SINGLETON and STATE patterns. Code for parsing the source code into an Abstract Syntax Tree (AST) is provided in ParserUtil.java. You will implement visitors to traverse this tree looking for specified properties.

You should do the homework by yourself. We will be using tools to look for plagiarism. The assignment grade will be the weighted average of the score on the homework, and the (possible) related pop quiz, which will be given in the lecture.

The signatures of all public methods provided in the starter files shall remain unchanged (with the exception of renaming your StudentTest methods). Additional private methods are permitted (and encouraged when it aids readability/code-reuse!). Additional public or protected methods are permitted, but may not be relied on for submitted StudentTest.

Problem 1: MethodInvocationPrinter
Take a look at visitors/MethodInvocationPrinter.java. It provides a visitor that writes to standard out every time a method call is visited.

This is demonstrated in App.java. You can also see this tested TestMethodInvocationPrinter.java

However, there is a small bug in the provided implementation. If there are any nested method invocations they are not printed. For example in:

System.out.println(“sqrt(4) = “ + Math.sqrt(4));

the invocation of the sqrt function is not printed.

Please correct this bug. It should only require a change to a single line of code. (3 points). If you’re confused about how to do this, carefully examine the other visitor examples provided, and look at associated documentation (in VSCode if you hover over a symbol like ASTVisitor or visit it will show you the documentation. You can also CTRL/CMD click to go to the source).

As an added feature the visitor accepts a “favoriteMethodName” property, where if it visits an invocation of that method name, it is supposed to print “!!! {favoriteMethodName} !!!”

However, this also seems to not be working due to a common mistake for new Java programmers. By only changing a single you line you should be able to fix this bug (1 point)

Problem 2: SingletonChecker
16 points
Implement a class SingletonChecker which exposes the following methods:

public static boolean checkPrivateConstructor(ASTNode ast)
Returns true iff ast contains at least one private constructor and no additional non-private constructors
Hint: Make sure to check the requirement below for the ConstructorStatsVisitor
public static boolean checkInstanceGetter(ASTNode ast)
Returns true iff the checked AST contains a method that has the following properties:
is marked public
is marked static
has return type with same name as the parsed
Hint: Your visitor needs to know the class It can visit TypeDeclaration to find the class name and then descend to nodes inside the class. You may use TypeDeclaration.getName().getIdentifier() to represent the type name.
public static boolean checkPrivateStaticVarForInstance(ASTNode ast)
Returns true iff the checked AST has a private static instance variable matching the type of the parsed class
Hint: FieldDeclaration nodes and getType().toString() might be useful
public static boolean checkConstructorCalledCorrectly(ASTNode ast)
Returns true iff the constructor of the parsed AST class is called exactly once. This constructor call must be inside of an IfStatement’s then statement or else statement (we only consider lazy singletons valid for this However, looking whether the IfStatement’s expression is testing if the instance variable is null is not part of this check)
Hint: This can be tricky to get right, so may want to save this part for You will likely find visiting the ClassInstanceCreation to be useful along with ClassInstanceCreation.getType().toString(). Also, you may consider having more than one visitor, where the `visit` method of one visitor uses another to visit all the descendants.
public static boolean checkCouldBeSingelton(ASTNode ast)
Returns true iff all of the previous properties are
There are several valid ways to implement such a checker, all with tradeoffs. If one is tightly optimizing for performance, one might prefer all these properties to be checked in a single visitor so only need to make a single pass over the AST. However, we are designing our API focusing on ease-of-testing and simplicity, so likely prefer several visitors.

ConstructorStatsVisitor
Only one visitor is exactly prescribed by this specification. You must include an ASTVistor named ConstructorStatsVisitor. It shall support the following interface:

public int getConstructorsVisitedCount()
returns the number of times a constructor of any protection level is visited
public int getPrivateConstructorsVisitedCount()
returns the number of times a private constructor is visited Public methods inherited from superclass are not listed.
Hint: MethodDeclaration.isConstructor() and NodeUtil.getKeywordModsFromDeclaration() might be useful. The Modifier’s returned have methods like isStatic() and isPrivate(). A MethodDeclaration is a subclass of BodyDeclaration.

Other visitors can be defined arbitrarily. In this case we define a visitor that calculates useful statistics that can then be checked for the desired property. However, it is also valid (and in some cases might be preferred) to make the visitor just expose a boolean.

Points for Problem 2 will roughly be three points for each of the SingletonChecker methods and one point for the ConstructorStatsVisitor (but these methods relate to each other)

Problem 3: StatePatternChecker
In this section we will implement a checker for some of the properties of the STATE pattern from homework 1. Implement a class StatePatternChecker that supports the following method:

public boolean checkContextHasMatchingMethodNames( ASTNode context,ASTNode abstractState)
Returns true iff for each public method defined in the abstractState, there exists a public method with an equal name defined in the context.
We consider the getMethodName() to represent the name of the method
Hint: Using a data structure such as a HashSet might be natural You could implement a visitor that gathers a Set of public method names. Then you find the set differences with something along the lines of abstractStateMethodNameSet.removeAll(contextMethodNameSet).isEmpty()
Just an idea. Other equivalent implementations are valid.

A more complete checker might also look for whether the CONTEXT contains an instance variable of the state and examine method invocations to ensure forwarding is done correctly. However, we won’t worry about this for this homework. By this point you hopefully have a good understanding of visitors.

Testcase Submission
You are encouraged to test your code with automated testing as the provided test suite is not guaranteed to be comprehensive.

You are required to submit exactly two test cases for us to grade. Please place these as a sibling of the other tests in `StudentTest.java` that contains a class named `StudentTest`. This should have exactly two Junit5 tests. You may write other tests for yourself in other files, but if more than two tests are supplied in StudentTest.java, we will choose two arbitrarily, and apply a penalty.

Each test should not attempt to test all aspects of the assignment. Instead, you are encouraged to submit a unit test that tests a specific assertion you have about a specific part of the assignment. The testcases should pass on any valid solution and test a non-trivial condition (so no assertTrue(true)).

Code Style
It is important to think about the quality of code you write. One component of this is following the style conventions of a language or of your organization.

We will adapt a relaxed version of the Google Java StyleLinks to an external site. guide. Notably, we do not enforce the requirements around indentation, package name, import order, or public javadocs (though javadocs is sometimes conspractice. We may be more strict in future assignments).

We provide a CheckstyleLinks to an external site. xml file with the starter files (google_checks_custom.xml). Submissions that pass a run of Checkstyle without any warnings will be awarded max points. Partial points will be given if you only have a few warnings.

Submission
Please zip your submission together. The format should be in similar form to the starter files in that it contains the entire project structure and unzips into a single directory.

FAQ/minutia
Why won’t my code build?
You might need to apply similar steps to what you did in HW1 to get the code to run locally. If you need help, you can post on piazza or come to office hours. Be sure to include information about your system and your `$ java --version`.

What happens if checkers are given code which is not syntactically valid Java 12 code? This behavior is undefined and will not be tested. However, since we rely on the string representation for types and names, unresolved symbol errors should be fine to trust whatever the AST gives you.

What happens if checkers are given an AST with multiple class definitions?
Behavior is only defined if there is exactly one TypeDeclaration in the AST. Other behavior is undefined and will not be tested.

I created a Singleton/State implementation that is not detected by following the definition given in these checkers. Also, I made a sneaky non-singleton which the checker thinks is a singleton. Should I change my checker to do what I think is right?
The truth conditions of methods should be exactly as described. The given conditions can detect many implementations of the pattern without adding excessive complexity. However, as with many static analysis problems, it is possible for there be both false negatives and false positives.

How do I run checkstyle?
Download checkstyle and follow the documentation. This will likely look something like running a command like:

java -jar <path_to_jar>/checkstyle-9.0.1-all.jar -c ./google_checks_custom.xml src/

You also may be able to configure your editor to show linter warnings inside the editor.

Can these properties be done with Java reflection?
The goal of the assignment is to explore the Visitor Pattern (plus gain some familiarity with concepts like AST’s). While it might be possible to check some (but likely not all) of the required properties with java.lang.reflection, use of these API’s is prohibited for the assignment.

Can I use ASTNode.getParent() or otherwise manually transverse the tree?
You are required to use ASTVisitor implementations for this homework, since we want you to learn how to use this widely used design pattern. Manual traversals should be used as little as possible. Using visitors you should not need to manually transverse the AST.

When comparing symbol names do I have to worry about resolution, fully qualified names, or shadowing?
No. We rely on the toString representation of types being reliable for our comparisons.

Do I have to reason about runtime behavior (like the truth value of IfStatement expressions or loop/recursion behavior)?
No. All analysis is done statically based on the presence of ASTNode’s. You do not have to reason about unreachable code or other runtime properties. In the general case these are undecidable.

I passed the supplied test cases, am I good?
Probably for the most part. However, we will also check correctness with additional tests.

Can I add new files?
You may add new source files. Additional test files are allowed, but only StudentTest.java will be evaluated. Please do not rely on reading auxiliary files from the file system (while it can be nice to place files you are performing static analysis on in their own files, for our purposes it will be easier to not have to deal with that). Please do not add any new jars or maven dependencies.

How can I ask other questions?
Please ask in the Discussion forum, during the Discussion section, or in office hours (check on canvas for times and locations). Unless needed, do not email us questions about the assignment which would be better placed on the Discussion Forum so that everyone can benefit from the question/answer.

Version History
V001 - Initial release for FQ24 - expect bugs