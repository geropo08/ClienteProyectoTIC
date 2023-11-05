package uy.edu.um.proyectotic.controladores.Aerolinea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.proyectotic.controladores.crearEmpleadoController;
import uy.edu.um.proyectotic.persistencia.Configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

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
    }
    @FXML
    public void buscarAvion(ActionEvent actionEvent) {
    }
    @FXML
    public void crearVuelo(ActionEvent actionEvent) {
    }
    @FXML
    public void buscarVuelo(ActionEvent actionEvent) {
    }
    @FXML
    public void informesVuelo(ActionEvent actionEvent) {
    }
    @FXML
    public void crearEmpleado(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonCerrarSesionAerolinea.getScene(), crearEmpleadoController.class,applicationContext);

    }
    @FXML
    public void buscarEmpleado(ActionEvent actionEvent) {
    }
    @FXML
    public void informesEmpleado(ActionEvent actionEvent) {
    }
}
