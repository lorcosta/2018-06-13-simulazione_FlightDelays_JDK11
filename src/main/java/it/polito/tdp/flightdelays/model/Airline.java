package it.polito.tdp.flightdelays.model;

public class Airline {
	
	private Integer id;
	private String name;
	
	public Airline(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Airline [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}*/
	@Override
	public String toString() {
		return name;
	}
}
