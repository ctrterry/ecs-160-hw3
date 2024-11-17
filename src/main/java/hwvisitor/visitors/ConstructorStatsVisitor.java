package hwvisitor.visitors;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class ConstructorStatsVisitor extends ASTVisitor {
    private int constructorsVisitedCount = 0;
    private int privateConstructorsVisitedCount = 0;

    @Override
    public boolean visit(MethodDeclaration node) {
        // Check if the node is a constructor
        if (node.isConstructor()) {
            constructorsVisitedCount++;
            
            // Check if the constructor is private by examining the modifiers list
            List<?> modifiers = node.modifiers();
            for (Object mod : modifiers) {
                if (mod instanceof Modifier && ((Modifier) mod).isPrivate()) {
                    privateConstructorsVisitedCount++;
                    break; // Only count once if private
                }
            }
        }
        return false; // No need to visit further nodes within the constructor
    }

    public int getConstructorsVisitedCount() {
        return constructorsVisitedCount;
    }

    public int getPrivateConstructorsVisitedCount() {  
        return privateConstructorsVisitedCount;
    }

    public int getPrivateConstructorsVisited() {  
        return privateConstructorsVisitedCount;
    }
    
}
