package Principal;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ManipulaArquivo {

	public static ArrayList<Amostra> lerAmostras (String caminho) {
		String linha = "";
		String arrayLinha[];
		ArrayList<Amostra> amostras = new ArrayList<Amostra>();
		Amostra amostra;
		float atributos[];
		int i, /*qtdLinhas = 0,*/ qtdAtributos = 0;
		
		try {
			FileReader arquivo = new FileReader(caminho);
			BufferedReader lerArquivo = new BufferedReader(arquivo);
			
			try {
				// Lê primeira linha
				linha = lerArquivo.readLine();
				arrayLinha = linha.split(" ");
				// qtdLinhas = Integer.parseInt(linha[0]); // Não irei precisar para percorrer o arquivo
				qtdAtributos = Integer.parseInt(arrayLinha[1]);
				
				// Lê atributos e classe correspondente
				while(linha != null) {
					linha = lerArquivo.readLine();
					if(linha != null) {
						arrayLinha = linha.split(" ");
						amostra = new Amostra();
						atributos = new float[qtdAtributos];
						
						for(i = 0; i < qtdAtributos; i++) {
							atributos[i] = Float.parseFloat(arrayLinha[i]);
						}
						amostra.setClasse(Integer.parseInt(arrayLinha[qtdAtributos]));
						amostra.setAtributos(atributos);
						
						amostras.add(amostra);
					} 
				} ;
				arquivo.close();
			}catch(IOException e) {
				System.out.println("Não foi possível ler o arquivo!");
				return null;
			}
		}catch(FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
			return null;
		}
		return amostras;
	}
}
