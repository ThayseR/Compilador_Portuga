package br.portuga.inter;

import br.portuga.lexer.Token;

public class Or extends Logical{
	public Or(Token tok, Expr x1, Expr x2){
		super(tok, x1, x2);
	}
	
	//gera código de desvio para uma expressão booleana B=B1||B2
	public void jumping(int t, int f){
		int label = t != 0? t : newLabel(); //se t for 0 entao label é definido como um novo rótulo
		expr1.jumping(label, 0);
		expr2.jumping(t, f);
		if(t == 0) emitLabel(label);
	}
	

}
