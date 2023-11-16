package uy.edu.um.proyectotic.controladores.Aerolinea;

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
import uy.edu.um.AvionesDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AvionRestService;

import java.util.List;

@Controller
@FxmlView("listaAvionesAerolinea.fxml")
public class listaAvionesAerolineaController {

    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AvionRestService avionService;

    @FXML
    private TableColumn<?, ?> columnaDistanciaDespegue;

    @FXML
    private Button botonModificarAvion;

    @FXML
    private Button botonEliminarAvion;

    @FXML
    private TableColumn<?, ?> columnaCargaAvion;

    @FXML
    private TableView<AvionesDTO> tablaAvionesAerolinea;

    @FXML
    private Button botonParaAtarasListaAvionesAerolineas;

    @FXML
    private TableColumn<?, ?> columnaMatriculaAvion;

    @FXML
    private TableColumn<?, ?> columnaModeloAvion;

    @FXML
    private TableColumn<?, ?> columnaPasajerosAvion;

    @FXML
    public void atras(ActionEvent actionEvent) {
        configuraciones.cambiarPantalla(botonParaAtarasListaAvionesAerolineas.getScene(), vistaAerolineaController.class,applicationContext);
    }
    @FXML
    public void eliminarAvion(ActionEvent actionEvent) {
        if(tablaAvionesAerolinea.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ninguna aerolinea");
        }else{
            Boolean eliminado = eliminarAvionAux(tablaAvionesAerolinea.getSelectionModel().getSelectedItem().getMatricula());
            if(eliminado){
                showAlert("Exito", "Se elimino la aerolinea");
            }else{
                showAlert("Error", "Hubo un error al eliminar la aerolinea");
            }
        }
    }

    @FXML
    public void modificarAvion(ActionEvent actionEvent) {
        if(tablaAvionesAerolinea.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ninguna aerolinea");
        }else{
            showAlert("No funciona", "No funciona");
            //configuraciones.cambiarPantalla(botonParaAtarasListaAerolineas.getScene(), modificarAerolineaController.class,applicationContext);
        }
    }

    @FXML
    public void initialize() {
        System.out.println("Cargando datos desde la base de datos");
        tablaAvionesAerolinea.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        UserSession usr=UserSession.getInstace();

        ResponseEntity<List<AvionesDTO>> avionesGet= avionService.getAviones(usr.getEmpresa());
        List<AvionesDTO> aviones = avionesGet.getBody();

        ObservableList<AvionesDTO> avionesDTOObservableList = FXCollections.observableArrayList(aviones);
        tablaAvionesAerolinea.setItems(avionesDTOObservableList);

        columnaCargaAvion.setCellValueFactory(new PropertyValueFactory("capacidadCarga"));
        columnaDistanciaDespegue.setCellValueFactory(new PropertyValueFactory("distanciaDeDespegue"));
        columnaMatriculaAvion.setCellValueFactory(new PropertyValueFactory("matricula"));
        columnaModeloAvion.setCellValueFactory(new PropertyValueFactory("modelo"));
        columnaPasajerosAvion.setCellValueFactory(new PropertyValueFactory("cantidadPasajeros"));
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private Boolean eliminarAvionAux(String id){
        return true;
    }

}
