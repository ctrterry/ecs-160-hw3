package hwvisitor.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ConstructorCallVisitor extends ASTVisitor {
    private boolean constructorCalledCorrectly = false;
    private String className;

    @Override
    public boolean visit(TypeDeclaration node) {
        className = node.getName().toString();
        return true;
    }

    @Override
    public boolean visit(IfStatement node) {
        node.getThenStatement().accept(new ClassInstanceCreationVisitor());
        if (node.getElseStatement() != null) {
            node.getElseStatement().accept(new ClassInstanceCreationVisitor());
        }
        return false;
    }

    private class ClassInstanceCreationVisitor extends ASTVisitor {
        @Override
        public boolean visit(ClassInstanceCreation node) {
            if (node.getType().toString().equals(className)) {
                constructorCalledCorrectly = true;
            }
            return false;
        }
    }

    public boolean isConstructorCalledCorrectly() {
        return constructorCalledCorrectly;
    }
}
