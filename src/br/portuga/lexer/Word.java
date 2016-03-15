package br.portuga.lexer;

/*
 * A classe word gerencia lexemas para palavras reservadas, identificadores e tokens compostos como &&e
 * texto fonte -2 tem a forma intermediária Minus 2
 */
public class Word extends Token{
	//as words tem tag e lexema
	public String lexeme = "";
	public Word(String s, int tag){
		super(tag);
		lexeme = s;
	}
	public String toString(){
		return lexeme;
	}
	
	public static final Word
		attr = new Word("<-", Tag.ATR),
		and = new Word("&&", Tag.AND), or = new Word("||", Tag.OR), 
		minus = new Word("minus", Tag.MINUS),
		True = new Word("true", Tag.TRUE), False = new Word("false", Tag.FALSE),
		vars = new Word("vars:", Tag.VARS), prog = new Word("programa", Tag.PROG),
		temp = new Word("t", Tag.TEMP), string = new Word("string", Tag.STRING);
		//eq = new Word("=", Tag.EQ), l = new Word("<", Tag.L), g = new Word(">", Tag.G),

}
