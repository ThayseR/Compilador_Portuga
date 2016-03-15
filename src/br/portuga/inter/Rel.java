package br.portuga.inter;

import br.portuga.lexer.Token;
import br.portuga.symbols.Type;

public class Rel extends Logical{
	public Rel(Token tok, Expr x1, Expr x2){
		super(tok,x1,x2);
	}
	
	public Type check(Type p1, Type p2){
		if(p1 == p2) return type.Bool;
		else return null;
	}
	
	public void jumping(int t, int f){
		Expr a = expr1.reduce(); //gera codigo para a subexpress�o x
		Expr b = expr2.reduce(); // gera codigo para a subexpress�o y
		String test = a.toString()+ " " + op.toString() + " " + b.toString();
		
		//emitJumps est� na classe Expr.java se t e f forem diferentes de 0 escreve o test e uma instru��o de desvio
		//Se t ou f forem 0 gera uma nova instru��o
		emitJumps(test, t, f);
	}

}
