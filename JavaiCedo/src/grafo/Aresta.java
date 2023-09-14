package grafo;

public class Aresta {
	
	private Vertice origem;	
	private Vertice destino;
	
	public Aresta(final Vertice origem, final Vertice destino) {
		this.origem = origem;
		this.destino = destino;
	}
	
	public Vertice getOrigem() {
		return origem;
	}
	
	public Vertice getDestino() {
		return destino;
	}
	
	@Override
	public String toString() {
		return "Aresta [origem=" + origem + ", destino=" + destino + "]";
	}
}
