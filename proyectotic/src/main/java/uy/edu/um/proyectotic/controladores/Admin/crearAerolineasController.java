package uy.edu.um.proyectotic.controladores.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("crearAerolineas.fxml")
public class crearAerolineasController {

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
    void crearAereolinea(ActionEvent event) {

    }

    @FXML
    void atras(ActionEvent event) {

    }

}
