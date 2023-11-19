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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import uy.edu.um.AceptacionVuelosT;
import uy.edu.um.VuelosDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.Datos;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AeropuertoRestService;
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
    @Autowired
    AeropuertoRestService aeropuertoRestService;

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
        TablaConfirmarVuelo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        UserSession usr=UserSession.getInstace();

        ResponseEntity<List<VuelosDTO>> vuelosGet= vueloService.getVuelos(usr.getEmpresa());
        List<VuelosDTO> vuelos = vuelosGet.getBody();

        ObservableList<VuelosDTO> vuelosDTOObservableList = FXCollections.observableArrayList(vuelos);
        TablaConfirmarVuelo.setItems(vuelosDTOObservableList);

        columnaAeropuertoLlegada.setCellValueFactory(new PropertyValueFactory("aeropuertoLlegada"));
        columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigoVuelo"));
        columnaAerppuertoSalida.setCellValueFactory(new PropertyValueFactory("aeropuertoSalida"));
        columnaFechaLlegada.setCellValueFactory(new PropertyValueFactory("fechaLlegada"));
        columnaFechaSalida.setCellValueFactory(new PropertyValueFactory("fechaSalida"));
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
                VuelosDTO vueloDTO = TablaConfirmarVuelo.getSelectionModel().getSelectedItems().get(0);
                Datos vuelo = Datos.getInstace();
                vuelo.setVuelo(vueloDTO);
                configuraciones.cambiarPantalla(botonAtrasConfirmarVuelo.getScene(), AsociarPistasPuertasController.class,applicationContext);
            } else {
                showAlert("Error", "Debe seleccionar un vuelo");
            }
        } else if (source == botonNegarVuelo) {
            if (!TablaConfirmarVuelo.getSelectionModel().isEmpty()) {
                VuelosDTO vueloDTO = TablaConfirmarVuelo.getSelectionModel().getSelectedItems().get(0);
                ResponseEntity<AceptacionVuelosT> aceptacionVuelosResponseEntity=null;
                UserSession usr=UserSession.getInstace();
                try{
                    aceptacionVuelosResponseEntity=aeropuertoRestService.denegarVuelo(vueloDTO.getCodigoVuelo(), usr.getEmpresa());
                    if(aceptacionVuelosResponseEntity.getStatusCode()==HttpStatus.OK){
                        showAlert("Eliminado", "El vuelo ha sido denegado");
                        configuraciones.cambiarPantalla(botonAtrasConfirmarVuelo.getScene(), confirmarVueloAerolineaController.class,applicationContext);

                    }

                } catch (Exception e){
                    showAlert("Error", "No se ha podido denegar el vuelo");
                }
                
            } else {
                showAlert("Error", "Debe seleccionar un vuelo");
            }
        }
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private Boolean eliminarVueloAux(String codigoVuelo) {
        return true;
    }
}

