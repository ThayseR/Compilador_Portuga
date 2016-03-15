package br.portuga.inter;

import br.portuga.lexer.Token;
import br.portuga.symbols.Type;

/*
 * Implementa operadores binários como + e *.
 */
public class Arith extends Op{
	public Expr expr1, expr2;
	
	public Arith(Token tok, Expr x1, Expr x2){
		super(tok, null); //tok é o token representado pelo operador e null é o marcador de lugar para tipo
		expr1 = x1;
		expr2 = x2;
		type = Type.max(expr1.type, expr2.type); //o tipo é determinado, verifica se os dois operandos podem ser convertidos para um tipo numérico comum
		if(type == null) error("erro de tipos");
	}
	
	//Método gen() constrói o lado direito de uma instrução de três endereços, reduzindo as subexpressões para
	//endereços e aplicando o operador aos endereços
	public Expr gen(){
		return new Arith(op, expr1.reduce(), expr2.reduce());
	}
	
	public String toString(){
		return expr1.toString() + " " + op.toString() + " " + expr2.toString();
	}

}
