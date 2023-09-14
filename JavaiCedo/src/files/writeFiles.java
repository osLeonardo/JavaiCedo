package files;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class writeFiles {

	public static void main(String[] args) {
		try {
			OutputStream os = new FileOutputStream("C:\\Temp\\teste.txt");
			OutputStreamWriter osw =  new OutputStreamWriter(os);
			BufferedWriter writer = new BufferedWriter(osw);
			
			writer.append("teste");
			writer.append("\r\n");
			writer.append("teste");
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
