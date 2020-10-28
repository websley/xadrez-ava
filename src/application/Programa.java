package application;

import java.util.Scanner;

import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosicaoNoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Partida partida = new Partida();
		
		while (true) {
			UI.printTabuleiro(partida.getPecas());
			System.out.println();
			System.out.println("Origem");
			PosicaoNoXadrez origem = UI.lerPosicaoEscolhida(sc);
			
			System.out.println();
			System.out.println("Destino");
			PosicaoNoXadrez destino = UI.lerPosicaoEscolhida(sc);
			
			PecaXadrez capturada = partida.executaMovimento(origem, destino);
		}
	}

}
