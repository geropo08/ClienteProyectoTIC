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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.ClienteTransporte;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.servicios.UsuarioRestService;

@Controller
@FxmlView("registrarPasajero.fxml")
public class registroClienteController {
    @Autowired
    Configuraciones conf;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    UsuarioRestService usuarioRestService;

    @FXML
    private TextField apellidoCrearCliente;

    @FXML
    private Button botonAtrasCrearCliente;

    @FXML
    private Button botonCrearCliente;

    @FXML
    private TextField contrasenaCrearCliente;

    @FXML
    private TextField emailCrearCliente;

    @FXML
    private DatePicker fechaNacimientoCrearCliente;

    @FXML
    private TextField nombreCrearCliente;

    @FXML
    private TextField pasaporteCrearCliente;

    @FXML
    void atras(ActionEvent event) {
        conf.cambiarPantalla(botonAtrasCrearCliente.getScene(), loginController.class,applicationContext);
    }

    @FXML
    void crear(ActionEvent event) {
        ResponseEntity<ClienteTransporte> clienteResponse=null;
        try{
            clienteResponse=usuarioRestService.crearCliente(emailCrearCliente.getText(), contrasenaCrearCliente.getText(), nombreCrearCliente.getText(), apellidoCrearCliente.getText(), pasaporteCrearCliente.getText(), fechaNacimientoCrearCliente.getValue());
            if(clienteResponse.getStatusCode()==HttpStatus.OK){
                showAlert("Exito en la creacion!", "Se ha creado el cliente "+nombreCrearCliente.getText()+" y con email "+emailCrearCliente.getText());
            }
        }catch (Exception e){
            showAlert("Datos Invalidos", "Error al crear cliente");
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
