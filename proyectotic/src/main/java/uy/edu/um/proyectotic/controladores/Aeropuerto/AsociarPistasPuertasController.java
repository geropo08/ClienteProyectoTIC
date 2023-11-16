package uy.edu.um.proyectotic.controladores.Aeropuerto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.Datos;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AeropuertoRestService;

@Controller
@FxmlView("asignarPuertasPistas.fxml")
public class AsociarPistasPuertasController {
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    AeropuertoRestService aeropuertoRestService;

    @FXML
    private Button botonAsignarPuertas;

    @FXML
    private ComboBox<String> comboBoxPistaAsignada;

    @FXML
    private  ComboBox<String> comboBoxPuertaAsignada;

    public void initialize(){
        Datos datos= Datos.getInstace();
        UserSession usr= UserSession.getInstace();
        ResponseEntity<List> listaPuerta=null;
        ResponseEntity<List> listaPistas=null;
        LocalDate fecha=null;
        String hora="";
        if(usr.getEmpresa().equals(datos.getVuelo().getAeropuertoLlegada())){
            fecha=datos.getVuelo().getFechaLlegada();
            hora=datos.getVuelo().getHoraLlegada();
        } else {
            fecha=datos.getVuelo().getFechaSalida();
            hora=datos.getVuelo().getHoraSalida();
        }

        try{
            listaPuerta=aeropuertoRestService.disponibilidadPuertas(usr.getEmpresa(), hora,fecha);
            listaPistas=aeropuertoRestService.disponibilidadPistas(usr.getEmpresa(), hora,fecha);
        } catch(Exception e){
            showAlert("Error", "Error al buscar pistas/puertas");
        }
        comboBoxPuertaAsignada.getItems().removeAll(comboBoxPuertaAsignada.getItems());
        comboBoxPuertaAsignada.getItems().setAll(listaPuerta.getBody());
        comboBoxPistaAsignada.getItems().removeAll(comboBoxPistaAsignada.getItems());
        comboBoxPistaAsignada.getItems().setAll(listaPistas.getBody());


    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


    
}
