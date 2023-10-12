package grafo_pt2;

public class Model {
	private String identificador;
	private Integer origem;
	private Integer destino;
	private Integer peso;
	
	
	
	public Model(String identificador, Integer origem, Integer destino, Integer peso) {
		this.identificador = identificador;
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}
	
	public Model(String identificador, Integer origem, Integer destino) {
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
	public Integer getOrigem() {
		return origem;
	}
	public void setOrigem(Integer origem) {
		this.origem = origem;
	}
	public Integer getDestino() {
		return destino;
	}
	public void setDestino(Integer destino) {
		this.destino = destino;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	
}
