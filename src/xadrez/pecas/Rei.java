package xadrez.pecas;

import tabuleirojogo.Posicao;
import tabuleirojogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString(){
		return " R";
	}

	private boolean canMove(Posicao posicao){
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		
		//acima
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		if(getTabuleiro().verificaPosicaoExiste(p) && canMove(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//baico
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		if(getTabuleiro().verificaPosicaoExiste(p) && canMove(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna() -1);
		if(getTabuleiro().verificaPosicaoExiste(p) && canMove(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita
		p.setValues(posicao.getLinha(), posicao.getColuna() +1);
		if(getTabuleiro().verificaPosicaoExiste(p) && canMove(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//diagonal acima esquerda
		p.setValues(posicao.getLinha() -1 , posicao.getColuna() - 1);
		if(getTabuleiro().verificaPosicaoExiste(p) && canMove(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//diagonal acima direita
		p.setValues(posicao.getLinha() -1 , posicao.getColuna() + 1);
		if(getTabuleiro().verificaPosicaoExiste(p) && canMove(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//diagonal abaixo esquerda
		p.setValues(posicao.getLinha() + 1 , posicao.getColuna() - 1);
		if(getTabuleiro().verificaPosicaoExiste(p) && canMove(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//diagonal abaixo direita
		p.setValues(posicao.getLinha() + 1 , posicao.getColuna() + 1);
		if(getTabuleiro().verificaPosicaoExiste(p) && canMove(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		return mat;
		
		
	}

}
