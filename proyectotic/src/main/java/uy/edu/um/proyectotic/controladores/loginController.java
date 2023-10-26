package uy.edu.um.proyectotic.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import uy.edu.um.UsuariosDTO;
import uy.edu.um.proyectotic.persistencia.Configuraciones;
import uy.edu.um.proyectotic.persistencia.UserSession;
import uy.edu.um.proyectotic.servicios.UsuarioRestService;

@Controller
@FxmlView("Login.fxml")
public class loginController {
    @Autowired
    ConfigurableApplicationContext applicationContext;

    @Autowired
    UsuarioRestService usuariosService;

    @Autowired
    Configuraciones conf;

    @FXML
    private Button button_inciarsesion;

    @FXML
    private PasswordField contrasena_login;

    @FXML
    private TextField usuario_login;

    @FXML
    void onOKButtonClick(ActionEvent event) {
        ResponseEntity<Boolean> usuarioResponse = usuariosService.login(usuario_login.getText(), contrasena_login.getText());
        Boolean login = usuarioResponse.getBody();
        if (login==false){
            System.out.println("Error al inicio de sesion");
        } else {
            ResponseEntity<UsuariosDTO> usuarioGet= usuariosService.obtenerUsuario(usuario_login.getText());
            UsuariosDTO usuario=usuarioGet.getBody();
            int permiso=usuario.getPermiso();
            UserSession usr=UserSession.getInstace();
            usr.setEmpresa(usuario.getEmpresa());
            usr.setPermiso(permiso);
            usr.setUsername(usuario.getEmail());
            switch (permiso) {
                case 0:
                    System.out.println("Admin");
                    conf.cambiarPantalla(button_inciarsesion.getScene(), loginController.class,applicationContext);
                    break;
                case 1:
                    System.out.println("Aeropuerto");
                    conf.cambiarPantalla(button_inciarsesion.getScene(), loginController.class,applicationContext);
                    break;
                case 2:
                    conf.cambiarPantalla(button_inciarsesion.getScene(), loginController.class,applicationContext);
                    System.out.println("Aerolinea");
                    break;
                case 3:
                    System.out.println("Usuario");
                    break;
                default:
                    System.out.println("Error al inicio de sesion");
                    break;
            }
        }

        
    }



    
}
