package Principal;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ManipulaArquivo {
	private ArrayList<Amostra> amostras; 
	int i, j, qtdLinhas = 0, qtdAtributos = 0;
	float[] atributos, somas;
	Amostra amostra = null;
	
	public ArrayList<Amostra> lerAmostras (String caminho, boolean zScore) {
		String linha = "";
		String[] arrayLinha;		
		
		try {
			FileReader arquivo = new FileReader(caminho);
			BufferedReader lerArquivo = new BufferedReader(arquivo);
			
			try {
				// Lê primeira linha com dados do arquivo
				linha = lerArquivo.readLine();
				arrayLinha = linha.split(" ");
				qtdLinhas = Integer.parseInt(arrayLinha[0]); 
				qtdAtributos = Integer.parseInt(arrayLinha[1]);
				
				// Lê atributos e classe correspondente por amostra (aplicando ou não z-score para normalizar)
				i = 0;
				amostras = new ArrayList<Amostra>();
				somas = new float[qtdAtributos];				
				for(i = 0; i < qtdLinhas; i++) {
					linha = lerArquivo.readLine();
					arrayLinha = linha.split(" ");
					
					amostra = new Amostra();
					atributos = new float[qtdAtributos];
					for(j = 0; j < qtdAtributos; j++) {
						atributos[j] = Float.parseFloat(arrayLinha[j]);
						somas[j] += atributos[j]; // vai somando, caso posteriormente precise aplicar o z-score
					}
					amostra.setClasse(Integer.parseInt(arrayLinha[qtdAtributos]));
					amostra.setAtributos(atributos);
					
					amostras.add(amostra);
				}
				
				if(zScore) {
					aplicaZScore();					
				}
				arquivo.close();
			}catch(IOException e) {
				System.out.println("Não foi possível ler o arquivo!");
				return null;
			}
		}catch(FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
			return null;
		}
		for(i = 0; i < qtdLinhas; i++) {
			for(j = 0; j < qtdAtributos; j++) {
				System.out.print(" "+Float.toString(amostras.get(i).getAtributos()[j]));
			}
			System.out.println(" "+Integer.toString(amostras.get(i).getClasse()));
		}
		return amostras;
	}
	
	private void aplicaZScore() {
		float[] medias = null, variancias = null, desviosPadroes = null;
		float auxVariancia = 0;
		
		medias = new float[qtdAtributos];
		variancias = new float[qtdAtributos];
		desviosPadroes = new float[qtdAtributos];
		
		for(i = 0; i < qtdAtributos; i++) {
			medias[i] = somas[i] / qtdLinhas;
			auxVariancia = 0;
			for(j = 0; j < qtdLinhas; j++) {
				auxVariancia += ((float) Math.pow((amostras.get(j).getAtributos()[i] - medias[i]), 2));
			}
			variancias[i] = auxVariancia / (qtdLinhas - 1);
			desviosPadroes[i] = (float) Math.sqrt(variancias[i]);
		}
		 
		for(i = 0; i < qtdLinhas; i++) {
			for(j = 0; j < qtdAtributos; j++) {
				amostras.get(i).getAtributos()[j] = (amostras.get(i).getAtributos()[j] - medias[j])/ (desviosPadroes[j]);
			}
		}
	}
}
