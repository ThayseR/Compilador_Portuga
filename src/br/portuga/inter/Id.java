package br.portuga.inter;

import br.portuga.lexer.Word;
import br.portuga.symbols.Type;

/*
 * herda as implementa��es default de gen e reduce na classe Expr, porque um identificador 
 * � um endere�o. O n� para o identificador da classe ID � uma folha
 */
public class Id extends Expr{
	public int offset; // endere�o relativo
	public Id(Word id, Type p, int b){
		super(id, p); //guarda id e p nos campos herdados op e type
		offset = b; //endere�o relativo deste identificador
	}

}
