package it.polito.tdp.flightdelays.model;

import java.util.HashMap;
import java.util.Map;


public class Simulator {
	private Map<Airport, Integer> airportPassengers;
	
	public void init(Integer passeggeri, Integer voli,Map<Integer,Airport> idMapAirport) {
		this.airportPassengers=new HashMap<>();
		int i=0;
		for(Airport a:idMapAirport.values()) {
			airportPassengers.put(a, 0);//inizializzo gli aereoporti con 0 passeggeri
		}
		//finchè i passeggeri da inserire negli aereoporti non sono finiti, decido casualmente dove metterli
		while(i<passeggeri) {
			for(Airport a:airportPassengers.keySet()) {
				if(Math.random()<0.5) {
					Integer value=airportPassengers.get(a);
					airportPassengers.put(a, value+1);
					i++;
				}
			}
		}
		
	}

	public void run() {
		
	}

}
