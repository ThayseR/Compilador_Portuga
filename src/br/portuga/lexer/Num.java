package br.portuga.lexer;

public class Num extends Token{
	//a classe num terá um tag identificador e um valor
	
	public final int value;
	public Num(int v){
		super(Tag.NUM); //aqui passamos o código 270 para o tag
		value = v;
	}
	
	public String toString(){
		return " " + value;
	}

}
