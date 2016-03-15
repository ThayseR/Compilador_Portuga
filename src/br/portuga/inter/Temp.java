package br.portuga.inter;

import br.portuga.lexer.Word;
import br.portuga.symbols.Type;

/*
 * Os nomes tempor�rios tem tipo como todas as outras express�es
 */
public class Temp extends Expr{
	static int count = 0;
	int number = 0;
	public Temp(Type p){
		super(Word.temp, p);
		number = ++count;
	}
	public String toString(){
		return "t" + number;
	}

}
