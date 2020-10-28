package xadrez;

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
	
	private void placeNewPiece(char coluna, int linha, PecaXadrez peca){
		tabuleiro.posisionaPeca(peca, new PosicaoNoXadrez(coluna, linha).toPosition());
	}
	
	private void iniciaPartida(){
		placeNewPiece('c', 1, new Torre(tabuleiro, Cor.WHITE));
        placeNewPiece('c', 2, new Torre(tabuleiro, Cor.WHITE));
        placeNewPiece('d', 2, new Torre(tabuleiro, Cor.WHITE));
        placeNewPiece('e', 2, new Torre(tabuleiro, Cor.WHITE));
        placeNewPiece('e', 1, new Torre(tabuleiro, Cor.WHITE));
        placeNewPiece('d', 1, new Rei(tabuleiro, Cor.WHITE));

        placeNewPiece('c', 7, new Torre(tabuleiro, Cor.BLACK));
        placeNewPiece('c', 8, new Torre(tabuleiro, Cor.BLACK));
        placeNewPiece('d', 7, new Torre(tabuleiro, Cor.BLACK));
        placeNewPiece('e', 7, new Torre(tabuleiro, Cor.BLACK));
        placeNewPiece('e', 8, new Torre(tabuleiro, Cor.BLACK));
        placeNewPiece('d', 8, new Rei(tabuleiro, Cor.BLACK));
	}
	
}
