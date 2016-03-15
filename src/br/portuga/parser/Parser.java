package br.portuga.parser;

import java.io.IOException;

import br.portuga.inter.And;
import br.portuga.inter.Arith;
import br.portuga.inter.CmdList;
import br.portuga.inter.Constant;
import br.portuga.inter.Enquanto;
import br.portuga.inter.Expr;
import br.portuga.inter.Id;
import br.portuga.inter.Imprimir;
import br.portuga.inter.Itere;
import br.portuga.inter.Ler;
import br.portuga.inter.Not;
import br.portuga.inter.Or;
import br.portuga.inter.Rel;
import br.portuga.inter.Se;
import br.portuga.inter.Senao;
import br.portuga.inter.Seq;
import br.portuga.inter.Set;
import br.portuga.inter.Texto;
import br.portuga.inter.Unary;
import br.portuga.lexer.Lexer;
import br.portuga.lexer.Tag;
import br.portuga.lexer.Token;
import br.portuga.lexer.Word;
import br.portuga.symbols.Env;
import br.portuga.symbols.Type;

/*
 * lê um fluxo de tokens e constrói uma árvore de sintaxe chamando as funções construtoras apropriadas
 * A tabela de símbolos correntes é mantida como na Figura 2.38 da seção 2.7 do livro
 */
public class Parser {
	
	private Lexer lex; //Analisador Léxico para este analisador sintático
	private Token look; //busca o proximo tag
	Env top = null; //tabela de símbolos corrente ou do topo
	int used = 0; //memória usada para declarações
	public Parser(Lexer l) throws IOException{
		lex = l;
		move();
	}
	
	void move() throws IOException{
		look = lex.scan();
		System.out.println("lido no lexer: " + look.toString());
	}
	
	void error(String s){
		throw new Error("próximo à linha " + lex.line + ": " + s);
	}
	
	void match(int t) throws IOException{
		if(look.tag == t) move();
		else error("erro de sintaxe");
	}
	
	/* Possui um procedimento para cada não terminal. Os procedimentos são baseados na gramática Portuga
	 * sem recursão à esquerda a análise começa com a chamada do procedimento program que chama block()
	 * para analisar sintaticamente o fluxo de entrada e construir a árvore de sintaxe.
	 */
	public void program() throws IOException{
		//program -> block
		match(Tag.PROG);
		match(Tag.ID);
		CmdList c = block();
		int begin = c.newLabel();
		int after = c.newLabel();
		c.emitLabel(begin);
		c.gen(begin, after);
		c.emitLabel(after);
		
	}
	
	//Tratamento da tabela de simbolos, aqui trata quando tem declaração de variáveis
	//locais dentro dos blocos mas não é o caso do Portuga
	CmdList block() throws IOException{
		//block -> {VarSec CommandList}
		match('{');
		match(Tag.VARS);
		Env savedEnv = top; //elo para a tabela de simbolos anterior
		top = new Env(top);
		decls();
		CmdList c = cmdLists();
		match('}');
		top = savedEnv;
		return c;
	}
	
	//Tratamento da tabela de simbolos
		CmdList blockCmd() throws IOException{
			//block -> {VarSec CommandList}
			CmdList c = cmdLists();
			match('}');
			return c;
		}
		
	//As declarações decls() resultam em entradas da tabela de simbolos para identificadores
	
	void decls() throws IOException{
		
		while(look.tag == Tag.BASIC){
			Type p = type();
			Token tok = look;
			match(Tag.ID);
			if(look.tag == ','){
				match(',');
			}else match(';');
			
			Id id = new Id((Word)tok, p, used);
			top.put(tok, id);
			used = used+p.width;
			
		}
	}
	
	Type type() throws IOException{
		Type p = (Type) look;
		match(Tag.BASIC); //espera look.tag == tag.Basic
		return p;
		
	}
	
	CmdList cmdLists() throws IOException{
		if(look.tag == '}') return CmdList.Null;
		else return new Seq(cmdList(), cmdLists());
	}
	
