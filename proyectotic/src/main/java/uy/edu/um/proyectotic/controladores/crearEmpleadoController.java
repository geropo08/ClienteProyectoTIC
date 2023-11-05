package uy.edu.um.proyectotic.controladores;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

import uy.edu.um.UsuarioTransporte;
import uy.edu.um.proyectotic.controladores.Admin.vistaAdminController;
import uy.edu.um.proyectotic.controladores.Aerolinea.vistaAerolineaController;
import uy.edu.um.proyectotic.controladores.Aeropuerto.vistaAeropuertoController;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.UsuarioRestService;

@Controller
@FxmlView("CrearEmpleado.fxml")
public class crearEmpleadoController {
    @Autowired
    Configuraciones configuraciones;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    UsuarioRestService usuarioRestService;

    @FXML
    private TextField licenciaPiloto;

    @FXML
    private ComboBox<String> rolesEmpleado;

    @FXML
    private TextField apellidoCrearEmpleado;

    @FXML
    private Button botonAtrasCrearEmpleado;

    @FXML
    private Button botonCrearEmpleado;

    @FXML
    private TextField contrasenaCrearEmpleado;

    @FXML
    private TextField emailCrearEmpleado;

    @FXML
    private TextField nombreCearEmpleado;


    @FXML
    private Label licenciaPilotoLabel;

    
    public void initialize(){      
        UserSession loggedInUser = UserSession.getInstace();
        licenciaPiloto.setVisible(false);
        licenciaPilotoLabel.setVisible(false);
        if(loggedInUser!=null){
            if(loggedInUser.getPermiso()==1){
                rolesEmpleado.getItems().removeAll(rolesEmpleado.getItems());
                rolesEmpleado.setValue("Seleccione");
                rolesEmpleado.getItems().setAll("Administrador","Check-In","Valijero","Gerente Vuelos","Transportista");
            } else if(loggedInUser.getPermiso()==2){
                rolesEmpleado.getItems().removeAll(rolesEmpleado.getItems());
                rolesEmpleado.setValue("Seleccione");
                rolesEmpleado.getItems().setAll("Administrador","Piloto");
                rolesEmpleado.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                   if (newValue=="Piloto"){
                    licenciaPiloto.setVisible(true);
                    licenciaPilotoLabel.setVisible(true);
                   } else{
                    licenciaPiloto.setVisible(false);
                    licenciaPilotoLabel.setVisible(false);
                    
                   }
                }); 
            } else if(loggedInUser.getPermiso()==0){
                rolesEmpleado.getItems().removeAll(rolesEmpleado.getItems());
                rolesEmpleado.setValue("Seleccione");
                rolesEmpleado.getItems().setAll("Administrador");
            }
        } else {
            showAlert("Error en la sesion", "Error al iniciar sesion, vuelva a hacerlo.");
        } 
    }

    @FXML
    public void crearEmpleado(ActionEvent event){
        ResponseEntity<UsuarioTransporte> usuarioResponse=null;
        UserSession usr=UserSession.getInstace();
        try{
            usuarioResponse=usuarioRestService.crearEmpleado(emailCrearEmpleado.getText(), contrasenaCrearEmpleado.getText(), nombreCearEmpleado.getText(), apellidoCrearEmpleado.getText(), rolesEmpleado.getValue(),licenciaPiloto.getText() , usr.getPermiso(), usr.getEmpresa());
            if(usuarioResponse.getStatusCode()==HttpStatus.OK){
                showAlert("Exito en la creacion!", "Se ha creado el empleado "+nombreCearEmpleado.getText()+" y con email "+emailCrearEmpleado.getText());
            }
        }catch (Exception e){
            showAlert("Datos Invalidos", "Error al crear empleado");
        }
    }
    @FXML
    public void atras(ActionEvent event){
        int usr=UserSession.getInstace().getPermiso();
        if(usr==0){
            configuraciones.cambiarPantalla(botonAtrasCrearEmpleado.getScene(), vistaAdminController.class,applicationContext);

        } else if(usr==1){
            configuraciones.cambiarPantalla(botonAtrasCrearEmpleado.getScene(), vistaAeropuertoController.class,applicationContext);

        } else if(usr==2){
            configuraciones.cambiarPantalla(botonAtrasCrearEmpleado.getScene(), vistaAerolineaController.class,applicationContext);

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
