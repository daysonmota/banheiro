package br.com.banheiro;

public class Principal {

	public static void main(String[] args) {
		Banheiro banheiro = new Banheiro();
		
		Thread convidado1 = new Thread(new TarefaNumero1(banheiro),"João");
		Thread convidado2 = new Thread(new TarefaNumero2(banheiro),"Pedro");
		Thread convidado3 = new Thread(new TarefaNumero2(banheiro),"Maria");
	
		Thread limpeza = new Thread(new TarefaLimpeza(banheiro),"Pessoa da limpeza");
		limpeza.setDaemon(true); // quanto terminar, para não executa mais
		
	
		convidado1.start();
		convidado2.start();
		convidado3.start();
		limpeza.start();
	}
}
