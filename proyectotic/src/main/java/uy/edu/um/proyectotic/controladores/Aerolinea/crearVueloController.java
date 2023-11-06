package uy.edu.um.proyectotic.controladores.Aerolinea;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.AvionesDTO;
import uy.edu.um.VuelosDTO;
import uy.edu.um.proyectotic.controladores.Admin.vistaAdminController;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AerolineaRestService;
import uy.edu.um.proyectotic.servicios.VueloRestService;

@Controller
@FxmlView("CreacionVuelo.fxml")
public class crearVueloController {
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    AerolineaRestService aerolineaRestService;
    @Autowired
    VueloRestService vueloRestService;

    @FXML
    private Button atrasCreacionVuelo;

    @FXML
    private Button botonCrearVuelo;

    @FXML
    private ComboBox<String> comboBoxIataLlegada;

    @FXML
    private ComboBox<String> comboBoxIataSalida;

    @FXML
    private ComboBox<String> comboBoxPilotos;

    @FXML
    private DatePicker fechaLlegadaCreacionVuelo;

    @FXML
    private DatePicker fechaSalidaCreacionVuelo;

    @FXML
    private TextField horaLlegadaCrecionVuelo;

    @FXML
    private TextField horaSalidaCreacionVuelo;

    @FXML
    private TextField matriculaAvionCreacionVuelo;

    @FXML
    private TextField numeroVuelo;

    public void initialize(){
        UserSession usr=UserSession.getInstace();
        ResponseEntity<List<String>> asociacionTransporte=null;
        try{
            asociacionTransporte=aerolineaRestService.getAeropuertosAsociados(usr.getEmpresa());
        } catch(Exception e){
            showAlert("Error", "Error al buscar aeropuertos");
        }
        comboBoxIataSalida.getItems().removeAll(comboBoxIataSalida.getItems());
        comboBoxIataSalida.getItems().setAll(asociacionTransporte.getBody());
        comboBoxIataLlegada.getItems().removeAll(comboBoxIataLlegada.getItems());
        comboBoxIataLlegada.getItems().setAll(asociacionTransporte.getBody());
        


    }

    @FXML
    void atras(ActionEvent event) {
        configuraciones.cambiarPantalla(atrasCreacionVuelo.getScene(), vistaAerolineaController.class,applicationContext);
    }
    
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void crearVuelo(ActionEvent event) {
        ResponseEntity<VuelosDTO> vueloResponseEntity=null;
        UserSession usr=UserSession.getInstace();

        try{
            vueloResponseEntity=vueloRestService.crearVuelo(comboBoxIataLlegada.getValue(), comboBoxIataSalida.getValue(), usr.getEmpresa(), fechaSalidaCreacionVuelo.getValue(), horaSalidaCreacionVuelo.getText(), fechaLlegadaCreacionVuelo.getValue(), horaLlegadaCrecionVuelo.getText(), numeroVuelo.getText(), matriculaAvionCreacionVuelo.getText());
            if(vueloResponseEntity.getStatusCode()==HttpStatus.OK){
                showAlert("Exito en la creacion!", "Se ha creado el vuelo "+numeroVuelo.getText());
            }
        } catch (Exception e){
            
            showAlert("Datos Invalidos", "Error al crear el vuelo");
        }
        

    }

}
