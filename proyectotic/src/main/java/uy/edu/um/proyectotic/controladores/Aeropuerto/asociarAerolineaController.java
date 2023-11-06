package uy.edu.um.proyectotic.controladores.Aeropuerto;

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
import uy.edu.um.AsociacionTransporte;
import uy.edu.um.proyectotic.controladores.Aerolinea.vistaAerolineaController;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AeropuertoRestService;

@Controller
@FxmlView("AsociarAerolinea.fxml")
public class asociarAerolineaController {
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AeropuertoRestService aeropuertoRestService;


    @FXML
    private Button botonAtrasAsociarAerolinea;

    @FXML
    private Button botonAsociarAerolinea;

    @FXML
    private TextField iataAerolinea;

    @FXML
    void asociarAerolinea(ActionEvent event) {
        ResponseEntity<AsociacionTransporte> asociacionResponseEntity=null;
        UserSession usr=UserSession.getInstace();

        try{
            asociacionResponseEntity=aeropuertoRestService.asociarAerolinea(iataAerolinea.getText(), usr.getEmpresa());
            if(asociacionResponseEntity.getStatusCode()==HttpStatus.OK){
                showAlert("Exito en la creacion!", "Se ha asociado la aerolinea "+iataAerolinea.getText());
            }
        } catch (Exception e){
            
            showAlert("Datos Invalidos", "Error al asociar aerolinea");
        }


    }

    @FXML
    void atras(ActionEvent event) {
        configuraciones.cambiarPantalla(botonAtrasAsociarAerolinea.getScene(), vistaAerolineaController.class,applicationContext);
    }
    
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
