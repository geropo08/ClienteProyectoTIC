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
import uy.edu.um.AvionesDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AvionRestService;

@Controller
@FxmlView("CrearAvion.fxml")
public class crearAvionController {
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AvionRestService avionRestService;

    @FXML
    private TextField CapacidadCargaCrearAvionAerolinea;

    @FXML
    private TextField CapacidadPersonasCrearAvionAerolinea;

    @FXML
    private TextField DistanciaCrearAvionAerolinea;

    @FXML
    private TextField MatriculaCrearAvionAerolinea;

    @FXML
    private TextField ModeloCrearAvionAerolinea;

    @FXML
    private Button botonAtrasAvionAerolinea;

    @FXML
    private Button botonCrearAvionAerolinea;

    @FXML
    void crearAvion(ActionEvent event){
        ResponseEntity<AvionesDTO> avionResponseEntity=null;
        UserSession usr=UserSession.getInstace();

        try{
            avionResponseEntity=avionRestService.crearAvion(MatriculaCrearAvionAerolinea.getText(),ModeloCrearAvionAerolinea.getText(), CapacidadPersonasCrearAvionAerolinea.getText(), CapacidadCargaCrearAvionAerolinea.getText(), DistanciaCrearAvionAerolinea.getText(), usr.getEmpresa() );
            if(avionResponseEntity.getStatusCode()==HttpStatus.OK){
                showAlert("Exito en la creacion!", "Se ha creado el avion con matricula "+MatriculaCrearAvionAerolinea.getText());
            }
        } catch (Exception e){
            
            showAlert("Datos Invalidos", "Error al crear el avion");
        }

    }

    @FXML
    void atras(ActionEvent event) {
        configuraciones.cambiarPantalla(botonAtrasAvionAerolinea.getScene(), vistaAerolineaController.class,applicationContext);
    }
    
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
