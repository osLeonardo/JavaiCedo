package grafo_pt2;

public class Model {
	private String identificador;
	private String origem;
	private String destino;
	private Integer peso;
	
	
	
	public Model(String identificador, String origem, String destino, Integer peso) {
		this.identificador = identificador;
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}
	
	public Model(String identificador, String origem, String destino) {
		this.identificador = identificador;
		this.origem = origem;
		this.destino = destino;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	
}
