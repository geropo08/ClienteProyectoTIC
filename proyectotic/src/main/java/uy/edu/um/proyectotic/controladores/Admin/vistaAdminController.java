package uy.edu.um.proyectotic.controladores.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import uy.edu.um.proyectotic.persistencia.Configuraciones;

@Controller
@FxmlView("vistaAdmin.fxml")
public class vistaAdminController {

    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    Configuraciones conf;

    @FXML
    private Button botonCerrarSesionAdmin;

    //===================================== AEROLINEA =====================================
    @FXML
    public void crearAerolinea(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAdmin.getScene(), crearAerolineasController.class,applicationContext);
    }
    @FXML
    public void buscarAerolinea(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAdmin.getScene(), listaAerolineasController.class,applicationContext);
    }
    @FXML
    public void informesAerolinea(ActionEvent actionEvent) {
        showAlert("No funciona", "No funciona");
        //conf.cambiarPantalla(botonCerrarSesionAdmin.getScene(), informesAerolineaController.class,applicationContext);
    }

    //===================================== AEROPUERTO =====================================
    @FXML
    public void crearAeropuerto(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAdmin.getScene(), crearAeropuertoController.class,applicationContext);
    }
    @FXML
    public void buscarAeropuerto(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAdmin.getScene(), listaAeropuertosController.class,applicationContext);
    }
    @FXML
    public void informesAeropuerto(ActionEvent actionEvent) {
        showAlert("No funciona", "No funciona");
        //conf.cambiarPantalla(botonCerrarSesionAdmin.getScene(), informesAeropuertoController.class,applicationContext);
    }
    @FXML
    public void cerrarSesion(ActionEvent actionEvent) {
    }


    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}

