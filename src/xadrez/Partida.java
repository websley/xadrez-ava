package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleirojogo.Peca;
import tabuleirojogo.Posicao;
import tabuleirojogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	
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
	
	public boolean getCheck(){
		return check;
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
		
		if(testCheck(jogadorAtual)){
			undoMove(origem, destino, pecaCapturada);
			throw new ExecoesXadrez("Você não pode se colocar em cheque!!");
		}
		
		check = (testCheck(oponente(jogadorAtual))) ? true : false;
		

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
	
	private void undoMove(Posicao origem, Posicao destino,Peca capturada){
		Peca p = tabuleiro.removePeca(destino); 
		tabuleiro.posisionaPeca(p, origem);
		
		if(capturada != null){
			tabuleiro.posisionaPeca(capturada, destino);
			pecasCapturadas.remove(capturada);
			pecasNoTabuleiro.add(capturada);
		}
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
	
	private Cor oponente(Cor cor){
		return (cor == cor.WHITE) ? cor.BLACK : cor.WHITE;
	}
	
	private PecaXadrez localizaRei(Cor cor){
		List<Peca> list =  pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : list){
			if (p instanceof Rei){
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Deu treta no sistema, Cade o rei Caramba ???");
	}
	// revisar aula 165
	private boolean testCheck(Cor cor){
		Posicao posicaoRei = localizaRei(cor).getPecaXadrez().toPosition();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasOponente){
			boolean[][] mat = p.possiveisMovimentos();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]){
				return true;
			}
		}
		return false;
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
