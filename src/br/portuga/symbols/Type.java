package br.portuga.symbols;

import br.portuga.lexer.Tag;
import br.portuga.lexer.Word;

public class Type extends Word{
	public int width = 0; //é usado para alocação de memória
	public Type(String s, int tag, int w){
		super(s, tag);
		width = w;
	}
	
	public static final Type
		Int = new Type("int", Tag.BASIC, 4),
		Bool = new Type("bool", Tag.BASIC, 1),
		String = new Type("string", Tag.BASIC, 16);
		//Char = new Type("char", Tag.BASIC, 1),
	
	//funções numeric e max são úteis para conversão de tipo
	public static boolean numeric(Type p){
		if(p == Type.Int || p == Type.Bool || p == Type.String){
			return true;
		}
		else return false;
	}
	
	public static Type max(Type p1, Type p2){
		if (! numeric(p1) || ! numeric(p2)) return null;
		else if(p1 == Type.Int || p2 == Type.Int) return Type.Int;
		else return Type.String;
	}

}
