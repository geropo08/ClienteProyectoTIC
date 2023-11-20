package uy.edu.um.proyectotic.controladores.Aeropuerto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
@FxmlView("listaVuelos.fxml")
public class buscarVueloAerolineaController {
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    VueloRestService vueloService;

    @FXML
    private TableColumn<?, ?> columnaFechaSalida;

    @FXML
    private Button botonModificarVuelo;

    @FXML
    private TableColumn<?, ?> columnaFechaLlegada;

    @FXML
    private Button botonEliminarVuelo;

    @FXML
    private TableColumn<?, ?> columnaEstadoSalida;

    @FXML
    private TableColumn<?, ?> columnaAerppuertoSalida;

    @FXML
    private TableColumn<?, ?> columnaEstadoVuelo;

    @FXML
    private TableView<VuelosDTO> TablaVuelos;

    @FXML
    private TableColumn<?, ?> columnaCodigo;

    @FXML
    private Button botonAtraslistaVuelo;

    @FXML
    private TableColumn<?, ?> columnaEstadoLlegada;

    @FXML
    private TableColumn<?, ?> columnaAeropuertoLlegada;


    @FXML
    void atras(ActionEvent event) {
        configuraciones.cambiarPantalla(botonAtraslistaVuelo.getScene(), vistaAeropuertoController.class,applicationContext);
    }

    @FXML
    void modificarVuelo(ActionEvent event) {
        if(TablaVuelos.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ningun Empleado");
        }else{
            //modiifcar
        }
    }

    @FXML
    void eliminarVuelo(ActionEvent event) {
        if(TablaVuelos.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ningun Empleado");
        }else{
            Boolean eliminado = eliminarVuelooAux(TablaVuelos.getSelectionModel().getSelectedItem().getCodigoVuelo());
            if(eliminado){
                showAlert("Exito", "Se elimino el Empleado");
            }else{
                showAlert("Error", "Hubo un error al eliminar el Empleado");
            }
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
        columnaEstadoLlegada.setCellValueFactory(new PropertyValueFactory("estadoAceptacionLlegada"));
        columnaEstadoSalida.setCellValueFactory(new PropertyValueFactory("estadoAceptacionSalida"));

    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private Boolean eliminarVuelooAux(String id){
        return true;
    }

}
