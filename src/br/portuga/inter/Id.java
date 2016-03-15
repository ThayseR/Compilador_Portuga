package br.portuga.inter;

import br.portuga.lexer.Word;
import br.portuga.symbols.Type;

/*
 * herda as implementações default de gen e reduce na classe Expr, porque um identificador 
 * é um endereço. O nó para o identificador da classe ID é uma folha
 */
public class Id extends Expr{
	public int offset; // endereço relativo
	public Id(Word id, Type p, int b){
		super(id, p); //guarda id e p nos campos herdados op e type
		offset = b; //endereço relativo deste identificador
	}

}
