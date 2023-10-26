package uy.edu.um.proyectotic.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import uy.edu.um.UsuariosDTO;

@Component
public class UsuarioRestService {
    @Autowired
    private RestTemplate restTemplate;
    
    public ResponseEntity<Boolean> login(String email, String contrasena){
        UsuariosDTO usr = new UsuariosDTO(email, null, null, contrasena, 0, null, null);
        return restTemplate.postForEntity("http://localhost:8080/verificarLogin", usr, Boolean.class); 
    }

    public ResponseEntity<UsuariosDTO> obtenerUsuario(String email){
        
        return restTemplate.getForEntity("http://localhost:8080/getUsuarios/"+email, UsuariosDTO.class);
    }
    
}
