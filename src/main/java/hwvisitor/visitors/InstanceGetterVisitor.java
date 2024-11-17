package hwvisitor.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class InstanceGetterVisitor extends ASTVisitor {
    private boolean instanceGetterFound = false;
    private String className;

    @Override
    public boolean visit(TypeDeclaration node) {
        className = node.getName().toString();
        return true;
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        if (Modifier.isPublic(node.getModifiers()) && Modifier.isStatic(node.getModifiers())) {
            if (node.getReturnType2() != null && node.getReturnType2().toString().equals(className)) {
                instanceGetterFound = true;
            }
        }
        return false;
    }

    public boolean isInstanceGetterFound() {
        return instanceGetterFound;
    }
}
