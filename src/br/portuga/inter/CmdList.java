package br.portuga.inter;


/*
 * A construção dos comandos é implementada por uma subclasse de CmdList
 */
public class CmdList extends Node{
	//Constrói a arvore de sintaxe
	public CmdList(){} //o trabalho será feito nas subclasses
	public static CmdList Null = new CmdList(); //representa uma sequencia vazia de comandos
	
	//Daqui em diante trata a geração de código de três endereços
	public void gen(int b, int a){} //chamado como rótulo begin e after onde b marca o início do código para esse
									//comando e a marca a primeira instrução após o código para esse comando
	int after = 0; //guarda rótulo after
	
	public static CmdList Enclosing = CmdList.Null; //usado para comandos break
	

}
