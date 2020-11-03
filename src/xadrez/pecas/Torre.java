package xadrez.pecas;

import tabuleirojogo.Posicao;
import tabuleirojogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString(){
		return " T";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		
		// acima
		p.setValues(posicao.getLinha()-1, posicao.getColuna());
		while (getTabuleiro().verificaPosicaoExiste(p) && !getTabuleiro().verificaPeca(p)){
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		
		if (getTabuleiro().verificaPosicaoExiste(p) && eUmaPecaInimiga(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// baixo
		p.setValues(posicao.getLinha()+1, posicao.getColuna());
		while (getTabuleiro().verificaPosicaoExiste(p) && !getTabuleiro().verificaPeca(p)){
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().verificaPosicaoExiste(p) && eUmaPecaInimiga(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
	
		// direta
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().verificaPosicaoExiste(p) && !getTabuleiro().verificaPeca(p)){
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().verificaPosicaoExiste(p) && eUmaPecaInimiga(p)){
			mat[p.getColuna()][p.getColuna()] = true;
		}
		
		// esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().verificaPosicaoExiste(p) && !getTabuleiro().verificaPeca(p)){
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().verificaPosicaoExiste(p) && eUmaPecaInimiga(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}
