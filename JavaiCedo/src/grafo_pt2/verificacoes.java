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
				String caminhodiretorioProcessados = caminhoTeste + "\\" + "Processados";
				String caminhodiretorioNaoProcessados = caminhoTeste + "\\" + "Não Processados";
				String nomeArquivoProcessado = caminhodiretorioProcessados + "\\Processado.txt";
				String nomeArquivoNaoProcessado = caminhodiretorioNaoProcessados + "\\Não Processado.txt";
				
				File diretorioTeste = new File(caminhoTeste);
				File diretorioProcessados = new File(caminhodiretorioProcessados);
				File diretorioNaoProcessados = new File(caminhodiretorioNaoProcessados);
				
				
				
				if(diretorioTeste.exists() && diretorioTeste.isDirectory()) {
					//System.out.println("O diretório 'Teste'existe.");
				} else {
					System.out.println("O diretório 'Teste' não existe ou não é um diretório.");
					
					boolean criadoTesteComSucesso = diretorioTeste.mkdirs();
					if(criadoTesteComSucesso) {
						System.out.println("Diretório 'Teste' criado com sucesso!");
					} else {
						System.out.println("Não foi possível criar o diretório 'Teste'.");
					}
				}
				
				if(!diretorioProcessados.exists() && !diretorioProcessados.isDirectory()) {					
					boolean criadoTesteComSucesso = diretorioProcessados.mkdirs();
					if(criadoTesteComSucesso) {
						System.out.println("Diretório 'Processado' criado com sucesso!");
					} else {
						System.out.println("Não foi possível criar o diretório 'Processado'.");
					}
				}
				
				if(!diretorioNaoProcessados.exists() && !diretorioNaoProcessados.isDirectory()) {
					boolean criadoTesteComSucesso = diretorioNaoProcessados.mkdirs();
					if(criadoTesteComSucesso) {
						System.out.println("Diretório 'Não Processado' criado com sucesso!");
					} else {
						System.out.println("Não foi possível criar o diretório 'Não Processado'.");
					}
				}
				
				//verifica se exite a pasta configuração
							
				
				File diretorioConfiguracao = new File(caminhoConfiguracao);
				
				if(diretorioConfiguracao.exists() && diretorioConfiguracao.isDirectory()) {
					//System.out.println("O diretório 'Configuracao' existe.");
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
					//System.out.println("O arquivo 'config.txt' existe");
				} else {
					System.out.println("O arquivo 'config.txt' não existe ");
					
					boolean criadoConfigTxtComSucesso;
					try {
						criadoConfigTxtComSucesso = diretorioConfigTxt.createNewFile();
						if(criadoConfigTxtComSucesso) {
							//System.out.println("Diretório 'config.txt' criado com sucesso!");
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
						//System.out.println(linha);
						String[] linhaArray = linha.split("C:");
						vazio = false;
						contagemLinhas++;
						System.out.println(contagemLinhas);
						if(contagemLinhas == 2) {
							//System.out.println("Segunda linha existe");
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
				
				
				// validação 1.6 e 1.7 e 1.8
				
				try {
					FileReader fileReader = new FileReader(rotaNN);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					
					List<String> uniqueNodesTotal = new ArrayList<>();
					Integer sumOfWeights = 0;
					String linha;
					Integer contagemLinhas = 0;
					Integer contagemLinhasPesos = 0;
					Boolean vazio = true;
					String headerTotalNumberOfNodes = null;
					Integer headerSumOfArestsWeights = 0;
					Integer trailerNumberOfConnectionLines = 0;
					Integer trailerNumberOfWeightLines = 0;
					Integer trailerSumOfWeights = 0;
					
					List<Model> formattedLines = new ArrayList<>();
					
					while((linha = bufferedReader.readLine()) != null) {
						
						//System.out.println(linha);
						String[] linhaArray = linha.split("C:");
						vazio = false;
						contagemLinhas++;
						//String identifier = linha.substring(0, 2);
						String origin;
						String destiny;
						Integer weight;
						
						System.out.println(contagemLinhas);
						if (linha.startsWith("00")) {
							if (linha.length() != 6) {
								// verificação 1.8
								throw new Exception("Header inválido");
							}
							headerTotalNumberOfNodes = linha.substring(2, 4);
							headerSumOfArestsWeights =  Integer.parseInt(linha.substring(4));
							} else if (linha.startsWith("01")) {
								if (linha.length() != 7 || !linha.substring(4,5).equals("=")) {
									// verificação 1.9
									throw new Exception("Resumo de conexões inválido");
								}
								//System.out.println("esss aqui");
								String[] originAndDestiny = linha.substring(2).split("=");
								origin = originAndDestiny[0];
								destiny = originAndDestiny[1];
								//System.out.println("entrou aqui");
								formattedLines.add(new Model(linha.substring(0, 2), origin, destiny));
							} else if (linha.startsWith("02")) {
								String[] originAndDestiny = linha.substring(2).split(";");
								origin = originAndDestiny[0];								
								destiny = originAndDestiny[1];
								weight = Integer.parseInt(linha.split("=")[1]);
								contagemLinhasPesos+=1;
								formattedLines.add(new Model(linha.substring(0, 2), origin, destiny, weight));
							} else if (linha.startsWith("09")) {
								String[] totals = linha.substring(2).split("=");
								String[] totalOfEachLineKind = totals[0].split(";");
								trailerNumberOfConnectionLines = Integer.parseInt(totalOfEachLineKind[0]);
								trailerNumberOfWeightLines = Integer.parseInt(totalOfEachLineKind[1]);
								trailerSumOfWeights = Integer.parseInt(totals[1]);
						}
					
						
						if(linhaArray[0].equals("Processado@")) {
						throw new RuntimeException("Há @ no lugar de =");
								}
								if(linhaArray[0].equals("Não Processado@")) {
								throw new RuntimeException("Há @ no lugar de =");
								}
						
								List<String> uniqueNodes = new ArrayList<>();
								List<Integer> uniqueWeight = new ArrayList<>();
								Integer peso = 0;
								for (Model model : formattedLines) {
							
									if (!uniqueNodes.contains(model.getOrigem()) && model.getPeso() == null) {
										uniqueNodes.add(model.getOrigem());
									}
									if (!uniqueNodes.contains(model.getDestino()) && model.getPeso() == null) {
										uniqueNodes.add(model.getDestino());	
									}
									
									if (model.getPeso() != null) {
										uniqueWeight.add(model.getPeso());
										peso+= model.getPeso();
									}
								}
								sumOfWeights = peso;
								uniqueNodesTotal = uniqueNodes;
									
						}
					System.out.println(trailerNumberOfConnectionLines);
					if ( !headerTotalNumberOfNodes.toString().equals(String.format("%02d", uniqueNodesTotal.size()))) {
						throw new Exception("Número totais de nós inválido");
					}
					if (!headerSumOfArestsWeights.toString().equals(sumOfWeights.toString())) {
						throw new Exception("Soma dos pesos difere (Valor do Registro HEADER = NN e Soma dos Pesos = NN)");
					}
					if ( !headerTotalNumberOfNodes.toString().equals(String.format("%02d", trailerNumberOfConnectionLines))) {
						throw new Exception("Número de linhas diferentes.(resumo)");
					} else {
						System.out.println("Número de linhas iguais.(resumo)");
					}
					if ( !String.format("%02d",contagemLinhasPesos).toString().equals(String.format("%02d", trailerNumberOfWeightLines))) {
						System.out.println(contagemLinhasPesos.toString());
						System.out.println(trailerNumberOfWeightLines);
						throw new Exception("Número de linhas diferentes. (peso)");
					} else {
						System.out.println("Número de linhas iguais.(peso)");
					}
					
					
					
					try {
						File GrafoProcessado = new File(nomeArquivoProcessado);
						boolean criadoTxtComSucesso;
						criadoTxtComSucesso = GrafoProcessado.createNewFile();
						if(criadoTxtComSucesso) {
							OutputStream os = new FileOutputStream(nomeArquivoProcessado);
							OutputStreamWriter osw =  new OutputStreamWriter(os);
							BufferedWriter writer = new BufferedWriter(osw);
							
							
							while((linha = bufferedReader.readLine()) != null) {
								writer.append(linha);
								writer.append("\r\n");
							}
							
							
							writer.flush();
							System.out.println("O arquivo processo com sucesso e movido para a pasta Processado.");
						} else {
							System.out.println("Não foi possível criar o diretório 'processado.txt'.");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					try {
						File GrafoProcessado = new File(nomeArquivoNaoProcessado);
						boolean criadoTxtComSucesso;
						criadoTxtComSucesso = GrafoProcessado.createNewFile();
						if(criadoTxtComSucesso) {
							OutputStream os = new FileOutputStream(nomeArquivoNaoProcessado);
							OutputStreamWriter osw =  new OutputStreamWriter(os);
							BufferedWriter writer = new BufferedWriter(osw);
							
							FileReader fileReader = new FileReader(rotaNN);
							BufferedReader bufferedReader = new BufferedReader(fileReader);
							String linha;
							while((linha = bufferedReader.readLine()) != null) {
								writer.append(linha);
								writer.append("\r\n");
							}
							
							
							writer.flush();
							System.out.println("O arquivo processado sem sucesso e movido para a pasta NãoProcessado.");
						} else {
							System.out.println("Não foi possível criar o diretório 'Não processado.txt'.");
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					e.printStackTrace();
				} catch (RuntimeException e) {
					try {
						File GrafoProcessado = new File(nomeArquivoNaoProcessado);
						boolean criadoTxtComSucesso;
						criadoTxtComSucesso = GrafoProcessado.createNewFile();
						if(criadoTxtComSucesso) {
							OutputStream os = new FileOutputStream(nomeArquivoNaoProcessado);
							OutputStreamWriter osw =  new OutputStreamWriter(os);
							BufferedWriter writer = new BufferedWriter(osw);
							
							FileReader fileReader = new FileReader(rotaNN);
							BufferedReader bufferedReader = new BufferedReader(fileReader);
							String linha;
							while((linha = bufferedReader.readLine()) != null) {
								writer.append(linha);
								writer.append("\r\n");
							}
							
							
							writer.flush();
							System.out.println("O arquivo processado sem sucesso e movido para a pasta NãoProcessado.");
						} else {
							System.out.println("Não foi possível criar o diretório 'Não processado.txt'.");
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					e.printStackTrace();
				} catch (IOException e) {
					try {
						File GrafoProcessado = new File(nomeArquivoNaoProcessado);
						boolean criadoTxtComSucesso;
						criadoTxtComSucesso = GrafoProcessado.createNewFile();
						if(criadoTxtComSucesso) {
							OutputStream os = new FileOutputStream(nomeArquivoNaoProcessado);
							OutputStreamWriter osw =  new OutputStreamWriter(os);
							BufferedWriter writer = new BufferedWriter(osw);
							
							FileReader fileReader = new FileReader(rotaNN);
							BufferedReader bufferedReader = new BufferedReader(fileReader);
							String linha;
							while((linha = bufferedReader.readLine()) != null) {
								writer.append(linha);
								writer.append("\r\n");
							}
							
							
							writer.flush();
							System.out.println("O arquivo processado sem sucesso e movido para a pasta NãoProcessado.");
						} else {
							System.out.println("Não foi possível criar o diretório 'Não processado.txt'.");
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					try {
						File GrafoProcessado = new File(nomeArquivoNaoProcessado);
						boolean criadoTxtComSucesso;
						criadoTxtComSucesso = GrafoProcessado.createNewFile();
						if(criadoTxtComSucesso) {
							OutputStream os = new FileOutputStream(nomeArquivoNaoProcessado);
							OutputStreamWriter osw =  new OutputStreamWriter(os);
							BufferedWriter writer = new BufferedWriter(osw);
							
							FileReader fileReader = new FileReader(rotaNN);
							BufferedReader bufferedReader = new BufferedReader(fileReader);
							String linha;
							while((linha = bufferedReader.readLine()) != null) {
								writer.append(linha);
								writer.append("\r\n");
							}
							
							
							writer.flush();
							System.out.println("O arquivo processado sem sucesso e movido para a pasta NãoProcessado.");
						} else {
							System.out.println("Não foi possível criar o diretório 'Não processado.txt'.");
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					e.printStackTrace();
				}
				
				
				
				
				
			
				

	}

}
