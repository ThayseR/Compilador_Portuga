package br.portuga.inter;

import br.portuga.symbols.Type;

public class Enquanto extends CmdList{
	Expr expr;
	CmdList cmd;
	
	//Cria n� com filhos nulos
	public Enquanto(){
		expr = null;
		cmd = null;
	}
	
	//Fun��o que inicializa a express�o e a lista de comandos
	public void init(Expr x, CmdList c){
		expr = x;
		cmd = c;
		if(expr.type != Type.Bool) expr.error(" booleano � necess�rio em Enquanto");
	}
	
	
	//Gera instru��o de tr�s endere�os
	public void gen(int b, int a){
		after = a; //guarda o r�tulo a
		expr.jumping(0, a);
		int label = newLabel(); //gera um r�tulo para o comando
		emitLabel(label);
		cmd.gen(label, b);
		emit("goto L"+ b);
		
	}

}
