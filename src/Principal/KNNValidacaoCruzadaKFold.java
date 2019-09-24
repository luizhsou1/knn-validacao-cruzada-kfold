package Principal;

import java.util.ArrayList;

public class KNNValidacaoCruzadaKFold {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String caminho;
		int qtdVizinhosKNN, qtdParticoesKFold;
		boolean zScore = false;
		ManipulaArquivo manipulaArquivo;
		ArrayList<Amostra> amostras;
		
		if (ValidaEntrada.validaArgs(args)) {
			caminho = args[0];
			qtdVizinhosKNN = Integer.parseInt(args[1]);
			qtdParticoesKFold = Integer.parseInt(args[2]);
			if (args.length > 3) {
				zScore = args[3].toLowerCase().equals("z");
			}
			
			manipulaArquivo = new ManipulaArquivo();
			amostras = manipulaArquivo.lerAmostras(caminho, zScore);
			if (amostras != null) {
				 System.out.println(""+Integer.toString(amostras.size()));
			}
		}
		return;
	}

}
