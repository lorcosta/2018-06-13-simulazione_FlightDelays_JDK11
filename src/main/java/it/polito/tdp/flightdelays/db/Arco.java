package it.polito.tdp.flightdelays.db;

import it.polito.tdp.flightdelays.model.Airport;


public class Arco implements Comparable<Arco>{
	private Airport a1;
	private Airport a2;
	private Double mediaRitardo;
	private Double distanza;
	private Double peso;
	/**
	 * @param a1
	 * @param a2
	 * @param mediaRitardo
	 */
	public Arco(Airport a1, Airport a2, Double mediaRitardo,Double distanza) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.mediaRitardo = mediaRitardo;
		this.distanza=distanza;
		this.peso=mediaRitardo/distanza;
	}
	public Airport getA1() {
		return a1;
	}
	public void setA1(Airport a1) {
		this.a1 = a1;
	}
	public Airport getA2() {
		return a2;
	}
	public void setA2(Airport a2) {
		this.a2 = a2;
	}
	public Double getMediaRitardo() {
		return mediaRitardo;
	}
	public void setMediaRitardo(Double mediaRitardo) {
		this.mediaRitardo = mediaRitardo;
	}
	public Double getPeso() {
		return peso;
	}
	public Double getDistanza() {
		return distanza;
	}
	@Override
	public int compareTo(Arco o) {
		return this.peso.compareTo(o.getPeso());
	}
	@Override
	public String toString() {
		return a1+"-->"+a2+"/PESO: "+peso;
	}
	
	
}
