package br.portuga.inter;

import br.portuga.lexer.Token;
import br.portuga.symbols.Type;


/*
 * A classe Op oferece uma implementa��o de reduce que � herdada pelas subclasses Arith para operadores
 * aritm�ticos, Unary para operadores un�rios e Access para acessos a arranjo.
 */
public class Op extends Expr{
	public Op(Token tok, Type p){
		super(tok, p);
	}
	public Expr reduce(){
		Expr x = gen(); // gen � chamado para gerar um termo
		Temp t = new Temp(type); //atribui o termo a um novo nome tempor�rio
		emit(t.toString()+" = " + x.toString());
		return t; //retorna o temporario 
	}

}
