package uy.edu.um.proyectotic.controladores.Aerolinea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.proyectotic.controladores.crearEmpleadoController;
import uy.edu.um.proyectotic.controladores.loginController;
import uy.edu.um.proyectotic.persistencia.Configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import uy.edu.um.proyectotic.persistencia.UserSession;

@Controller
@FxmlView("vistaAerolinea.fxml")
public class vistaAerolineaController {
    @Autowired
    Configuraciones conf;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    
    @FXML
    private Button botonCerrarSesionAerolinea;

    @FXML
    public void crearAvion(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAerolinea.getScene(), crearAvionController.class,applicationContext);
    }
    @FXML
    public void buscarAvion(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAerolinea.getScene(), listaAvionesAerolineaController.class,applicationContext);
    }
    @FXML
    public void crearVuelo(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAerolinea.getScene(), crearVueloController.class,applicationContext);
    }
    @FXML
    public void buscarVuelo(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAerolinea.getScene(), listaVuelosAerolineasController.class,applicationContext);
    }
    @FXML
    public void asociarPasajero(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAerolinea.getScene(), seleccionarVueloController.class,applicationContext);
    }
    @FXML
    public void informesVuelo(ActionEvent actionEvent) {
        showAlert("Informes", "No hay informes disponibles");
    }
    @FXML
    public void crearEmpleado(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAerolinea.getScene(), crearEmpleadoController.class,applicationContext);

    }
    @FXML
    public void buscarEmpleado(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAerolinea.getScene(), listaEmpleadosController.class,applicationContext);
    }
    @FXML
    public void informesEmpleado(ActionEvent actionEvent) {
        showAlert("Informes", "No hay informes disponibles");
    }

    @FXML
    public void cerrarSesion(ActionEvent actionEvent) {
        UserSession.leaveInstance();
        conf.cambiarPantalla(botonCerrarSesionAerolinea.getScene(), loginController.class,applicationContext);
    }


    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
