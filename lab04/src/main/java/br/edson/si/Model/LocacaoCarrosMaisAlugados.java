package br.edson.si.Model;

public class LocacaoCarrosMaisAlugados {
	private Long idCarro;
	private Long vezesAlugado;
	public LocacaoCarrosMaisAlugados(Long idCarro, Long vezesAlugado) {
		super();
		this.idCarro = idCarro;
		this.vezesAlugado = vezesAlugado;
	}
	public Long getIdCarro() {
		return idCarro;
	}
	public void setIdCarro(Long idCarro) {
		this.idCarro = idCarro;
	}
	public Long getVezesAlugado() {
		return vezesAlugado;
	}
	public void setVezesAlugado(Long vezesAlugado) {
		this.vezesAlugado = vezesAlugado;
	}
	
	
	
}
