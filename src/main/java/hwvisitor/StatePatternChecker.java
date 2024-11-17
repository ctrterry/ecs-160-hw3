package hwvisitor;

import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

public final class StatePatternChecker {
    private StatePatternChecker() {}

    public static boolean checkContextHasMatchingMethodNames(
        ASTNode context, 
        ASTNode abstractState
    ) {
        // Collect public method names from abstractState
        MethodNameCollectorVisitor abstractStateVisitor = new MethodNameCollectorVisitor();
        abstractState.accept(abstractStateVisitor);
        Set<String> abstractStateMethodNames = abstractStateVisitor.getPublicMethodNames();

        // Collect public method names from context
        MethodNameCollectorVisitor contextVisitor = new MethodNameCollectorVisitor();
        context.accept(contextVisitor);
        Set<String> contextMethodNames = contextVisitor.getPublicMethodNames();

        // Check if all abstractState methods are present in context
        return contextMethodNames.containsAll(abstractStateMethodNames);
    }
}
