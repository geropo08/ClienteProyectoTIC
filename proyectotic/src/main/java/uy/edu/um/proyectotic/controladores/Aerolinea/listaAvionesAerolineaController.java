package uy.edu.um.proyectotic.controladores.Aerolinea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("listaAvionesAerolinea.fxml")
public class listaAvionesAerolineaController {
    @FXML
    private TableColumn<?, ?> columnaDistanciaDespegue;

    @FXML
    private Button botonEliminarAvion;

    @FXML
    private TableColumn<?, ?> columnaCargaAvion;

    @FXML
    private TableView<?> tablaDatosAerolinea;

    @FXML
    private Button botonParaAtarasListaAerolineas;

    @FXML
    private TableColumn<?, ?> columnaMatriculaAvion;

    @FXML
    private TableColumn<?, ?> columnaModeloAvion;

    @FXML
    private TableColumn<?, ?> columnaPasajerosAvion;

    @FXML
    private Button botonEliminarAvion1;
    public void atras(ActionEvent actionEvent) {
    }

    public void eliminarAvion(ActionEvent actionEvent) {
    }

    public void modificarAvion(ActionEvent actionEvent) {
    }
}
