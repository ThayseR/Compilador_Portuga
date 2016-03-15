package br.portuga.inter;

import br.portuga.symbols.Type;

public class Se extends CmdList{
	Expr expr;
	CmdList cmdlist;
	
	//Constr�i o n� do comando Se
	public Se(Expr x, CmdList cmd){
		expr = x;
		cmdlist = cmd;
		
		if(expr.type != Type.Bool) expr.error("booleano � necess�rio em comando Se");
	}
	
	//gera instru��o de tr�s endere�os para comando Se
	public void gen(int b, int a){
		int label = newLabel(); //r�tulo do c�digo para CmdList
		expr.jumping(0, a); //segue se for true, vai para a se for false
		emitLabel(label);
		cmdlist.gen(label, a);
	}

}
