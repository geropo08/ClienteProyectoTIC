package uy.edu.um.proyectotic.controladores.Cliente;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.PasajerosVuelosT;
import uy.edu.um.VuelosDTO;
import uy.edu.um.proyectotic.controladores.loginController;
import uy.edu.um.proyectotic.controladores.Aerolinea.asociarPasajeroVueloController;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.Datos;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AerolineaRestService;
import uy.edu.um.proyectotic.servicios.AeropuertoRestService;
import uy.edu.um.proyectotic.servicios.VueloRestService;

import java.util.List;

@Controller
@FxmlView("listaVuelosDisponibles.fxml")
public class vuelosDisponiblesController {
    @Autowired
    Configuraciones conf;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AeropuertoRestService aeropuertoRestService;
    @Autowired
    VueloRestService vueloRestService;
    @Autowired
    AerolineaRestService aerolineaRestService;

    @FXML
    private TableView<VuelosDTO> TablaVuelo;

    @FXML
    private Button botonAtrasVuelo;

    @FXML
    private Button buscarVuelos;

    @FXML
    private Button comprarVuelos;

    @FXML
    private TableColumn<?, ?> columnaAeropuertoLlegada;

    @FXML
    private TableColumn<?, ?> columnaAerppuertoSalida;

    @FXML
    private TableColumn<?, ?> columnaCodigo;

    @FXML
    private TableColumn<?, ?> columnaEstadoVuelo;

    @FXML
    private TableColumn<?, ?> columnaFechaLlegada;

    @FXML
    private TableColumn<?, ?> columnaFechaSalida;

    @FXML
    private ComboBox<String> paisDestino;

    @FXML
    private ComboBox<String> paisSalida;

    public void initialize(){
        ResponseEntity<List<String>> paisesAeropuertos=null;
        try{
            paisesAeropuertos=aeropuertoRestService.paisesDisponibles();
        } catch(Exception e){
            showAlert("Error", "Error al buscar aeropuertos");
        }
        paisSalida.getItems().removeAll(paisSalida.getItems());
        paisSalida.getItems().setAll(paisesAeropuertos.getBody());
        paisDestino.getItems().removeAll(paisDestino.getItems());
        paisDestino.getItems().setAll(paisesAeropuertos.getBody());
        


    }

    @FXML
    void atras(ActionEvent event) {
        conf.cambiarPantalla(botonAtrasVuelo.getScene(), vistaClienteController.class,applicationContext);
    }

    @FXML
    void buscar(ActionEvent event) {
        TablaVuelo.setItems(null);
        ResponseEntity<List<VuelosDTO>> vuelosDisponibles= null;
        if(paisSalida.getValue()==null||paisDestino.getValue()==null){
            showAlert("Error", "Debe ingresar una salida y un destino");
        } else {
            try{
                vuelosDisponibles=vueloRestService.vuelosDisponibles(paisSalida.getValue(),paisDestino.getValue());
            } catch (Exception e){
                showAlert("Error", "Error al buscar vuelos.");
            }
            if(!vuelosDisponibles.getBody().isEmpty()){
                ObservableList<VuelosDTO> vuelosDTOObservableList = FXCollections.observableArrayList(vuelosDisponibles.getBody());
                TablaVuelo.setItems(vuelosDTOObservableList);

                columnaAeropuertoLlegada.setCellValueFactory(new PropertyValueFactory("aeropuertoLlegada"));
                columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigoVuelo"));
                columnaAerppuertoSalida.setCellValueFactory(new PropertyValueFactory("aeropuertoSalida"));
                columnaFechaLlegada.setCellValueFactory(new PropertyValueFactory("fechaLlegada"));
                columnaEstadoVuelo.setCellValueFactory(new PropertyValueFactory("estadoVuelo"));
                columnaFechaSalida.setCellValueFactory(new PropertyValueFactory("fechaSalida"));
            } else {
                showAlert("No Disponible", "No hay vuelos disponibles para ese origen y destino");

            }
        }
        

    }

    @FXML
    void comprar(ActionEvent event){
        if(TablaVuelo.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ningun vuelo");
        }else{
            VuelosDTO vueloDTO = TablaVuelo.getSelectionModel().getSelectedItems().get(0);
            UserSession usr=UserSession.getInstace();
            ResponseEntity<PasajerosVuelosT> pasajerosVuelosResponseEntity=null;
            try{
                pasajerosVuelosResponseEntity=aerolineaRestService.asignarVueloPasajero(vueloDTO.getCodigoVuelo(), usr.getPasaporte());
                if(pasajerosVuelosResponseEntity.getStatusCode()==HttpStatus.OK){
                    showAlert("Exito en la creacion!", "Se ha asociado el pasajero al vuelo.");
                }
            } catch (Exception e){
                showAlert("Error", "Error al asociar pasajero.");
            }
            
        }
        
    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
