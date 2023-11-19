package uy.edu.um.proyectotic.controladores.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.proyectotic.controladores.loginController;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;

@Controller
@FxmlView("VistaCliente.fxml")
public class vistaClienteController {
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    Configuraciones conf;

    @FXML
    private Button botonCerrarSesionCliente;

    @FXML
    public void verVuelos(ActionEvent actionEvent){
        conf.cambiarPantalla(botonCerrarSesionCliente.getScene(), vuelosCompradosController.class,applicationContext);

    }
    @FXML
    public void listaVuelos(ActionEvent actionEvent){
        conf.cambiarPantalla(botonCerrarSesionCliente.getScene(), vuelosDisponiblesController.class,applicationContext);

    }



    @FXML
    public void cerrarSesion(ActionEvent actionEvent) {
        UserSession.leaveInstance();
        conf.cambiarPantalla(botonCerrarSesionCliente.getScene(), loginController.class,applicationContext);
    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
    
}
