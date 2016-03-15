package br.portuga.inter;

import br.portuga.lexer.Token;
import br.portuga.symbols.Type;


/*
 * é o correspondente de um operando da classe Arith
 */
public class Unary extends Op{
	public Expr expr;
	public Unary(Token tok, Expr x){
		super(tok, null);
		expr = x;
		type = type.max(Type.Int, expr.type);
		if(type == null) error("erro de tipos");
	}
	
	public Expr gen(){
		return new Unary(op, expr.reduce());
	}
	
	public String toString(){
		return op.toString() + " " + expr.toString();
	}

}
