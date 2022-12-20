package br.com.banheiro;

public class Banheiro {

	private boolean ehSujo = true;

	public void fazNumero1() {
		String nome = Thread.currentThread().getName(); // Nome da Thread

		System.out.println(nome + " batendo na porta");

		/* Uma Thread ao mesmo tempo, um convidado pode entrar no banheiro */
		synchronized (this) {
			System.out.println(nome + " Entrando no banheiro");

			while (ehSujo) {
				esperaLaFora(nome);
			}

			System.out.println(nome + " fazendo coisa rapida");
			dormeUmPouco(10000);
			this.ehSujo = true; // deixa sujo novamente
			System.out.println(nome + " dando descarga");
			System.out.println(nome + " lavando as mãos");
			System.out.println(nome + " saindo do banheiro");
		}

	}

	public void fazNumero2() {

		String nome = Thread.currentThread().getName(); // Nome da Thread

		System.out.println(nome + " batendo na porta");

		/* Uma Thread ao mesmo tempo, um convidade pode entrar no banheiro */
		synchronized (this) {
			System.out.println(nome + " Entrando no banheiro");

			while (ehSujo) {
				esperaLaFora(nome);
			}

			System.out.println(nome + " fazendo coisa demorada");
			dormeUmPouco(1000);
			this.ehSujo = true; // deixa sujo novamente
			System.out.println(nome + " dando descarga");
			System.out.println(nome + " lavando mao");
			System.out.println(nome + " saindo do banheiro");
		}
	}

	private void dormeUmPouco(int milin) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*Aguardando*/
	private void esperaLaFora(String nome) {
		System.out.println(nome + ", disse que o banheiro está sujo");
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void limpa() {

		String nome = Thread.currentThread().getName(); // Nome da Thread

		System.out.println(nome + " batendo na porta");

		/* Uma Thread ao mesmo tempo, um convidade pode entrar no banheiro */
		synchronized (this) {
			System.out.println(nome + " Entrando no banheiro");

			if (!ehSujo) {
				System.out.println(nome + " , não está sujo, vou sair");
				return;
			}
			System.out.println(nome + " limpando o banheiro");
			this.ehSujo = false;

			dormeUmPouco(3000);
			
			this.notifyAll(); // avisa todas Threads que estão esperando e tenta entrar novamente
			System.out.println(nome + " saindo do banheiro");
		}
	}

}
