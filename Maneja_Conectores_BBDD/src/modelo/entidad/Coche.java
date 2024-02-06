package modelo.entidad;

public class Coche {

	private int id;
	private String modelo;
	private int yearFabricacion;
	private int km;
	
	
	
	public Coche(int id, String modelo, int yearFabricacion, int km) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.yearFabricacion = yearFabricacion;
		this.km = km;
	}

	
	
	
	//Getter and Setter
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getModelo() {
		return modelo;
	}



	public Coche() {
		super();
		// TODO Auto-generated constructor stub
	}




	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public int getYearFabricacion() {
		return yearFabricacion;
	}



	public void setYearFabricacion(int yearFabricacion) {
		this.yearFabricacion = yearFabricacion;
	}



	public int getKm() {
		return km;
	}



	public void setKm(int km) {
		this.km = km;
	}




	@Override
	public String toString() {
		return "Coche [id=" + id + ", modelo=" + modelo + ", yearFabricacion=" + yearFabricacion + ", km=" + km + "]";
	}
	
	

	

}
