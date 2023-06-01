package ast;

public abstract class INI implements ASTNode{
	public abstract KindIni kind();
    public TDEF bloq1() {throw new UnsupportedOperationException("bloq1");} 
    public STRUCT bloq2() {throw new UnsupportedOperationException("bloq2");} 
    public D bloq3() {throw new UnsupportedOperationException("bloq3");} 
    public I bloq4() {throw new UnsupportedOperationException("bloq4");} 
    public NodeKind nodeKind() {return NodeKind.TIPO;}
    public String toString() {return "";}

}
