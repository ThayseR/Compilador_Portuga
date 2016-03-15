package br.portuga.lexer;

public class Token {
	
	//o que � comum a todos os tokens � um tag identificador
	
	public final int tag;
	public Token(int t){
		tag = t;
	}
	
	//para imprimir
	public String toString(){
		return  " " + (char) tag;
	}

}
