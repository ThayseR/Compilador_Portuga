package br.portuga.main;

import java.io.IOException;

import br.portuga.lexer.Lexer;
import br.portuga.parser.Parser;


public class Main {

	public static void main(String[] args) throws IOException{
			//String fileName = args[0];
			//Lexer lex = new Lexer(fileName);
			Lexer lex = new Lexer();
			Parser parse = new Parser(lex);
			parse.program();
			System.out.write('\n');


	}

}
