package br.portuga.inter;


/*
 * Implementa uma sequencia de comandos
 */
public class Seq extends CmdList{
	CmdList cm1, cm2;
	
	public Seq(CmdList c1, CmdList c2){
		cm1 = c1;
		cm2 = c2;
	}
	
	
	//gera intrução de 3 endereços
	public void gen(int b, int a){
		if(cm1 == CmdList.Null) cm2.gen(b, a);
		else if(cm2 == CmdList.Null) cm1.gen(b, a);
		else{
			int label = newLabel();
			cm1.gen(b, label);
			emitLabel(label);
			cm2.gen(label, a);
		}
	}

}
