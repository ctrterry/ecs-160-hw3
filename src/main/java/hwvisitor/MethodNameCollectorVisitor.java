package hwvisitor;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class MethodNameCollectorVisitor extends ASTVisitor {
    private Set<String> publicMethodNames = new HashSet<>();

    @Override
    public boolean visit(MethodDeclaration node) {
        // Check if the method is public
        if (Modifier.isPublic(node.getModifiers())) {
            // Use MethodUtil to get the method name
            publicMethodNames.add(MethodUtil.getMethodName(node));
        }
        return false;
    }

    public Set<String> getPublicMethodNames() {
        return publicMethodNames;
    }
}
