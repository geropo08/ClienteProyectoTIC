package uy.edu.um.proyectotic.controladores.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

import java.awt.*;

@Controller
@FxmlView("modificarAerolinea.fxml")
public class modificarAerolineaController {

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

    public void crearAereolinea(ActionEvent actionEvent) {
    }

    public void atras(ActionEvent actionEvent) {
    }
}
