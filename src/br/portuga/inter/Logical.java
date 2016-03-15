package br.portuga.inter;

import br.portuga.lexer.Token;
import br.portuga.symbols.Type;

public class Logical extends Expr{
	public Expr expr1, expr2; //correspondem aos operandos de um operador lógico
	
	//Constrói um nó de sintaxe com operador tok e operandos x1 e x2
	Logical(Token tok, Expr x1, Expr x2){
		super(tok, null); //começa com tipo nulo
		expr1 = x1; 
		expr2 = x2;
		type = check(expr1.type, expr2.type);
		if (type == null) error(" erro de tipos");
	}
	
	//Confere se os operandos são booleanos
	public Type check(Type p1, Type p2){
		if(p1 == Type.Bool && p2 == Type.Bool) return Type.Bool;
		else return null;
	}
	
	public Expr gen(){
		int f = newLabel();
		int a = newLabel();
		Temp temp = new Temp(type);
		this.jumping(0, f);
		emit(temp.toString() + " = true");
		emit("goto L"+ a);
		emitLabel(f);
		emit(temp.toString() + " = false");
		emitLabel(a);
		return temp;
	}
	
	public String toString(){
		return expr1.toString() + " " + op.toString() + " " + expr2.toString();
	}

}
