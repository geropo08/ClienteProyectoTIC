package uy.edu.um.proyectotic.controladores.Aerolinea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

import java.awt.*;

@Controller
@FxmlView("vistaAerolinea.fxml")
public class modificarAvionController {
    @FXML
    private TextField DistanciaCrearAvionAerolinea;

    @FXML
    private Button botonAtrasModificarAvion;

    @FXML
    private TextField ModeloCrearAvionAerolinea;

    @FXML
    private TextField CapacidadCargaCrearAvionAerolinea1;

    @FXML
    private TextField CapacidadPersonasCrearAvionAerolinea;

    @FXML
    private Button botonGuardarAvionAerolinea;

    @FXML
    private TextField MatriculaCrearAvionAerolinea;
    public void atras(ActionEvent actionEvent) {
    }

    public void guardarAvion(ActionEvent actionEvent) {
    }
}
