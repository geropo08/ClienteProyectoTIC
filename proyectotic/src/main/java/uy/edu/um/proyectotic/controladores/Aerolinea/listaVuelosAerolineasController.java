package uy.edu.um.proyectotic.controladores.Aerolinea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import uy.edu.um.VuelosDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AvionRestService;
import uy.edu.um.proyectotic.servicios.VueloRestService;

import java.util.List;

@Controller
@FxmlView ("listaVuelosAerolinea.fxml")
public class listaVuelosAerolineasController {

    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AvionRestService avionService;
    @Autowired
    VueloRestService vueloService;

    @FXML
    private TableView<VuelosDTO> TablaVuelo;

    @FXML
    private TableColumn<?, ?> columnaFechaSalida;

    @FXML
    private Button botonAtrasVuelo;

    @FXML
    private TableColumn<?, ?> columnaFechaLlegada;

    @FXML
    private TableColumn<?, ?> columnaMatricula;

    @FXML
    private TableColumn<?, ?> columnaAerppuertoSalida;

    @FXML
    private TableColumn<?, ?> columnaEstadoVuelo;

    @FXML
    private TableColumn<?, ?> columnaCodigo;

    @FXML
    private TableColumn<?, ?> columnaAeropuertoLlegada;

    @FXML
    private TableColumn<?, ?> columnaPiloto;

    @FXML
    private TableColumn<VuelosDTO, Boolean>  columnaEstadoSalida;

    @FXML
    private TableColumn<VuelosDTO, Boolean>  columnaEstadoLlegada;

    @FXML
    void atras(ActionEvent event) {
        configuraciones.cambiarPantalla(botonAtrasVuelo.getScene(), vistaAerolineaController.class,applicationContext);
    }

    @FXML
    public void initialize() {
        TablaVuelo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        UserSession usr=UserSession.getInstace();

        ResponseEntity<List<VuelosDTO>> vuelosGet= vueloService.getVuelosAerolinea(usr.getEmpresa());
        List<VuelosDTO> vuelos = vuelosGet.getBody();

        ObservableList<VuelosDTO> vuelosDTOObservableList = FXCollections.observableArrayList(vuelos);
        TablaVuelo.setItems(vuelosDTOObservableList);

        columnaAeropuertoLlegada.setCellValueFactory(new PropertyValueFactory("aeropuertoLlegada"));
        columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigoVuelo"));
        columnaAerppuertoSalida.setCellValueFactory(new PropertyValueFactory("aeropuertoSalida"));
        columnaFechaLlegada.setCellValueFactory(new PropertyValueFactory("fechaLlegada"));

        columnaPiloto.setCellValueFactory(new PropertyValueFactory("licenciaPiloto"));
        columnaEstadoVuelo.setCellValueFactory(new PropertyValueFactory("estadoVuelo"));
        columnaMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        columnaFechaSalida.setCellValueFactory(new PropertyValueFactory("fechaSalida"));
        columnaEstadoLlegada.setCellValueFactory(new PropertyValueFactory("estadoAceptacionLlegada"));
        columnaEstadoSalida.setCellValueFactory(new PropertyValueFactory("estadoAceptacionSalida"));
        
        columnaEstadoSalida.setCellFactory(tc -> new TextFieldTableCell<>(){
            @Override
            public void updateItem(Boolean item,boolean empty){
                super.updateItem(item, empty);
                if(item == null){
                    setText(null);
                } else if(item==true){
                    setText("Confirmado");
                } else if(item==false){
                    setText("Denegado");
                }
            }
                
        });
        columnaEstadoLlegada.setCellFactory(tc -> new TextFieldTableCell<>(){
            @Override
            public void updateItem(Boolean item,boolean empty){
                super.updateItem(item, empty);
                if(empty || item ==null){
                    setText(null);
                } else if(item==true){
                    setText("Confirmado");
                } else if(item==false){
                    setText("Denegado");
                }
            }
                
        });

    }

}
