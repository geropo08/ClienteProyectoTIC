package uy.edu.um.proyectotic.controladores.Aerolinea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("vistaAerolinea.fxml")
public class vistaAerolineaController {
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
    }
    @FXML
    public void buscarEmpleado(ActionEvent actionEvent) {
    }
    @FXML
    public void informesEmpleado(ActionEvent actionEvent) {
    }
}
