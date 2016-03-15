package br.portuga.lexer;

import java.io.IOException;
import java.util.Hashtable;

import br.portuga.symbols.Type;

public class Lexer {
	public static int line = 1;
	char peek = ' ';
	//cria uma tabela de hash com as palavras reservadas encontradas
	Hashtable<String, Word> words = new Hashtable<String, Word>();
	void reserve(Word w){
		words.put(w.lexeme, w);
	}
	
	public Lexer(){
		//reserva de palavras chave selecionadas colocando na tabela hash words do lexer
		reserve(new Word("se", Tag.SE));
		reserve(new Word("vars:", Tag.VARS));
		reserve(new Word("programa", Tag.PROG));
		reserve(new Word("senao", Tag.SENAO));
		reserve(new Word("enquanto", Tag.ENQUANTO));
		reserve(new Word("imprimir", Tag.IMPRIMIR));
		reserve(new Word("ler", Tag.LER));
		reserve(new Word("itere", Tag.ITERE));
		reserve(Word.True); reserve(Word.False);
		reserve(Type.Int); reserve(Type.String); reserve(Type.Bool);
		
	}
	
	void readch() throws IOException{
		//função usada para ler o próximo caractere da entrada na variável peek
		
		peek = (char) System.in.read();
	}
	
	boolean readch(char c) throws IOException{
		//função que auxilia a reconhecer tokens compostos como && 
		readch();
		if (peek != c) return false;
		peek = ' ';
		return true;
		
	}
	
	public Token scan() throws IOException{
		for(; ; readch()){
			//ignora espaços em branco, tabulação ou quebra de linha
			if (peek == ' ' || peek == '\t') continue;
			else if(peek == '\n') line = line + 1;
			else break;
		}
		
		//Parte que reconhece tokens compostos
		switch(peek){
		case '&':
			if(readch('&')) return Word.and; 
			else return new Token('&'); 
		case '|':
			if(readch('|')) return Word.or; 
			else return new Token('|'); 
		case '<':
			if(readch('-')) return Word.attr; 
			else return new Token('<');
		}
		
		//Aqui o token pega os valores inteiros
		if(Character.isDigit(peek)){
			int v = 0;
			do{
				v = 10*v + Character.digit(peek, 10);
				readch();
			}while(Character.isDigit(peek));
			return new Num(v);
		}
		
		//Aqui pega strings
		if(peek == '"'){
			//System.out.println("Entrou em reconhecer strings");
			StringBuffer b = new StringBuffer();
			readch();
			while(peek != '"'){
				b.append(peek);
				readch();
			}
			String s = b.toString();
			Word w = (Word) words.get(s);
			//verifica se a string já existe na tabela hash words
			if(w != null) return w;
			w = new Word(s, Tag.STRING);
			words.put(s, w);
			return w;
		}

		
		
		//Aqui o token pega IDs e tem que começar com letra maiúscula
		if(Character.isLetter(peek) && ! Character.isLowerCase(peek)){
			//System.out.println("Entrou em reconhecer Id");
			StringBuffer b = new StringBuffer();
			do{
				b.append(peek);
				readch();
			}while (Character.isLetterOrDigit(peek));
			String s = b.toString();
			Word w = (Word) words.get(s);
			//verifica se a string já existe na tabela hash words
			if(w != null) return w;
			w = new Word(s, Tag.ID);
			words.put(s, w);
			return w;
		} 
		else if(Character.isLetter(peek)){
			//System.out.println("Entrou em reconhecer var e programa");
			StringBuffer b = new StringBuffer();
			//readch();
			while(peek != ' ' && peek != '(' && peek != ')' && peek != '{'){
				b.append(peek);
				readch();
			}
			String s = b.toString();
			//System.out.println("string lida: " + s);
			Word w = (Word) words.get(s);
			//verifica se a string já existe na tabela hash words
			if(w != null) return w;
			if(s=="vars:"){
				w = new Word(s, Tag.VARS);
				words.put(s, w);
				return w;
			}else if(s == "programa"){
				w = new Word(s, Tag.PROG);
				words.put(s, w);
				return w;
			}
			peek = ' ';
			
		}
		
		//Qualquer outro caso é retornado como token
		Token tok = new Token(peek);
		peek = ' ';
		return tok;
				
	}

}
