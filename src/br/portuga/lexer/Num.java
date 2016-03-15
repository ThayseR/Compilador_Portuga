package br.portuga.lexer;

public class Num extends Token{
	//a classe num ter� um tag identificador e um valor
	
	public final int value;
	public Num(int v){
		super(Tag.NUM); //aqui passamos o c�digo 270 para o tag
		value = v;
	}
	
	public String toString(){
		return " " + value;
	}

}
