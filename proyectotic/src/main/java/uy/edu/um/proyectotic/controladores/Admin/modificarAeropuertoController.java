package uy.edu.um.proyectotic.controladores.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;
import javafx.scene.control.*;

@Controller
@FxmlView("modificarAeropuerto.fxml")
public class modificarAeropuertoController {
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
