package uy.edu.um.proyectotic.controladores.Aeropuerto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.PuertasPistasTransporte;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AeropuertoRestService;

@Controller
@FxmlView("AsignarPista.fxml")
public class crearPistaController {
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    AeropuertoRestService aeropuertoRestService;
    
    private int contador=2;

    @FXML
    private Button botonAtrasCrearPista;

    @FXML
    private Button botonCrearPista;

    @FXML
    private Button botonSumar;

    @FXML
    private TextField pista1;

    @FXML
    private Label pista1_lbl;

    @FXML
    private TextField pista2;

    @FXML
    private Label pista2_lbl;

    @FXML
    private TextField pista3;

    @FXML
    private Label pista3_lbl;

    @FXML
    private TextField pista4;

    @FXML
    private Label pista4_lbl;

    @FXML
    private TextField pista5;

    @FXML
    private Label pista5_lbl;

    public void initialize(){
        botonSumar.setOnAction(event ->{
            if(contador<6){
                String nombreText="#pista"+contador;
                String nombreLabel="#pista"+contador+"_lbl";
                Scene scene= botonSumar.getScene();
                TextField fieldT= (TextField) scene.lookup(nombreText);
                Label lbl=(Label) scene.lookup(nombreLabel);
                fieldT.setVisible(true);
                lbl.setVisible(true);
                contador=contador+1;
                
            } else {
                showAlert("Limite Excedido", "Solo puede crear 5 pistas a la vez");
            }
        });
    }

    @FXML
    void atras(ActionEvent event) {
        contador=2;
        configuraciones.cambiarPantalla(botonAtrasCrearPista.getScene(), vistaAeropuertoController.class,applicationContext);
    }

    @FXML
    void crear(ActionEvent event) {
        ResponseEntity<PuertasPistasTransporte> pResponseEntity=null;
        UserSession usr=UserSession.getInstace();
        List<String> listaPistas=new ArrayList<>();
        TextField fieldT;
        String nombreText;
        Scene scene= botonSumar.getScene();
        for (int i=1; i<6; i++){      
            nombreText="#pista"+i;
            fieldT= (TextField) scene.lookup(nombreText); 
            if(fieldT.getText()!=null && !fieldT.getText().equals("")){
                listaPistas.add(fieldT.getText());
                
            }
        }

        try{
            pResponseEntity=aeropuertoRestService.crearPistas(usr.getEmpresa(),listaPistas);
            if(pResponseEntity.getStatusCode()==HttpStatus.OK){
                showAlert("Exito en la creacion!", "Se han creado las pistas.");
                
            }
        } catch (Exception e){
            
            showAlert("Datos Invalidos", "Error al crear pistas");
        }

    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
