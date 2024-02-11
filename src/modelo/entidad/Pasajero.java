package modelo.entidad;


public class Pasajero {
	
	
	private int id;
	private String nombre;
	private int edad;
	private float peso;
	
	
	public Pasajero( String nombre, int edad, float peso) {
		super();
		
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public float getPeso() {
		return peso;
	}


	public void setPeso(float peso) {
		this.peso = peso;
	}


	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso + "]";
	}
	
	

}
