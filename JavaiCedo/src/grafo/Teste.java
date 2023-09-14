package grafo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Teste {

	public static void main(String[] args) {
		// Grafo g = new Grafo();
		//	Vertice s = g.addVertice("s");
		//Vertice t = g.addVertice("t");
		//Vertice y = g.addVertice("y");
		
		//	g.addAresta(s, t);
		//g.addAresta(s, y);
		//g.addAresta(t, y);
		//g.addAresta(y, t);
		
		 // System.out.println(g);
		
		try {
			FileInputStream fis = new FileInputStream("C:\\Temp\\configs.txt");
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			LineNumberReader ln = new LineNumberReader(br);
			Scanner sn = new Scanner(fis);
			String line =" ";
			
			List<Integer> apartaments = new ArrayList<>();
			Map<String, Integer> errors = new HashMap<>();
			List<List<Integer>> apartamentsMatrix = new ArrayList<>();
			Integer indice = 0;
			Integer apt= 0;
			
			while ((line = br.readLine()) != null) {
				if (indice == 0) {
					System.out.println(line);
					indice+=1;
					continue;
				}
				indice+=1;
				String[] lineArray = line.split(" ");				
				
				if(lineArray[1].equals("TRAILER33") ) {
					break;
				}
			
				Integer apartament = Integer.parseInt(lineArray[1]);
				if (apartament >= 0 && !apartaments.contains(apartament)) {
					apartaments.add(apartament);
				} else {
					errors.put(lineArray[0], apartament);
				}
				
			}
			//System.out.println( errors);
			//System.out.println( apartaments.size());
			//System.out.println(apartaments + " Indice: "+indice);
			br.close();
			
			// add to matrix following the graph
			
			for (Integer apart : apartaments) {
				Grafo g = new Grafo();
				
				String apartString= apart.toString();
				System.out.println(apart);
				System.out.println(Integer.parseInt(String.valueOf(apartString.charAt(2))));
				List<Integer> neighbors = apartaments
						.stream()
						.filter(aprt -> {
							String aprtString= aprt.toString();
							if(Integer.parseInt(String.valueOf(aprtString.charAt(2))) == 1) {
								return 	apart == (aprt + 1);
							}
							else if(Integer.parseInt(String.valueOf(aprtString.charAt(2))) == 2) {
								return 	apart == (aprt - 1) ||
										apart == (aprt + 1) ||
										apart == (aprt + 99) ||
										apart == (aprt + 100);
							}else if(Integer.parseInt(String.valueOf(aprtString.charAt(2))) == 3) {
								return 	(apart == aprt - 1) ||
										(apart == aprt + 1)||
										(apart == aprt + 99)||
										(apart == aprt - 100)||
										(apart == aprt - 101)||
										(apart == aprt + 100);
							}
							else {
								return 	aprt == apart - 1 || 
										aprt == apart - 100 ||
										aprt == apart + 100 ||
										aprt == apart - 101;
							}
							
						}).collect(Collectors.toList());
				Vertice vo = g.addVertice(apart.toString());
				
				for (Integer neighbor : neighbors) {
					Vertice vd = g.addVertice(neighbor.toString());
					g.addAresta(vo, vd);
				}
				
				System.out.println("adjascentes do vertice " + vo.getNome() + ": " + vo.getAdj());
				
				//System.out.println(g);
			}
			
			// print matrix
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
