package uy.edu.um.proyectotic.controladores.Aeropuerto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.proyectotic.controladores.crearEmpleadoController;
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

    //==================================================AEROLINEA======================================================//

    @FXML
    public void asociarAerolinea(ActionEvent actionEvent) {
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), asociarAerolineaController.class,applicationContext);
    }

    @FXML
    public void buscarAerolinea(ActionEvent actionEvent) {
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), listaAerolineasAeropuertoController.class,applicationContext);
    }

    @FXML
    public void informesAerolineas(ActionEvent actionEvent) {
    }
    //======================================================VUELO======================================================//
    @FXML
    public void aceptarVuelos(ActionEvent actionEvent) {
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), confirmarVueloAerolineaController.class,applicationContext);
    }

    @FXML
    public void buscarVuelos(ActionEvent actionEvent) {
    }

    @FXML
    public void informesVuelos(ActionEvent actionEvent) {
    }

    //==================================================TIENDAS========================================================//
    @FXML
    public void crearTiendas(ActionEvent actionEvent) {
        
    }

    @FXML
    public void buscarTiendas(ActionEvent actionEvent) {
    }

    @FXML
    public void informesTiendas(ActionEvent actionEvent) {
    }
    //==================================================EMPLEADO=======================================================//
    @FXML
    public void crearEmpleado(ActionEvent actionEvent) {
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), crearEmpleadoController.class,applicationContext);

    }

    @FXML
    public void buscarEmpleado(ActionEvent actionEvent) {

    }

    @FXML
    public void modificarInformacion(ActionEvent actionEvent) {
    }
    //==================================================AEROPUERTO=====================================================//
    @FXML
    public void informesAeropuerto(ActionEvent actionEvent) {
    }
    @FXML
    public void crearPuerta(ActionEvent actionEvent){
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), crearPuertaController.class,applicationContext);
    }
    @FXML
    public void crearPista(ActionEvent actionEvent){
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), crearPistaController.class,applicationContext);

    }

    @FXML
    public void cerrarSesion(ActionEvent actionEvent) {
        UserSession.leaveInstance();
        conf.cambiarPantalla(BotonCerrarSesionAerolinea.getScene(), loginController.class,applicationContext);
    }
}
