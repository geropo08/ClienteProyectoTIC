package uy.edu.um.proyectotic.controladores.Admin;

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
import uy.edu.um.AeropuertosDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.servicios.AeropuertoRestService;

import java.util.List;

@Controller
@FxmlView("listaAeropuertos.fxml")
public class listaAeropuertosController {
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AeropuertoRestService aeropuertoService;
    @FXML
    private Button botonModificarAeropuerto;

    @FXML
    private Button EliminarAerolinea;

    @FXML
    private TableColumn<?, ?> columnaNombreAerolinea;

    @FXML
    private TableView<AeropuertosDTO> tablaDatosAeropuertos;

    @FXML
    private Button botonParaAtarasListaAerolineas;

    @FXML
    private TableColumn<?, ?> columnaIATA;

    @FXML
    private TableColumn<?, ?> columnaPais;

    @FXML
    public void atras(ActionEvent actionEvent) {
        configuraciones.cambiarPantalla(botonParaAtarasListaAerolineas.getScene(), vistaAdminController.class,applicationContext);
    }
    @FXML
    public void eliminarAerolinea(ActionEvent actionEvent) {
        tablaDatosAeropuertos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        if(tablaDatosAeropuertos.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ningun aeropuerto");
        }else{
            Boolean eliminado = eliminarAeropuertoAux(tablaDatosAeropuertos.getSelectionModel().getSelectedItem().getCodigoIATA());
            if(eliminado){
                showAlert("Exito", "Se elimino la aeropuerto");
            }else{
                showAlert("Error", "Hubo un error al eliminar el aeropuerto");
            }
        }
    }
    @FXML
    public void modificarAerolinea(ActionEvent actionEvent) {
        tablaDatosAeropuertos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        if(tablaDatosAeropuertos.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ningun aeropuerto");
        }else{
            configuraciones.cambiarPantalla(botonModificarAeropuerto.getScene(), modificarAeropuertoController.class,applicationContext);
        }
    }

    @FXML
    public void initialize() {
        System.out.println("Cargando datos desde la base de datos");

        ResponseEntity<List<AeropuertosDTO>> aeropuertosGet= aeropuertoService.getAeropuertos();
        List<AeropuertosDTO> aeropuertos = aeropuertosGet.getBody();

        ObservableList<AeropuertosDTO> aeropuertosObservableList = FXCollections.observableArrayList(aeropuertos);
        tablaDatosAeropuertos.setItems(aeropuertosObservableList);

        columnaNombreAerolinea.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaIATA.setCellValueFactory(new PropertyValueFactory("codigoIATA"));
        columnaPais.setCellValueFactory(new PropertyValueFactory("pais"));
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private Boolean eliminarAeropuertoAux(String codigoIATA){
        return true;
    }
}
