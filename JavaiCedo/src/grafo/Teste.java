package grafo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Teste {

	public static void main(String[] args) {		
		try {
			FileInputStream fis = new FileInputStream("C:\\Temp\\configs.txt");
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			LineNumberReader ln = new LineNumberReader(br);
			Scanner sn = new Scanner(fis);
			String line =" ";
			
			OutputStream os = new FileOutputStream("C:\\Temp\\teste.txt");
			OutputStreamWriter osw =  new OutputStreamWriter(os);
			BufferedWriter writer = new BufferedWriter(osw);
			
			List<Integer> apartaments = new ArrayList<>();
			Map<String, Integer> errors = new HashMap<>();
			List<List<Integer>> apartamentsMatrix = new ArrayList<>();
			Integer indice = 0;
			Integer indiceAux = 0;
			Integer apt= 0;
			Integer indexNegativo = 0;
			Integer indexDuplicado = 0;
			
			while ((line = br.readLine()) != null) {
				if (indice == 0) {
					System.out.println(line);
					indice+=1;
					continue;
				}
				indice+=1;
				String[] lineArray = line.split(" ");
				
				
				if(lineArray[1].equals("TRAILER33") ) {
					if(indice - 2 != 33) {
						throw new RuntimeException("Erro: O Número de nós está errado");
					}
					
					break;
				}
			
				Integer apartament = Integer.parseInt(lineArray[1]);
				if (apartament >= 0 && !apartaments.contains(apartament)) {
					apartaments.add(apartament);
					indiceAux++;
				} else {
					
					if(apartament <=0) {
						indexNegativo+=1;
						errors.put("\r\n" + "Número Negativo: " + lineArray[0], apartament);
					}else {
						indexDuplicado+=1;
						errors.put("\r\n" + "Número Duplicado: " + lineArray[0], apartament);
					}
					
				}
				
			}
			
			br.close();
			
			for (Integer apart : apartaments) {
				Grafo g = new Grafo();
				
				String apartString= apart.toString();
				List<Integer> neighbors = apartaments
						.stream()
						.filter(aprt -> {
							String aprtString= aprt.toString();
							if(Integer.parseInt(String.valueOf(apartString.charAt(2))) == 1) {
								return 	apart == (aprt);
							}
							 else if(Integer.parseInt(String.valueOf(apartString.charAt(2))) == 2) {
								return 	aprt == (apart - 1) || 
										aprt == (apart + 1) ||
										aprt == (apart + 99) ||
										aprt == (apart + 100) ;
										
							} else if(Integer.parseInt(String.valueOf(apartString.charAt(2))) == 3) {
								return 	(aprt == apart - 1) ||
										(aprt == apart + 1)||
										(aprt == apart + 99)||
										(aprt == apart + 101)||
										(aprt == apart + 100);
							}  else if(Integer.parseInt(String.valueOf(apartString.charAt(2))) == 4) {
							return 	(aprt == apart - 1) ||
									(aprt == apart + 99)||
									(aprt == apart + 100);
							}
							else {return false;}
							
						}).collect(Collectors.toList());
				Vertice vo = g.addVertice(apart.toString());
				for (Integer neighbor : neighbors) {
					if(Integer.parseInt(String.valueOf(apartString.charAt(2))) == 1) {
						neighbor += 1;
					}
					
					Vertice vd = g.addVertice(neighbor.toString());
					g.addAresta(vo, vd);
				}
				
				System.out.println("adjascentes do vertice " + vo.getNome() + ": " + vo.getAdj());
				writer.append("\r\n");
				writer.append("adjascentes do vertice " + vo.getNome() + ": " + vo.getAdj());
				writer.append("\r\n");
			}
			writer.append("\r\n");
			writer.append("\r\n");
			writer.append("\r\n");
			writer.append("Erros: " + errors);
			writer.append("\r\n");
			writer.append("Quantidade de números duplicados: " + indexDuplicado);
			writer.append("\r\n");
			writer.append("Quantidade de números negativos: " + indexNegativo);
			writer.append("\r\n");
			writer.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
