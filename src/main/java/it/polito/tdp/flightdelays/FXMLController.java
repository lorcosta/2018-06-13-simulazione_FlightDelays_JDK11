package it.polito.tdp.flightdelays;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.flightdelays.db.Arco;
import it.polito.tdp.flightdelays.model.Airline;
import it.polito.tdp.flightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private ComboBox<Airline> cmbBoxLineaAerea;

    @FXML
    private Button caricaVoliBtn;

    @FXML
    private TextField numeroPasseggeriTxtInput;

    @FXML
    private TextField numeroVoliTxtInput;

    @FXML
    void doCaricaVoli(ActionEvent event) {
    	this.txtResult.clear();
    	Airline airline=this.cmbBoxLineaAerea.getValue();
    	if(airline==null) {
    		this.txtResult.appendText("ATTENZIONE! Nessuna linea aerea selezionata.\n");
    	}
    	List<Arco> peggioriRotte=model.creaGrafo(airline);
    	Integer vertici=model.getNumVertici(), archi=model.getNumArchi();
    	if(vertici==0 || archi.equals(0)) {
    		this.txtResult.appendText("ATTENZIONE! Qualcosa e' andato storto nella creazione del grafo.\n");
    		return;
    	}
    	this.txtResult.appendText("GRAFO CREATO!\n #VERTICI: "+vertici+" e #ARCHI: "+archi+"\n");
    	if(peggioriRotte.size()==0) {
    		this.txtResult.appendText("ATTENZIONE! Qualcosa e' andato storto nella creazione del grafo.\n");
    		return;
    	}
    	this.txtResult.appendText("Stampo le 10 peggiori rotte:\n");
    	for(int i=0;i<10;i++) {
    		this.txtResult.appendText(peggioriRotte.get(i).toString()+"\n");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	String passeggeriString=this.numeroPasseggeriTxtInput.getText();
    	String voliString=this.numeroVoliTxtInput.getText();
    	Integer passeggeri,voli;
    	try {
    		passeggeri=Integer.parseInt(passeggeriString);
    	}catch( NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.appendText("ATTENZIONE! Il valore inserito per i passeggeri non e' un numero consentito.\n");
    		return;
    	}
    	try {
    		voli=Integer.parseInt(voliString);
    	}catch( NumberFormatException e) {
    		e.printStackTrace();
    		this.txtResult.appendText("ATTENZIONE! Il valore inserito per i passeggeri non e' un numero consentito.\n");
    		return;
    	}
    	model.simula(passeggeri,voli);
    }
    
    void loadData(){
    	this.cmbBoxLineaAerea.getItems().addAll(model.getAirlines());
    }
    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert cmbBoxLineaAerea != null : "fx:id=\"cmbBoxLineaAerea\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert caricaVoliBtn != null : "fx:id=\"caricaVoliBtn\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert numeroPasseggeriTxtInput != null : "fx:id=\"numeroPasseggeriTxtInput\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert numeroVoliTxtInput != null : "fx:id=\"numeroVoliTxtInput\" was not injected: check your FXML file 'FlightDelays.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		loadData();
	}
}
