package xadrez;

import tabuleirojogo.Posicao;
import tabuleirojogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {
	
	private Tabuleiro tabuleiro;
	
	public Partida(){
		tabuleiro = new Tabuleiro(8,8);
		iniciaPartida();
	}

	public PecaXadrez[][] getPecas(){
		
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0; i<tabuleiro.getLinhas(); i++){
			for(int j=0; j<tabuleiro.getColunas(); j++){
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		
		return mat;
	}
	
	private void iniciaPartida(){
		tabuleiro.posisionaPeca(new Torre(tabuleiro, Cor.WHITE), new Posicao(2, 1));
		tabuleiro.posisionaPeca(new Rei(tabuleiro, Cor.BLACK), new Posicao(0, 4));
		tabuleiro.posisionaPeca(new Rei(tabuleiro, Cor.WHITE), new Posicao(7, 4));
	}
	
}
