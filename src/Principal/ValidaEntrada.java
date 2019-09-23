package Principal;

public class ValidaEntrada {
	public static boolean validaArgs(String[] args){
		if(args.length < 3) {
			System.out.println("É necessário passar pelo menos três parâmetros para a função");
			System.out.println("1º Caminho do arquivo (Obrigatório)");
			System.out.println("2º Número de vizinhos do K-NN (Obrigatório)");
			System.out.println("3º Número de partições do K-Fold (Obrigatório)");
			System.out.println("4º Letra z, para aplicar o z-score (Opcional)");
			return false;
		} 
		if(args.length > 3) {
			if(!args[3].toLowerCase().equals("z")) {
				System.out.println(args[3] + ", não é um valor válido!\nCaso deseje normalizar pelo z-score, passe a letra z no quarto parâmetro.\\n \" + ");
				return false;
			}
		}
		
		try {
			Integer.parseInt(args[1]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Segundo parâmetro " + args[1] + ", não é um valor válido para o número de vizinhos do K-NN.");
			return false;
		}
		
		try {
			Integer.parseInt(args[2]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Terceiro parâmetro " + args[2] + ", não é um valor válido para o número de partições do K-Fold.");
			return false;
		}
		
		return true;
	}
}
