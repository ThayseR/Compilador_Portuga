package br.portuga.lexer;

public class Token {
	
	//o que é comum a todos os tokens é um tag identificador
	
	public final int tag;
	public Token(int t){
		tag = t;
	}
	
	//para imprimir
	public String toString(){
		return  " " + (char) tag;
	}

}
