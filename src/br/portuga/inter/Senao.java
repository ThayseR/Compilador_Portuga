package br.portuga.inter;

import br.portuga.symbols.Type;

public class Senao extends CmdList{
	CmdList cmd1;
	Expr expr;
	CmdList cmd2;
	
	/*public Senao(){
		cmd1 = null;
		expr = null;
	}*/
	public Senao(Expr x, CmdList c1, CmdList c2){
		expr = x;
		cmd1 = c1;
		cmd2 = c2;
		
		if(expr.type != Type.Bool) expr.error("booleano é necessário em comando Se");
	}
	
	
	//gera instrução de três endereços
	public void gen(int b, int a){
		int label1 = newLabel();
		int label2 = newLabel(); //rótulo do código para CmdList
		expr.jumping(0, label2); //segue se for true, vai para a se for false
		emitLabel(label1);
		cmd1.gen(label1, a);
		emit("goto L" + a);
		emitLabel(label2);
		cmd2.gen(label2, a);

	}
	

}
