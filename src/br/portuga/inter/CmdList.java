package br.portuga.inter;


/*
 * A constru��o dos comandos � implementada por uma subclasse de CmdList
 */
public class CmdList extends Node{
	//Constr�i a arvore de sintaxe
	public CmdList(){} //o trabalho ser� feito nas subclasses
	public static CmdList Null = new CmdList(); //representa uma sequencia vazia de comandos
	
	//Daqui em diante trata a gera��o de c�digo de tr�s endere�os
	public void gen(int b, int a){} //chamado como r�tulo begin e after onde b marca o in�cio do c�digo para esse
									//comando e a marca a primeira instru��o ap�s o c�digo para esse comando
	int after = 0; //guarda r�tulo after
	
	public static CmdList Enclosing = CmdList.Null; //usado para comandos break
	

}
