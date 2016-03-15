package br.portuga.inter;

import br.portuga.lexer.Lexer;

/*
 * Os n�s na �rvore sint�tica s�o implementados como objetos da classe Node
 */
public class Node {
	int lexline = 0;
	Node() {
		lexline = Lexer.line;
	}
	void error(String s){
		throw new Error("proximo a linha " + lexline + ": " + s);
	}
	
	//Daqui em diante � usado para a emiss�o de c�digos de tr�s endere�os
	static int labels = 0;
	
	//controla os labels de saltos que s�o dados por L#
	public int newLabel(){
		return ++labels;
	}
	
	//escreve o label
	public void emitLabel(int i){
		System.out.print("L" + i + ":");
	}
	
	//Escreve o comando a ser feito
	public void emit(String s){
		System.out.println("\t" + s);
	}

}
