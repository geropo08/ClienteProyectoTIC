package uy.edu.um.proyectotic.controladores.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

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
import uy.edu.um.VuelosDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AerolineaRestService;
import uy.edu.um.proyectotic.servicios.VueloRestService;

import java.util.List;

@Controller
@FxmlView("listaVuelosComprados.fxml")
public class vuelosCompradosController {
    @Autowired
    Configuraciones conf;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AerolineaRestService aerolineaRestService;

    @FXML
    private TableView<VuelosDTO> TablaVuelo;

    @FXML
    private Button botonAtrasVuelo;

    @FXML
    private TableColumn<?, ?> columnaAeropuertoLlegada;

    @FXML
    private TableColumn<?, ?> columnaAerppuertoSalida;

    @FXML
    private TableColumn<?, ?> columnaCodigo;

    @FXML
    private TableColumn<?, ?> columnaEstadoVuelo;

    @FXML
    private TableColumn<?, ?> columnaFechaLlegada;

    @FXML
    private TableColumn<?, ?> columnaFechaSalida;

    @FXML
    public void initialize() {
        TablaVuelo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        UserSession usr=UserSession.getInstace();

        ResponseEntity<List<VuelosDTO>> vuelosComprados= aerolineaRestService.vuelosComprados(usr.getPasaporte());

        ObservableList<VuelosDTO> vuelosDTOObservableList = FXCollections.observableArrayList(vuelosComprados.getBody());
        TablaVuelo.setItems(vuelosDTOObservableList);

        columnaAeropuertoLlegada.setCellValueFactory(new PropertyValueFactory("aeropuertoLlegada"));
        columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigoVuelo"));
        columnaAerppuertoSalida.setCellValueFactory(new PropertyValueFactory("aeropuertoSalida"));
        columnaFechaLlegada.setCellValueFactory(new PropertyValueFactory("fechaLlegada"));

        columnaEstadoVuelo.setCellValueFactory(new PropertyValueFactory("estadoVuelo"));
        columnaFechaSalida.setCellValueFactory(new PropertyValueFactory("fechaSalida"));

    }

    @FXML
    void atras(ActionEvent event) {
        conf.cambiarPantalla(botonAtrasVuelo.getScene(), vistaClienteController.class,applicationContext);

    }

}
