package br.portuga.inter;

import br.portuga.symbols.Type;

/*
 * Classe onde é feita a atribuição de um valor a um id, ou seja, no portuga ela implementa o <-
 */
public class Set extends CmdList{
	public Id id;
	public Expr expr;
	
	public Set(Id i, Expr x){
		id = i;
		expr = x;
		
		//faz a verificação se os tipos são compatíveis
		if(check(id.type, expr.type) == null) error("erro de tipos");
	}
	
	public Type check(Type p1, Type p2){
		if(Type.numeric(p1) && Type.numeric(p2)) return p2;
		else if(p1 == Type.String && p2 == Type.String) return p2;
		else if(p1 == Type.Bool && p2 == Type.Bool) return p2;
		else return null;
	}
	
	
	//Gera a instrução de três endereços
	public void gen(int b, int a){
		emit(id.toString() + " = " + expr.gen().toString());
	}

}
