package uy.edu.um.proyectotic.controladores.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("crearAeropuerto.fxml")
public class crearAeropuertoController {

    @FXML
    private TextField contrasenaAeropuerto;

    @FXML
    private TextField emailCrearAeropuerto;

    @FXML
    private TextField paisCrearAeropuerto;

    @FXML
    private TextField cantidadPuestosCheckInAeropuerto;

    @FXML
    private Button botonEviarCrearAeropuerto;

    @FXML
    private TextField cantidadMangasAeropuerto;

    @FXML
    private TextField codigoIataAeropuerto;

    @FXML
    private TextField cantidadPuertasAeropuerto;

    @FXML
    private Button botonAtrasCrearAeropuerto;

    @FXML
    private TextField nombreCearAeropuerto;

    public void atras(ActionEvent actionEvent) {
    }

    public void crearAeropuerto(ActionEvent actionEvent) {
    }
}
