package Principal;

import java.util.ArrayList;

public class KNNValidacaoCruzadaKFold {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String caminho;
		int qtdVizinhosKNN, qtdParticoesKFold;
		boolean zScore = false;
		ArrayList<Amostra> amostras;
		
		if (!ValidaEntrada.validaArgs(args)) {
			return;
		}
	 
		caminho = args[0];
		qtdVizinhosKNN = Integer.parseInt(args[1]);
		qtdParticoesKFold = Integer.parseInt(args[2]);
		if (args.length > 3) {
			zScore = args[3].toLowerCase().equals("z");
		}
		
		amostras = ManipulaArquivo.lerAmostras("dados.txt");
		if (amostras == null) {
			return;
		}
		
		System.out.println(""+Integer.toString(amostras.size()));
	}

}
