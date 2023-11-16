package uy.edu.um.proyectotic.controladores.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.AeropuertoTransporte;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.servicios.AeropuertoRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("crearAeropuerto.fxml")
public class crearAeropuertoController {
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AeropuertoRestService aeropuertoRestService;

    @FXML
    private TextField contrasenaAeropuerto;

    @FXML
    private TextField emailCrearAeropuerto;

    @FXML
    private TextField paisCrearAeropuerto;

    @FXML
    private TextField cantidadPuestosCheckInAeropuerto;

    @FXML
    private Button botonEviarCrearAeropuerto;

    @FXML
    private TextField cantidadMangasAeropuerto;

    @FXML
    private TextField codigoIataAeropuerto;

    @FXML
    private TextField cantidadPuertasAeropuerto;

    @FXML
    private Button botonAtrasCrearAeropuerto;

    @FXML
    private TextField nombreCearAeropuerto;

    public void atras(ActionEvent actionEvent) {
        configuraciones.cambiarPantalla(botonAtrasCrearAeropuerto.getScene(), vistaAdminController.class,applicationContext);
    }

    public void crearAeropuerto(ActionEvent actionEvent) {
        ResponseEntity<AeropuertoTransporte> aeropuertoResponse=null;

        try{
            aeropuertoResponse=aeropuertoRestService.crearAeropuerto(emailCrearAeropuerto.getText(), contrasenaAeropuerto.getText(), paisCrearAeropuerto.getText(), codigoIataAeropuerto.getText(), nombreCearAeropuerto.getText(), cantidadPuertasAeropuerto.getText(), cantidadMangasAeropuerto.getText(), cantidadPuestosCheckInAeropuerto.getText());
            showAlert("Exito en la creacion!", "Se ha creado el aeropuerto "+nombreCearAeropuerto.getText()+" y el usuario "+emailCrearAeropuerto.getText());
        } catch (Exception e){
            
            showAlert("Datos Invalidos", "Error al crear aeropuerto");
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
