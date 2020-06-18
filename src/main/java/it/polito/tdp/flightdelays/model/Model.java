package it.polito.tdp.flightdelays.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.flightdelays.db.Arco;
import it.polito.tdp.flightdelays.db.FlightDelaysDAO;

public class Model {
	private FlightDelaysDAO dao= new FlightDelaysDAO();
	private Graph<Airport, DefaultWeightedEdge> graph;
	private Map<Integer, Airport> idMapAirport;
	public List<Airline> getAirlines() {
		return dao.getAirline();
	}
	
	public List<Arco> creaGrafo(Airline airline) {
		this.graph=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		this.idMapAirport=new HashMap<>();
		List<Airport> airports=dao.loadAllAirports(idMapAirport);
		Graphs.addAllVertices(this.graph, airports);
		List<Arco> flights=dao.getFlights(airline, idMapAirport);
		for(Arco a:flights) {
			if(this.graph.vertexSet().contains(a.getA1()) && this.graph.vertexSet().contains(a.getA1()) &&  
					this.graph.getEdge(a.getA1(), a.getA2())==null && !a.getA1().equals(a.getA2())) {
				if(a.getPeso()>0) {
					Double peso=-(a.getPeso());
					Graphs.addEdgeWithVertices(this.graph, a.getA1(), a.getA2(), peso);
				}else {
					Graphs.addEdgeWithVertices(this.graph, a.getA2(), a.getA1(), a.getPeso());
				}
			}
		}
		Collections.sort(flights);
		return flights;
	}
	public Integer getNumVertici() {
		return this.graph.vertexSet().size();
	}
	public Integer getNumArchi() {
		return this.graph.edgeSet().size();
	}
}
