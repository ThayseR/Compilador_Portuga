package br.portuga.lexer;

/*
 * Classe que define constantes inteiras para os símbolos terminais
 */

public class Tag {
	/*
	 * Três constantes INDEX, MINUS e TEMP não são tokens léxicos elas serão usadas na árvores sintáticas.
	 * o Token Basic representa os tipos básicos(bool, string, int), NUM representa os tipos Int do Portuga
	 * ATR representa atribuição ID <- 10 por exemplo, pois nosso comando de atribuição é composto por <-
	 */

	public final static int
		AND = 256, BASIC = 257, SENAO = 260, FALSE = 262, ID = 264, SE = 265, VARS = 281, PROG = 282,
		INDEX = 266, MINUS = 268, NUM = 270, OR = 271, TEMP = 273, TRUE = 274, ENQUANTO = 275,
		ITERE = 276, LER = 277, IMPRIMIR = 278, ATR = 279, STRING = 280;
		// G = 263,  L = 267, EQ = 261
}
