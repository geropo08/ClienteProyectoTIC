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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import uy.edu.um.VuelosDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.VueloRestService;

import java.util.List;

@Controller
@FxmlView ("confirmarVueloAerolinea.fxml")
public class confirmarVueloAerolineaController {

    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    VueloRestService vueloService;

    @FXML
    private TableView<VuelosDTO> TablaConfirmarVuelo;

    @FXML
    private TableColumn<?, ?> columnaFechaSalida;

    @FXML
    private Button botonAtrasConfirmarVuelo;

    @FXML
    private Button botonConfirmarVuelo;

    @FXML
    private TableColumn<?, ?> columnaFechaLlegada;

    @FXML
    private TableColumn<?, ?> columnaAerppuertoSalida;

    @FXML
    private Button botonNegarVuelo;

    @FXML
    private TableColumn<?, ?> columnaCodigo;

    @FXML
    private TableColumn<?, ?> columnaAeropuertoLlegada;

    @FXML
    public void initialize() {
        System.out.println("Cargando datos desde la base de datos");
        TablaConfirmarVuelo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        UserSession usr=UserSession.getInstace();
        System.out.println("asdasd");

        ResponseEntity<List<VuelosDTO>> vuelosGet= vueloService.getVuelos(usr.getEmpresa());
        List<VuelosDTO> vuelos = vuelosGet.getBody();
        System.out.println(vuelos);
        System.out.println("Asdasd");

        ObservableList<VuelosDTO> vuelosDTOObservableList = FXCollections.observableArrayList(vuelos);
        TablaConfirmarVuelo.setItems(vuelosDTOObservableList);
        System.out.println("qweqwe");

        columnaAeropuertoLlegada.setCellValueFactory(new PropertyValueFactory("aeropuertoLlegada"));
        columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigoVuelo"));
        columnaAerppuertoSalida.setCellValueFactory(new PropertyValueFactory("aeropuertoSalida"));
        columnaFechaLlegada.setCellValueFactory(new PropertyValueFactory("fechaLlegada"));
        columnaFechaSalida.setCellValueFactory(new PropertyValueFactory("fechaSalida"));

        System.out.println("qwe");
    }

    @FXML
    public void atras(ActionEvent actionEvent) {
        configuraciones.cambiarPantalla(botonAtrasConfirmarVuelo.getScene(), vistaAeropuertoController.class,applicationContext);
    }

    @FXML
    public void confirmarVuelo(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == botonConfirmarVuelo) {
            if (!TablaConfirmarVuelo.getSelectionModel().isEmpty()) {
                VuelosDTO codigoVuelo = TablaConfirmarVuelo.getSelectionModel().getSelectedItems().get(0);
                System.out.println(codigoVuelo);
            } else {
                // error
            }
        } else if (source == botonNegarVuelo) {
            if (!TablaConfirmarVuelo.getSelectionModel().isEmpty()) {

            } else {
                // error
            }
        }
    }
}

