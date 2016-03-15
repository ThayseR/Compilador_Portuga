package br.portuga.inter;

public class Imprimir extends CmdList{
	Expr expr;
	
	public Imprimir(Expr x){
		expr = x;
	}
	
	public void gen(int b, int a){
		emit("printf(" + expr.gen().toString()+ ")");
	}

}
