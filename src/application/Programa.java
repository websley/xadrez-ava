package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExecoesXadrez;
import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosicaoNoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Partida partida = new Partida();
		List<PecaXadrez> capturadas = new ArrayList<>();
		
		while (true) {
			try {
				UI.clearScreen();
				UI.motraPartida(partida, capturadas);
				System.out.println();
				System.out.println("Origem");
				PosicaoNoXadrez origem = UI.lerPosicaoEscolhida(sc);
				
				boolean[][] possiviesMovimentos = partida.possiveisMovimentos(origem);
				UI.clearScreen();
				UI.printTabuleiro(partida.getPecas(), possiviesMovimentos);
				
				System.out.println();
				System.out.println("Destino");
				PosicaoNoXadrez destino = UI.lerPosicaoEscolhida(sc);
				
				PecaXadrez capturada = partida.executaMovimento(origem, destino);
				
				
				if(capturada != null){
					capturadas.add(capturada);
				} 
			}
			catch(ExecoesXadrez e){
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e){
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
		}
	}

}
