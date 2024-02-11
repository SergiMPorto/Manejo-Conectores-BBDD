package modelo.entidad;





public class Coche {
	
	
	
	private int id;
	private String marca;
	private String modelo;
	private int anhoFabricacion;
	private double kilometraje;
	
	
	public Coche() {
		super();
	}


	public Coche(String marca, String modelo, int anhoFabricacion, double kilometraje) {
		super();
		
		this.marca = marca;
		this.modelo = modelo;
		this.anhoFabricacion = anhoFabricacion;
		this.kilometraje = kilometraje;
		
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public int getAnhoFabricacion() {
		return anhoFabricacion;
	}


	public void setAnhoFabricacion(int anhoFabricacion) {
		this.anhoFabricacion = anhoFabricacion;
	}


	public double getKilometraje() {
		return kilometraje;
	}


	public void setKilometraje(double kilometraje) {
		this.kilometraje = kilometraje;
	}


	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", anhoFabricacion=" + anhoFabricacion
				+ ", kilometraje=" + kilometraje + "]";
	}
	
}
