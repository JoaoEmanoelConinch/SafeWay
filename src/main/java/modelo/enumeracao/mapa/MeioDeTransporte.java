package modelo.enumeracao.mapa;

public enum MeioDeTransporte {

	DRIVING_CAR("driving-car"), 
	DRIVING_HGV("driving-hgv"), 
	CYCLING_REGULAR("cycling-regular"),
	CYCLING_ROAD("cycling-road"), 
	CYCLIHG_MOUNTAIN("cycling-mountain"), 
	CYCCLING_ELECTRIC("cycling-electric"),
	FOOT_WALKING("foot-walking"), 
	FOOT_HIKING("foot-hiking"), 
	WHEELCHAIR("wheelchair");
	
	private String descricao;
	
	MeioDeTransporte(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
