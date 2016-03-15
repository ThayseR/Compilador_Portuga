package br.portuga.inter;

import br.portuga.symbols.Type;

public class Se extends CmdList{
	Expr expr;
	CmdList cmdlist;
	
	//Constrói o nó do comando Se
	public Se(Expr x, CmdList cmd){
		expr = x;
		cmdlist = cmd;
		
		if(expr.type != Type.Bool) expr.error("booleano é necessário em comando Se");
	}
	
	//gera instrução de três endereços para comando Se
	public void gen(int b, int a){
		int label = newLabel(); //rótulo do código para CmdList
		expr.jumping(0, a); //segue se for true, vai para a se for false
		emitLabel(label);
		cmdlist.gen(label, a);
	}

}
