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
import uy.edu.um.UsuariosDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.UsuarioRestService;

import java.util.List;

@Controller
@FxmlView("listaEmpleados.fxml")
public class listaEmpleadosController {

    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    UsuarioRestService usuarioService;

    @FXML
    private Button EliminarEmpleado;

    @FXML
    private TableColumn<?, ?> columnaRoll;

    @FXML
    private Button ModificarEmpleado;

    @FXML
    private TableColumn<?, ?> columnaApellido;

    @FXML
    private TableView<UsuariosDTO> tablaEmpleados;

    @FXML
    private Button botonParaAtaras;

    @FXML
    private TableColumn<?, ?> columnaMail;

    @FXML
    private TableColumn<?, ?> columnaNombre;

    @FXML
    void atras(ActionEvent event) {
        configuraciones.cambiarPantalla(botonParaAtaras.getScene(), vistaAerolineaController.class,applicationContext);
    }

    @FXML
    void eliminarEmpleado(ActionEvent event) {
        if(tablaEmpleados.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ningun Empleado");
        }else{
            Boolean eliminado = eliminarEmpleadoAux(tablaEmpleados.getSelectionModel().getSelectedItem().getEmail());
            if(eliminado){
                showAlert("Exito", "Se elimino el Empleado");
            }else{
                showAlert("Error", "Hubo un error al eliminar el Empleado");
            }
        }
    }

    @FXML
    void modificarEmpleado(ActionEvent event) {
        if(tablaEmpleados.getSelectionModel().getSelectedItem()==null){
            showAlert("Datos Invalidos", "No se selecciono ningun Empleado");
        }else{
            showAlert("No funciona", "No funciona");
            //configuraciones.cambiarPantalla(botonParaAtarasListaAerolineas.getScene(), modificarAerolineaController.class,applicationContext);
        }
    }

    @FXML
    public void initialize() {
        System.out.println("Cargando datos desde la base de datos");
        tablaEmpleados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        UserSession usr=UserSession.getInstace();

        ResponseEntity<List<UsuariosDTO>> empleadosGet= usuarioService.getEmpleados(usr.getEmpresa());
        List<UsuariosDTO> empleados = empleadosGet.getBody();

        ObservableList<UsuariosDTO> usuarsDTOObservableList = FXCollections.observableArrayList(empleados);
        tablaEmpleados.setItems(usuarsDTOObservableList);

        columnaMail.setCellValueFactory(new PropertyValueFactory("email"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        columnaRoll.setCellValueFactory(new PropertyValueFactory("rol"));
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private Boolean eliminarEmpleadoAux(String id){
        return true;
    }
}
