package br.portuga.inter;

/*
 * Classe para ler uma entrada e atribuir a um id
 */
public class Ler extends CmdList{
	
	Expr id;
	
	public Ler(Expr i){
		id = i;
	}
	
	public void gen(int b, int a){
		emit("scanf(" + id.toString()+ ")");
	}

}
