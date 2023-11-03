package uy.edu.um.proyectotic.controladores.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.AerolineaTransporte;
import uy.edu.um.AeropuertoTransporte;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.servicios.AerolineaRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("crearAerolinea.fxml")
public class crearAerolineasController {

    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AerolineaRestService aerolineaRestService;

    @FXML
    private TextField nombre_aerolinea_reg_aeropuerto;

    @FXML
    private TextField sede_principal_aerolinea_reg_aeropuerto;

    @FXML
    private TextField contacto_aerolinea_reg_aeropuerto;

    @FXML
    private TextField codigo_aerolinea_reg_aeropuerto;

    @FXML
    private Button botonAtrasCrearAerolinea;

    @FXML
    private Button enviar_aerolinea_aeropuerto;

    @FXML
    private TextField contrasenaAerolineaCreacion;

    @FXML
    private TextField emailAerolineaCreacion;

    @FXML
    void crearAereolinea(ActionEvent event) {
         ResponseEntity<AerolineaTransporte> aerolineaTransporte=null;

        try{
            aerolineaTransporte=aerolineaRestService.crearAerolinea(nombre_aerolinea_reg_aeropuerto.getText(), codigo_aerolinea_reg_aeropuerto.getText(), contacto_aerolinea_reg_aeropuerto.getText(), sede_principal_aerolinea_reg_aeropuerto.getText(), emailAerolineaCreacion.getText(), contrasenaAerolineaCreacion.getText());
            if(aerolineaTransporte.getStatusCode()==HttpStatus.OK){
                showAlert("Exito en la creacion!", "Se ha creado la aerolinea "+nombre_aerolinea_reg_aeropuerto.getText()+" y el usuario "+emailAerolineaCreacion.getText());
            }
        } catch (Exception e){
            
            showAlert("Datos Invalidos", "Error al crear aerolinea");
        }
    }

    @FXML
    void atras(ActionEvent event) {
        configuraciones.cambiarPantalla(botonAtrasCrearAerolinea.getScene(), vistaAdminController.class,applicationContext);
    }
    
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
