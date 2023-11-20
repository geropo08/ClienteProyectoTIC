package uy.edu.um.proyectotic.controladores.Aerolinea;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.PasajerosVuelosT;
import uy.edu.um.proyectotic.controladores.Aeropuerto.vistaAeropuertoController;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.Datos;
import uy.edu.um.proyectotic.servicios.AerolineaRestService;

@Controller
@FxmlView("AsociarCliente.fxml")
public class asociarPasajeroVueloController {
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    Configuraciones conf;
    @Autowired
    AerolineaRestService aerolineaRestService;

    @FXML
    private Button botonAsociarPasajero;

    @FXML
    private Button botonAtrasAsociarPasajero;

    @FXML
    private TextField pasaportePasajero;

    @FXML
    void asociarPasajero(ActionEvent event) {
        if(pasaportePasajero.getText()!=null){
            ResponseEntity<PasajerosVuelosT> pasajerosVuelosResponseEntity=null;
            Datos data=Datos.getInstace();
            try{
                pasajerosVuelosResponseEntity=aerolineaRestService.asignarVueloPasajero(data.getVuelo().getCodigoVuelo(), pasaportePasajero.getText());
                if(pasajerosVuelosResponseEntity.getStatusCode()==HttpStatus.OK){
                    
                    showAlert("Exito en la creacion!", "Se ha asociado el pasajero al vuelo.");
                }
            } catch (Exception e){
                showAlert("Error", "Error al asociar pasajero.");
            }

        } else {
            showAlert("Error", "Debe ingresar un pasaporte!");
        }

    }

    @FXML
    void atras(ActionEvent event) {
        Datos.leaveInstance();
        conf.cambiarPantalla(botonAtrasAsociarPasajero.getScene(), vistaAerolineaController.class,applicationContext);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
