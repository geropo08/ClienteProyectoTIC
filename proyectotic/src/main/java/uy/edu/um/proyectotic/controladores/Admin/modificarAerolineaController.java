package uy.edu.um.proyectotic.controladores.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;
import javafx.scene.control.*;

@Controller
@FxmlView("modificarAerolinea.fxml")
public class modificarAerolineaController {

    @FXML
    private TextField modificarNombreAerolinea;

    @FXML
    private TextField modificarContactoAerolinea;

    @FXML
    private TextField modificarSedePrincipalAerolinea;

    @FXML
    private Button botonAtrasCrearAerolinea;

    @FXML
    private Button enviar_aerolinea_aeropuerto;

    @FXML
    private TextField modificarCodigoAerolinea;

    @FXML
    private TextField modificarPassAerolinea;



    public void crearAereolinea(ActionEvent actionEvent) {
    }

    public void atras(ActionEvent actionEvent) {
    }
}
