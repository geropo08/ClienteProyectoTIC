package uy.edu.um.proyectotic.controladores.Aeropuerto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("vistaAerolineasAeropuerto.fxml")
public class listaAerolineasAeropuertoController {
    @FXML
    private TableColumn<?, ?> columnaNombreAerolinea;

    @FXML
    private TableView<?> tablaDatosAerolinea;

    @FXML
    private Button botonParaAtarasListaAerolineas;

    @FXML
    private TableColumn<?, ?> columnaContactoAerolinea;

    @FXML
    public void atras(ActionEvent actionEvent) {
    }
}
