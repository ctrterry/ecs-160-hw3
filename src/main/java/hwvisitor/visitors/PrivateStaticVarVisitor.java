package hwvisitor.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class PrivateStaticVarVisitor extends ASTVisitor {
    private boolean privateStaticVarFound = false;
    private String className;

    @Override
    public boolean visit(TypeDeclaration node) {
        className = node.getName().toString();
        return true;
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        if (Modifier.isPrivate(node.getModifiers()) && Modifier.isStatic(node.getModifiers())) {
            if (node.getType().toString().equals(className)) {
                privateStaticVarFound = true;
            }
        }
        return false;
    }

    public boolean isPrivateStaticVarFound() {
        return privateStaticVarFound;
    }
}
