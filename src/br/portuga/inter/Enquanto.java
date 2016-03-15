package br.portuga.inter;

import br.portuga.symbols.Type;

public class Enquanto extends CmdList{
	Expr expr;
	CmdList cmd;
	
	//Cria nó com filhos nulos
	public Enquanto(){
		expr = null;
		cmd = null;
	}
	
	//Função que inicializa a expressão e a lista de comandos
	public void init(Expr x, CmdList c){
		expr = x;
		cmd = c;
		if(expr.type != Type.Bool) expr.error(" booleano é necessário em Enquanto");
	}
	
	
	//Gera instrução de três endereços
	public void gen(int b, int a){
		after = a; //guarda o rótulo a
		expr.jumping(0, a);
		int label = newLabel(); //gera um rótulo para o comando
		emitLabel(label);
		cmd.gen(label, b);
		emit("goto L"+ b);
		
	}

}
