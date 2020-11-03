package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleirojogo.Peca;
import tabuleirojogo.Posicao;
import tabuleirojogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	//contrutor
	public Partida(){
		tabuleiro = new Tabuleiro(8,8);
		turno = 1;
		jogadorAtual = Cor.WHITE;
		
		iniciaPartida();
	}
	
	public void proximoTurno(){
		turno++;
		jogadorAtual= (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	public int getTurno(){
		return turno;
	}
	
	public Cor getJogadorAtual(){
		return jogadorAtual;
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
	
	public PecaXadrez executaMovimento(PosicaoNoXadrez posicaoOrigem, PosicaoNoXadrez posicaoDestino){
		Posicao origem = posicaoOrigem.toPosition();
		Posicao destino = posicaoDestino.toPosition();
		validaPosicaoOrigem(origem);
		validaPosicaoDestino(origem, destino);
		Peca pecaCapturada = makeMove(origem, destino);
		proximoTurno();
		return (PecaXadrez)pecaCapturada;
	}
	
	
	
	private void validaPosicaoOrigem(Posicao posicao){
		if(!tabuleiro.verificaPeca(posicao)){
			throw new ExecoesXadrez("Não a peças na posição selecionada");
		}
		if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()){
			throw new ExecoesXadrez("A peça escolhida não é sua");
		}
		if (!tabuleiro.peca(posicao).temPossibilidadeMover()) {
			throw new ExecoesXadrez("Não existe movimentos possveis para a peça selecionada");
		}
	}
	
	private void validaPosicaoDestino(Posicao origem, Posicao destino){
		if (!tabuleiro.peca(origem).podeMover(destino)){
			throw new ExecoesXadrez("A peca não pode se mover para possição esclhida");
		}
	}
	
	private Peca makeMove(Posicao origem, Posicao destino){
		Peca p  = tabuleiro.removePeca(origem);
		Peca capturada = tabuleiro.removePeca(destino);
		tabuleiro.posisionaPeca(p, destino);
		System.out.println("aki");
		
		if(capturada != null){
			pecasNoTabuleiro.remove(capturada);
			pecasCapturadas.add(capturada);
		}
		return capturada;
	}
	
	
	private void placeNewPiece(char coluna, int linha, PecaXadrez peca){
		tabuleiro.posisionaPeca(peca, new PosicaoNoXadrez(coluna, linha).toPosition());
		pecasNoTabuleiro.add(peca);
	}
	
	public boolean[][] possiveisMovimentos(PosicaoNoXadrez posicaoPeca){
		Posicao posicao = posicaoPeca.toPosition();
		validaPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
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
