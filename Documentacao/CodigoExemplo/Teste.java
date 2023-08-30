
public class Teste {
	
	public static void main(String[] args) {
		
		Grafo g = new Grafo();
		Vertice s = g.addVertice("s");
		Vertice t = g.addVertice("t");
		Vertice y = g.addVertice("y");
		
		g.addAresta(s, t);
		g.addAresta(s, y);
		g.addAresta(t, y);
		g.addAresta(y, t);
		
		System.out.println(g);
		
	}

}
