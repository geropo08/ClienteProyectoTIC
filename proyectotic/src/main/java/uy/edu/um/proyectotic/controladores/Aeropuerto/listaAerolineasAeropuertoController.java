package uy.edu.um.proyectotic.controladores.Aeropuerto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import uy.edu.um.AerolineasDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AerolineaRestService;

import java.util.List;

@Controller
@FxmlView("vistaAerolineasAeropuerto.fxml")
public class listaAerolineasAeropuertoController {
    @Autowired
    AerolineaRestService aerolineasService;
    @Autowired
    Configuraciones conf;
    @Autowired
    ConfigurableApplicationContext applicationContext;

    @FXML
    private TableColumn<?, ?> columnaNombreAerolinea;

    @FXML
    private TableView<AerolineasDTO> tablaDatosAerolinea;

    @FXML
    private Button botonParaAtarasListaAerolineas;

    @FXML
    private TableColumn<?, ?> columnaContactoAerolinea;

    @FXML
    public void atras(ActionEvent actionEvent) {
        conf.cambiarPantalla(botonParaAtarasListaAerolineas.getScene(), vistaAeropuertoController.class,applicationContext);
    }
    @FXML
    public void initialize() {
        System.out.println("Cargando datos desde la base de datos");
        tablaDatosAerolinea.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        UserSession usr=UserSession.getInstace();

        ResponseEntity<List<String>> aerolineasGet= aerolineasService.getAerolineasAeropuerto(usr.getEmpresa());
        List<String> aerolineas = aerolineasGet.getBody();

        ObservableList<AerolineasDTO> aerolineasObservableList = FXCollections.observableArrayList();
        tablaDatosAerolinea.setItems(aerolineasObservableList);

        columnaNombreAerolinea.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaContactoAerolinea.setCellValueFactory(new PropertyValueFactory("contacto"));
    }
}
