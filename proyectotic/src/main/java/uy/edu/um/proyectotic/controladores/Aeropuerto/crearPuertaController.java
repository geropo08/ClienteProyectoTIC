package uy.edu.um.proyectotic.controladores.Aeropuerto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.PuertasPistasTransporte;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.AeropuertoRestService;
import java.util.List;
import java.util.ArrayList;

@Controller
@FxmlView("AsignarPuerta.fxml")
public class crearPuertaController {
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    AeropuertoRestService aeropuertoRestService;
    private int contador=2;

    @FXML
    private Button botonAtrasCrearPuerta;

    @FXML
    private Button botonCrearPuerta;

    @FXML
    private Button botonSumar;

    @FXML
    private TextField puerta1;

    @FXML
    private Label puerta1_lbl;

    @FXML
    private TextField puerta2;

    @FXML
    private Label puerta2_lbl;

    @FXML
    private TextField puerta3;

    @FXML
    private Label puerta3_lbl;

    @FXML
    private TextField puerta4;

    @FXML
    private Label puerta4_lbl;

    @FXML
    private TextField puerta5;

    @FXML
    private Label puerta5_lbl;
   
    public void initialize(){
        botonSumar.setOnAction(event ->{
            if(contador<6){
                String nombreText="#puerta"+contador;
                String nombreLabel="#puerta"+contador+"_lbl";
                Scene scene= botonSumar.getScene();
                TextField fieldT= (TextField) scene.lookup(nombreText);
                Label lbl=(Label) scene.lookup(nombreLabel);
                fieldT.setVisible(true);
                lbl.setVisible(true);
                contador=contador+1;
                
            } else {
                showAlert("Limite Excedido", "Solo puede crear 5 puertas a la vez");
            }
        });


    }
    @FXML
    void atras(ActionEvent event) {
        contador=2;
        configuraciones.cambiarPantalla(botonAtrasCrearPuerta.getScene(), vistaAeropuertoController.class,applicationContext);
    }
    @FXML
    void crear(ActionEvent event){
        ResponseEntity<PuertasPistasTransporte> pResponseEntity=null;
        UserSession usr=UserSession.getInstace();
        List<String> listaPuertas=new ArrayList<>();
        TextField fieldT;
        String nombreText;
        Scene scene= botonSumar.getScene();
        for (int i=1; i<6; i++){      
            nombreText="#puerta"+i;
            fieldT= (TextField) scene.lookup(nombreText); 
            if(fieldT.getText()!=null && !fieldT.getText().equals("")){
                listaPuertas.add(fieldT.getText());
                
            }
        }

        try{
            pResponseEntity=aeropuertoRestService.crearPuertas(usr.getEmpresa(),listaPuertas);
            if(pResponseEntity.getStatusCode()==HttpStatus.OK){
                showAlert("Exito en la creacion!", "Se han creado las puertas.");
            }
        } catch (Exception e){
            
            showAlert("Datos Invalidos", "Error al crear puertas");
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
