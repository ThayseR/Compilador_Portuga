package br.portuga.inter;

import br.portuga.symbols.Type;

public class Itere extends CmdList{

	Expr expr;
	CmdList cmd;
	
	//Cria nó com filhos nulos
	public Itere(){
		expr = null;
		cmd = null;
	}
	
	//Função que inicializa a expressão e a lista de comandos
	public void init(Expr x, CmdList c){
		expr = x;
		cmd = c;
		if(expr.type != Type.Int) expr.error(" valor inteiro é necessário no comando Itere");
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
