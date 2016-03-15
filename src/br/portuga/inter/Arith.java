package br.portuga.inter;

import br.portuga.lexer.Token;
import br.portuga.symbols.Type;

/*
 * Implementa operadores bin�rios como + e *.
 */
public class Arith extends Op{
	public Expr expr1, expr2;
	
	public Arith(Token tok, Expr x1, Expr x2){
		super(tok, null); //tok � o token representado pelo operador e null � o marcador de lugar para tipo
		expr1 = x1;
		expr2 = x2;
		type = Type.max(expr1.type, expr2.type); //o tipo � determinado, verifica se os dois operandos podem ser convertidos para um tipo num�rico comum
		if(type == null) error("erro de tipos");
	}
	
	//M�todo gen() constr�i o lado direito de uma instru��o de tr�s endere�os, reduzindo as subexpress�es para
	//endere�os e aplicando o operador aos endere�os
	public Expr gen(){
		return new Arith(op, expr1.reduce(), expr2.reduce());
	}
	
	public String toString(){
		return expr1.toString() + " " + op.toString() + " " + expr2.toString();
	}

}
