package grafo_pt2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class verificacoes {

	public static void main(String[] args) {
		//verificar se existe a pasta teste
				String caminhoTeste = "C:\\Teste";
				String caminhoConfiguracao = caminhoTeste + "\\" + "Configuracao";
				String caminhoConfigTxt = caminhoConfiguracao + "\\config.txt";
				String rotaNN = caminhoTeste + "\\rotaNN.txt";
				
				File diretorioTeste = new File(caminhoTeste);
				
				
				
				if(diretorioTeste.exists() && diretorioTeste.isDirectory()) {
					System.out.println("O diretório 'Teste'existe.");
				} else {
					System.out.println("O diretório 'Teste' não existe ou não é um diretório.");
					
					boolean criadoTesteComSucesso = diretorioTeste.mkdirs();
					if(criadoTesteComSucesso) {
						System.out.println("Diretório 'Teste' criado com sucesso!");
					} else {
						System.out.println("Não foi possível criar o diretório 'Teste'.");
					}
				}
				
				//verifica se exite a pasta configuração
							
				
				File diretorioConfiguracao = new File(caminhoConfiguracao);
				
				if(diretorioConfiguracao.exists() && diretorioConfiguracao.isDirectory()) {
					System.out.println("O diretório 'Configuracao' existe.");
				} else {
					System.out.println("O diretório 'Configuracao' não existe ou não é um diretório.");
					
					boolean criadoConfiguracaoComSucesso = diretorioConfiguracao.mkdirs();
					if(criadoConfiguracaoComSucesso) {
						System.out.println("Diretório 'Configuracao' criado com sucesso!");
					} else {
						System.out.println("Não foi possível criar o diretório 'Configuracao'.");
					}
				}
				
				
				//verifica se existe o arquivo config.txt		
				File diretorioConfigTxt = new File(caminhoConfigTxt);
				
				if(diretorioConfigTxt.exists() && diretorioConfigTxt.isFile()) {
					System.out.println("O arquivo 'config.txt' existe");
				} else {
					System.out.println("O arquivo 'config.txt' não existe ");
					
					boolean criadoConfigTxtComSucesso;
					try {
						criadoConfigTxtComSucesso = diretorioConfigTxt.createNewFile();
						if(criadoConfigTxtComSucesso) {
							System.out.println("Diretório 'config.txt' criado com sucesso!");
						} else {
							System.out.println("Não foi possível criar o diretório 'config.txt'.");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				// verificação 1.3 e 1.4 e 1.5
				try {
					FileReader fileReader = new FileReader(caminhoConfigTxt);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					
					String linha;
					boolean vazio = true;
					int contagemLinhas= 0;
					
					while((linha = bufferedReader.readLine()) != null) {
						System.out.println(linha);
						String[] linhaArray = linha.split("C:");
						vazio = false;
						contagemLinhas++;
						if(contagemLinhas == 2) {
							System.out.println("Segunda linha existe");
						}
						try {
							if(linhaArray[0].equals("Processado@")) {
								throw new RuntimeException("Há @ no lugar de =");
							}
							if(linhaArray[0].equals("Não Processado@")) {
								throw new RuntimeException("Há @ no lugar de =");
							}
						} catch (RuntimeException e) {
							System.out.println(e);
							return;
						}
					}
					
					if(vazio) {
						System.out.println("O arquivo está vazio, criando as duas primeiras linhas...");
						OutputStream os = new FileOutputStream(caminhoConfigTxt);
						OutputStreamWriter osw =  new OutputStreamWriter(os);
						BufferedWriter writer = new BufferedWriter(osw);
						
						writer.append("Processado=C:\\Teste\\Processado");
						writer.append("\r\n");
						writer.append("Não Processado=C:\\Teste\\NaoProcessado");
						writer.flush();
					} else if(contagemLinhas < 2) {
						System.out.println("O arquivo não possui o total de linhas necessarias. as criando...");
						OutputStream os = new FileOutputStream(caminhoConfigTxt);
						OutputStreamWriter osw =  new OutputStreamWriter(os);
						BufferedWriter writer = new BufferedWriter(osw);
						
						writer.append("Processado=C:\\Teste\\Processado");
						writer.append("\r\n");
						writer.append("Não Processado=C:\\Teste\\NaoProcessado");
						writer.flush();
					}
					
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				
				// validação 1.6
				
				try {
					FileReader fileReader = new FileReader(rotaNN);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					
					String linha;
					Integer contagemLinhas = 0;
					Boolean vazio = true;
					Integer headerTotalNumberOfNodes = 0;
					Integer headerSumOfArestsWeights = 0;
					Integer trailerNumberOfConnectionLines = 0;
					Integer trailerNumberOfWeightLines = 0;
					Integer trailerSumOfWeights = 0;
					
					List<Model> formattedLines = new ArrayList<>();
					
					while((linha = bufferedReader.readLine()) != null) {
						System.out.println(linha);
						String[] linhaArray = linha.split("C:");
						vazio = false;
						contagemLinhas++;
						String identifier = linha.substring(0, 2);
						Integer origin;
						Integer destiny;
						Integer weight;
						
						if (identifier.equals("00")) {
							if (linha.length() != 6) {
								throw new Exception("Header inválido");
							}
							headerTotalNumberOfNodes = Integer.parseInt(linha.substring(2, 4));
							headerSumOfArestsWeights =  Integer.parseInt(linha.substring(4));
						} else if (identifier.equals("01")) {
							if (linha.length() != 7) {
								throw new Exception("Resumo de conexões inválido");
							}
							
							String[] originAndDestiny = linha.substring(2).split("=");
							origin = Integer.parseInt(originAndDestiny[0]);
							destiny = Integer.parseInt(originAndDestiny[1]);
							formattedLines.add(new Model(identifier, origin, destiny));
						} else if (identifier.equals("02")) {
							String[] originAndDestiny = linha.substring(2).split(";");
							origin = Integer.parseInt(originAndDestiny[0]);
							destiny = Integer.parseInt(originAndDestiny[1]);
							weight = Integer.parseInt(linha.split("=")[1]);
							formattedLines.add(new Model(identifier, origin, destiny, weight));
						} else if (identifier.equals("09")) {
							String[] totals = linha.substring(2).split("=");
							String[] totalOfEachLineKind = totals[0].split(";");
							trailerNumberOfConnectionLines = Integer.parseInt(totalOfEachLineKind[0]);
							trailerNumberOfWeightLines = Integer.parseInt(totalOfEachLineKind[1]);
							trailerSumOfWeights = Integer.parseInt(totals[1]);
						}
						
						try {
							if(linhaArray[0].equals("Processado@")) {
								throw new RuntimeException("Há @ no lugar de =");
							}
							if(linhaArray[0].equals("Não Processado@")) {
								throw new RuntimeException("Há @ no lugar de =");
							}
						} catch (RuntimeException e) {
							System.out.println(e);
							return;
						}
					}
					
					List<Integer> uniqueNodes = new ArrayList<>();
					Integer sumOfWeights = 0;
					for (Model model : formattedLines) {
						if (!uniqueNodes.contains(model.getOrigem())) {
							uniqueNodes.add(model.getOrigem());
						}
						if (!uniqueNodes.contains(model.getDestino())) {
							uniqueNodes.add(model.getDestino());
						}
						if (model.getPeso() != null) {
							sumOfWeights += model.getPeso();
						}
					}
					
					if (uniqueNodes.size() != headerTotalNumberOfNodes) {
						throw new Exception("Número totais de nós inválido");
					}
					if (sumOfWeights != headerSumOfArestsWeights) {
						throw new Exception("Soma dos pesos difere (Valor do Registro HEADER = NN e Soma dos Pesos = NN)");
					}
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					
				}
			
				

	}

}
