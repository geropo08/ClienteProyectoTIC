package uy.edu.um.proyectotic.controladores.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("listaAerolineas.fxml")
public class listaAerolineasController {
    @FXML
    private Button EliminarAerolinea;

    @FXML
    private TableColumn<?, ?> columnaNombreAerolinea;

    @FXML
    private Button ModificarAerolinea1;

    @FXML
    private TableView<?> tablaDatosAerolinea;

    @FXML
    private Button botonParaAtarasListaAerolineas;

    @FXML
    private TableColumn<?, ?> columnaContactoAerolinea;
    public void atras(ActionEvent actionEvent) {
    }

    public void eliminarAerolinea(ActionEvent actionEvent) {
    }

    public void modificarAerolinea(ActionEvent actionEvent) {
    }
}