	CmdList cmdList() throws IOException{
		Expr x;
		CmdList c, c1, c2;
		CmdList savedCmdList; //guarda o loop
		
		/*cada case corresponde às produções para o não terminal CmdList
		 *Cada case constrói um nó para um comando. 
		 *Os loops aninhados são tratados usando a variável CmdList.Enclosing da classe cmdList e saved CmdList
		 *para manter o loop envolvente corrente
		 */
		switch(look.tag){
		case ';':
			move();
			return CmdList.Null;
		case Tag.SE:
			match(Tag.SE); 
			match('(');
			x = bool(); 
			match(')');
			c1 = cmdList();
			//move();
			if(look.tag != Tag.SENAO) return new Se(x, c1);
			match(Tag.SENAO);
			c2 = cmdList();
			return new Senao(x,c1,c2);
		case Tag.IMPRIMIR:
			match(Tag.IMPRIMIR); 
			match('(');
			x = bool(); 
			//move();
			match(')');
			match(';');
			return new Imprimir(x);
		case Tag.LER:
			match(Tag.LER); 
			match('(');
			x = factor(); 
			//move();
			match(')');
			match(';');
			return new Ler(x);
		case Tag.ENQUANTO:
			Enquanto enquantoNo = new Enquanto();
			savedCmdList = CmdList.Enclosing;
			CmdList.Enclosing = enquantoNo;
			match(Tag.ENQUANTO);
			match('(');
			x = bool();
			match(')');
			c1 = cmdList();
			enquantoNo.init(x, c1);
			CmdList.Enclosing = savedCmdList; //reinicia CmdList.Enclosing
			return enquantoNo;
		case Tag.ITERE:
			Itere itereNo = new Itere();
			savedCmdList = CmdList.Enclosing;
			CmdList.Enclosing = itereNo;
			match(Tag.ITERE);
			match('(');
			x = factor();
			//move();
			match(')');
			c1 = cmdList();
			itereNo.init(x, c1);
			CmdList.Enclosing = savedCmdList; //reinicia CmdList.Enclosing
			return itereNo;
		case '{':
			move();
			return blockCmd();
		default:
			return assign();
			
		}
		
	}
	
	//Código para atribuições
	CmdList assign() throws IOException{
		CmdList cmd; Token t = look;
		//System.out.println("teste token lido "+ t.toString());
		match(Tag.ID);
		Id id = top.get(t);
		if(id == null) error(t.toString()+ " não declarado");
		//atribuição em Portuga é composto por <-
		match(Tag.ATR);
		System.out.println("Vou para set");
		cmd = new Set(id, bool());
		System.out.println("Saiu do set");
		match(';');
		return cmd;
	}
	
	Expr bool() throws IOException{
		Expr x = join();
		while(look.tag == Tag.OR){
			Token tok = look;
			move();
			x = new Or(tok, x, join());
		}
		return x;
	}
	
	Expr join() throws IOException{
		Expr x = equality();
		while(look.tag == Tag.AND){
			Token tok = look;
			move();
			x = new And(tok, x, equality());
		}
		return x;
	}
	
	Expr equality() throws IOException{
		Expr x = rel();
		while(look.tag == '='){
			Token tok = look;
			move();
			x = new Rel(tok, x, rel());
		}
		return x;
	}
	
	Expr rel() throws IOException{
		Expr x = expr();
		switch(look.tag){
		case '<': case '>':
			Token tok = look;
			move();
			return new Rel(tok, x, expr());
		default:
			return x;
		}
	}
	
	Expr expr() throws IOException{
		Expr x = term();
		while(look.tag == '+' || look.tag == '-'){
			Token tok = look;
			move();
			x = new Arith(tok, x, term());
		}
		return x;
	}
	
	Expr term() throws IOException{
		Expr x = unary();
		while(look.tag == '*' || look.tag == '/'){
			Token tok = look;
			move();
			x = new Arith(tok, x, unary());
		}
		return x;
	}
	
	Expr unary() throws IOException{
		if(look.tag == '-'){
			move();
			return new Unary(Word.minus, unary());
		}
		else if(look.tag == '!'){
			Token tok = look;
			move();
			return new Not(tok, unary());
			
		}
		else return factor();
		
	}
	
	//Agora tratamos fatores nas expressões
	Expr factor() throws IOException{
		Expr x = null;
		//System.out.println(" Parser factor() " + look.tag); 
		switch(look.tag){
		case'(':
			move();
			x = bool();
			match(')');
			return x;
		case Tag.NUM:
			x = new Constant(look, Type.Int);
			move();
			return x;
		case Tag.TRUE:
			x = Constant.True;
			move();
			return x;
		case Tag.FALSE:
			x = Constant.False;
			move();
			return x;
		case Tag.ID:
			//System.out.println("Entrou no factor() ID");
			String s = look.toString();
			Id id = top.get(look);
			if (id == null) error(look.toString() + " não declarado");
			//System.out.println("Retornou o ID");
			move();
			return id;
		case Tag.STRING:
			//String s = look.toString();
			System.out.println("Entrei no case tag.string");
			x = new Texto(look, Type.String);
			move();
			return x;
		default:
			error("erro de sintaxe");
			return x;
		}
	}
	
	
}
