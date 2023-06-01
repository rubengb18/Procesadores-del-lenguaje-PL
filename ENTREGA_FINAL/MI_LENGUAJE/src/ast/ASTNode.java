package ast;

import tablasimbolos.TablaSimbolos;

public interface ASTNode {
    public Tipo type();
    public void bind(TablaSimbolos tabla);
    public String generateCode();
    public NodeKind nodeKind();
    public String toString();
}
