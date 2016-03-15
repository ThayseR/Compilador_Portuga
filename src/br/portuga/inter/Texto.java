package br.portuga.inter;

import br.portuga.lexer.Token;
import br.portuga.symbols.Type;


public class Texto extends Expr{
	
		public Texto(Token tok, Type p){
			super(tok, p); //guarda id e p nos campos herdados op e type
			
		}

}
