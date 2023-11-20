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
import uy.edu.um.AerolineasDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.servicios.AerolineaRestService;

import java.util.List;

@Controller
@FxmlView("listaAerolineas.fxml")
public class listaAerolineasController {
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AerolineaRestService aerolineasService;

    @FXML
    private Button EliminarAerolinea;

    @FXML
    private TableColumn<?, ?> columnaNombreAerolinea;

    @FXML
    private Button ModificarAerolinea1;

    @FXML
    private TableView<AerolineasDTO> tablaDatosAerolinea;

    @FXML
    private TableColumn<?, ?> columnaSede;

    @FXML
    private Button botonParaAtarasListaAerolineas;

    @FXML
    private TableColumn<?, ?> columnaCodigo;

    @FXML
    private TableColumn<?, ?> columnaContacto;
    @FXML
    public void atras(ActionEvent actionEvent) {
        configuraciones.cambiarPantalla(botonParaAtarasListaAerolineas.getScene(), vistaAdminController.class,applicationContext);
    }
    @FXML
    public void eliminarAerolinea(ActionEvent actionEvent) {
        if(tablaDatosAerolinea.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ninguna aerolinea");
        }else{
            Boolean eliminado = eliminarAerolineaAux(tablaDatosAerolinea.getSelectionModel().getSelectedItem().getId());
            if(eliminado){
                showAlert("Exito", "Se elimino la aerolinea");
            }else{
                showAlert("Error", "Hubo un error al eliminar la aerolinea");
            }
        }
    }
    @FXML
    public void modificarAerolinea(ActionEvent actionEvent) {
        if(tablaDatosAerolinea.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ninguna aerolinea");
        }else{
            showAlert("No funciona", "No funciona");
            //configuraciones.cambiarPantalla(botonParaAtarasListaAerolineas.getScene(), modificarAerolineaController.class,applicationContext);
        }
    }

    @FXML
    public void initialize() {
        System.out.println("Cargando datos desde la base de datos");
        tablaDatosAerolinea.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ResponseEntity<List<AerolineasDTO>> aerolineasGet= aerolineasService.getAerolineas();
        List<AerolineasDTO> aerolineas = aerolineasGet.getBody();

        ObservableList<AerolineasDTO> aerolineasObservableList = FXCollections.observableArrayList(aerolineas);
        tablaDatosAerolinea.setItems(aerolineasObservableList);

        columnaNombreAerolinea.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        columnaSede.setCellValueFactory(new PropertyValueFactory("sedePrincipal"));
        columnaContacto.setCellValueFactory(new PropertyValueFactory("contacto"));
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private Boolean eliminarAerolineaAux(Long id){
        return true;
    }

}
