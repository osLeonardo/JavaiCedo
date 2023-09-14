package files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class readFiles {

	public static void main(String[] args) {
		try {
			InputStream is =  new FileInputStream("C:\\Temp\\configs.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = " ";
			int indice = 0;
			
			
			while ((linha =  buffer.readLine())!= null) {
				System.out.println(linha);
				
				indice ++;
				
			}
			System.out.println("Linha"+indice+": "+"TRAILER33");
			buffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
