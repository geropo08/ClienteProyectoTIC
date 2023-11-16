package uy.edu.um.proyectotic.controladores.Aeropuerto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.proyectotic.controladores.loginController;
import uy.edu.um.proyectotic.persistencia.Configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import uy.edu.um.proyectotic.persistencia.UserSession;

@Controller
@FxmlView("vistaAeropuerto.fxml")
public class vistaAeropuertoController {
    @Autowired
    Configuraciones conf;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @FXML
    private Button BotonCerrarSesionAerolinea;

    @FXML
    private MenuItem AceptarVuelo;

    public void asociarAerolinea(ActionEvent actionEvent) {
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), asociarAerolineaController.class,applicationContext);
    }

    public void buscarAerolinea(ActionEvent actionEvent) {
    }
    public void crearPuerta(ActionEvent actionEvent){
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), crearPuertaController.class,applicationContext);
    }
    public void crearPista(ActionEvent actionEvent){
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), crearPistaController.class,applicationContext);

    }


    public void informesAerolineas(ActionEvent actionEvent) {
    }

    public void aceptarVuelos(ActionEvent actionEvent) {
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), confirmarVueloAerolineaController.class,applicationContext);
    }

    public void buscarVuelos(ActionEvent actionEvent) {
    }

    public void informesVuelos(ActionEvent actionEvent) {
    }

    public void crearTiendas(ActionEvent actionEvent) {
        
    }

    public void buscarTiendas(ActionEvent actionEvent) {
    }

    public void informesTiendas(ActionEvent actionEvent) {
    }

    public void crearEmpleado(ActionEvent actionEvent) {
    }

    public void buscarEmpleado(ActionEvent actionEvent) {
    }

    public void modificarInformacion(ActionEvent actionEvent) {
    }

    public void informesAeropuerto(ActionEvent actionEvent) {
    }

    @FXML
    public void cerrarSesion(ActionEvent actionEvent) {
        UserSession.leaveInstance();
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), loginController.class,applicationContext);
    }
}
