package tabuleirojogo;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1){
			throw new ExecaoTabuleiro("Erro ao criar tabuleiro: deve conter pelo menos uma coluna e uma linha");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}


	public int getColunas() {
		return colunas;
	}

	
	public Peca peca(int linhas, int colunas){
		if (!verificaPosicaoExiste(linhas, colunas)){
			throw new ExecaoTabuleiro("Posicao não está no tabulerio ");
		}
		return pecas[linhas][colunas];
	}
	
	public Peca peca(Posicao posicao) {
		if (!verificaPosicaoExiste(posicao)){
			throw new ExecaoTabuleiro("Posicao não está no tabulerio ");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void posisionaPeca(Peca peca, Posicao posicao){
		if (verificaPeca(posicao)){
			throw new ExecaoTabuleiro("Ja existe uma peça na posicao " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean verificaPosicaoExiste(int linha, int coluna){
		return linha >=0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean verificaPosicaoExiste(Posicao posicao){
		return verificaPosicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean verificaPeca(Posicao posicao){
		if (!verificaPosicaoExiste(posicao)){
			throw new ExecaoTabuleiro("Posicao não está no tabulerio ");
		}
		return peca(posicao) != null;
	}
	
	public Peca removePeca(Posicao posicao){
		if (!verificaPosicaoExiste(posicao)){
			throw new ExecaoTabuleiro("Posicao não está no tabulerio ");
		}
		if (peca(posicao) == null){
			return null;
		}else{
			Peca aux = peca(posicao);
			aux.posicao = null;
			pecas[posicao.getLinha()][posicao.getColuna()] = null;
			return aux;
		}
	}
	

}
