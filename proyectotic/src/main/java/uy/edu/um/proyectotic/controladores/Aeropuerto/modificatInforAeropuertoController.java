package uy.edu.um.proyectotic.controladores.Aeropuerto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

import java.awt.*;

@Controller
@FxmlView("vistaAeropuerto.fxml")
public class modificatInforAeropuertoController {
    @FXML
    private TextField mangasAeropuerto;

    @FXML
    private Button botonModificarAeropuerto;

    @FXML
    private TextField cantidadPuestosCheckIn;

    @FXML
    private Button botonAtrasModificarAeropuerto;

    @FXML
    private TextField estacionamientoAeronaves;

    @FXML
    private TextField capacidadMaximaAeronaves;

    @FXML
    private TextField cantidadPuertas;

    @FXML
    private TextField tamanoPista;
    @FXML
    public void atras(ActionEvent actionEvent) {
    }
    @FXML
    public void guardarAeropuerto(ActionEvent actionEvent) {
    }
}
