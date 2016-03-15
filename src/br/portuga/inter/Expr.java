package br.portuga.inter;

import br.portuga.lexer.Token;
import br.portuga.symbols.Type;

/*
 * A classe Expr tem um operador e seu tipo respectivamente em um n�
 */
public class Expr extends Node{
	public Token op;
	public Type type;
	Expr(Token tok, Type p){
		op = tok;
		type = p;
	}
	
	/*
	 * O m�todo gen() retorna um 'termo' que pode caber do lado direito de um comando de tr�s endere�os
	 * Dada a express�o E = E1 + E2 o metodo gen retorna um termo x1+x2, onde x1 e x2 s�o endere�os para
	 * os valores de E1 e E2 respectivamente
	 */
	public Expr gen(){
		//o valor de retorno this � apropriado se esse objeto for um endere�o
		return this;
	}
	
	/*
	 * Calcula ou reduz uma express�o a um �nico endere�o, ou seja, retorna uma constante, um identificador
	 * ou um nome tempor�rio. Dada a express�o E o m�todo reduce retorna um tempor�rio t contendo o valor
	 * de E.
	 */
	public Expr reduce(){
		//o valor de retorno this � apropriado se esse objeto for um endere�
		return this;
	}
	
	/*
	 * Jumping e emitJumping geram c�digos de desvio para express�es booleanas
	 */
	public void jumping(int t, int f){
		emitJumps(toString(), t, f);
	}
	
	public void emitJumps(String test, int t, int f){
		//t e f s�o labels para true ou false
		if(t != 0 && f != 0){
			emit("if " + test + " goto L" + t);
			emit("goto L" + f);
		}
		else if(t != 0) emit("if " + test + " goto L" + t);
		else if(f != 0) emit("iffalse " + test + " goto L" + f);
		else; //nada pois f e t falharam
	}
	
	public String toString(){
		return op.toString();
	}

}
