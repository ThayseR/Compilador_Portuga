package br.portuga.inter;

import br.portuga.lexer.Token;


public class Not extends Logical{
	public Not(Token tok, Expr x2){
		super(tok, x2, x2);
		
	}
	
	public void jumping(int t, int f){
		expr2.jumping(f,t); //como é not chama jumping com as entradas f e t invertidas
		
	}
	
	public String toString(){
		return op.toString()+ " " + expr2.toString();
	}
	

}
