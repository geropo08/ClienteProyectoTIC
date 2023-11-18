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
@FxmlView("seleccionarVuelo.fxml")
public class seleccionarVueloController {
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    VueloRestService vueloService;

    @FXML
    private TableColumn<?, ?> columnaFechaSalida;

    @FXML
    private TableColumn<?, ?> columnaFechaLlegada;

    @FXML
    private TableColumn<?, ?> columnaAerppuertoSalida;

    @FXML
    private TableColumn<?, ?> columnaEstadoVuelo;

    @FXML
    private TableView<VuelosDTO> TablaVuelos;

    @FXML
    private TableColumn<?, ?> columnaCodigo;

    @FXML
    private Button botonAtrasSeleccionaraVuelo;

    @FXML
    private TableColumn<?, ?> columnaAeropuertoLlegada;

    @FXML
    private Button botonSeleccionar;

    @FXML
    void atras(ActionEvent event) {
        configuraciones.cambiarPantalla(botonAtrasSeleccionaraVuelo.getScene(), vistaAeropuertoController.class,applicationContext);
    }

    @FXML
    void seleccionarVuelo(ActionEvent event) {
        if(TablaVuelos.getSelectionModel().getSelectedItem()==null){
            //showAlert("Datos Invalidos", "No se selecciono ningun Empleado");
        }else{
           // mandar a la otra pantalla
        }

    }
    @FXML
    public void initialize() {
        TablaVuelos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        UserSession usr=UserSession.getInstace();

        ResponseEntity<List<VuelosDTO>> vuelosGet= vueloService.getAllVuelosAeropuerto(usr.getEmpresa());
        List<VuelosDTO> vuelos = vuelosGet.getBody();

        ObservableList<VuelosDTO> vuelosDTOObservableList = FXCollections.observableArrayList(vuelos);
        TablaVuelos.setItems(vuelosDTOObservableList);

        columnaAeropuertoLlegada.setCellValueFactory(new PropertyValueFactory("aeropuertoLlegada"));
        columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigoVuelo"));
        columnaAerppuertoSalida.setCellValueFactory(new PropertyValueFactory("aeropuertoSalida"));
        columnaFechaLlegada.setCellValueFactory(new PropertyValueFactory("fechaLlegada"));
        columnaFechaSalida.setCellValueFactory(new PropertyValueFactory("fechaSalida"));
        columnaEstadoVuelo.setCellValueFactory(new PropertyValueFactory("estadoVuelo"));

    }

    /*private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }*/


}
