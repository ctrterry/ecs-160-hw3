package hwvisitor;

import org.eclipse.jdt.core.dom.ASTNode;

import hwvisitor.visitors.ConstructorCallVisitor;
import hwvisitor.visitors.ConstructorStatsVisitor;
import hwvisitor.visitors.InstanceGetterVisitor;
import hwvisitor.visitors.PrivateStaticVarVisitor;

public final class SingletonChecker {
    private SingletonChecker() {}

    public static boolean checkPrivateConstructor(ASTNode ast) {
        ConstructorStatsVisitor visitor = new ConstructorStatsVisitor();
        ast.accept(visitor);
        return visitor.getConstructorsVisitedCount() > 0 &&
               visitor.getPrivateConstructorsVisitedCount() == visitor.getConstructorsVisitedCount();
    }

    public static boolean checkInstanceGetter(ASTNode ast) {
        InstanceGetterVisitor visitor = new InstanceGetterVisitor();
        ast.accept(visitor);
        return visitor.isInstanceGetterFound();
    }

    public static boolean checkPrivateStaticVarForInstance(ASTNode ast) {
        PrivateStaticVarVisitor visitor = new PrivateStaticVarVisitor();
        ast.accept(visitor);
        return visitor.isPrivateStaticVarFound();
    }

    public static boolean checkConstructorCalledCorrectly(ASTNode ast) {
        ConstructorCallVisitor visitor = new ConstructorCallVisitor();
        ast.accept(visitor);
        return visitor.isConstructorCalledCorrectly();
    }

    public static boolean checkCouldBeSingleton(ASTNode ast) {
        return checkPrivateConstructor(ast)
            && checkInstanceGetter(ast)
            && checkPrivateStaticVarForInstance(ast)
            && checkConstructorCalledCorrectly(ast);
    }
}
