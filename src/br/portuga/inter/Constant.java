package br.portuga.inter;

import br.portuga.lexer.Num;
import br.portuga.lexer.Token;
import br.portuga.lexer.Word;
import br.portuga.symbols.Type;

public class Constant extends Expr{
	public Constant(Token tok, Type p){
		super(tok,p); //constrói uma folha da árvore de sintaxe
	}
	
	public Constant(int i){
		super(new Num(i), Type.Int); //Cria um objeto constante a partir de um inteiro
	}
	
	public static final Constant
		True = new Constant(Word.True, Type.Bool),
		False = new Constant(Word.False, Type.Bool);
	
	public void jumping(int t, int f){
		if(this == True && t != 0) emit("goto L" + t); //desvio se a expressao booleana verdadeira
		else if(this == False && f != 0) emit("goto L" + f); //desvio se a expressao booleana falsa
	}

}
