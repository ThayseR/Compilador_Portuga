package br.portuga.symbols;

import java.util.Hashtable;

import br.portuga.inter.Id;
import br.portuga.lexer.Token;

/*
 * Classe que mapeia tokens de palavras a objetos da classe ID que é definida no pacote inter com as classes
 * para as expressões e comandos
 */
public class Env {
	
	private Hashtable<Token, Id> table;
	protected Env prev;
	public Env(Env n){
		table = new Hashtable<Token, Id>();
		prev = n;
	}
	
	//insere na tabela table o word e seu id
	public void put(Token w, Id i){
		table.put(w, i);
	}
	
	//procura se o identificador já existe em algum envio existente
	public Id get(Token w){
		for(Env e = this; e != null; e=e.prev){
			Id found = (Id) (e.table.get(w));
			if(found != null) return found;
		}
		return null;
	}

}
