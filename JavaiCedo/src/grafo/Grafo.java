package grafo;
import java.util.ArrayList;
import java.util.List;

public class Grafo {

	private List<Vertice> listaVertices;
	
	public Grafo() {
		listaVertices = new ArrayList<Vertice>();
	}
	
	public Vertice addVertice(final String nome) {
		Vertice v = new Vertice(nome);
		listaVertices.add(v);
		return v;
	}
	
	public Aresta addAresta(final Vertice origem, final Vertice destino) {
		Aresta a = new Aresta(origem, destino);
		origem.getAdj().add(a);
		return a;
	}
	
	public int getDegree(Vertice vertice) {
		return vertice.getAdj().size();
	}
	
	public int getInDegree(Vertice vertice) {
		int inDegree = 0;
		for (Vertice v : listaVertices) {
			for (Aresta a : v.getAdj()) {
				if (a.getDestino() == vertice) {
					inDegree++;
				}
			}
		}
		return inDegree;
	}
	
	public void printDegree() {
		for (Vertice v : listaVertices) {
			int degree = getDegree(v);
			int inDegree = getInDegree(v);
			System.out.println("Grau de emissão de "+v.getNome()+": "+degree);
			System.out.println("Grau de recepção de "+v.getNome()+": "+inDegree );
		}
	}
	
	@Override
	public String toString() {
		
		String r = "";
		
		for (Vertice v : listaVertices) {
			r += v.getNome() +" -> ";
			for (Aresta a : v.getAdj()) {
				v = a.getDestino();
				r += "["+v.getNome()+"] -> ";
			}
			r += "[/]";
			r += "\n";
		}		
		return r;
	}
}
