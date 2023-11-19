package uy.edu.um.proyectotic.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import uy.edu.um.AvionesDTO;
import uy.edu.um.ClienteTransporte;
import uy.edu.um.ClientesDTO;
import uy.edu.um.UsuarioTransporte;
import uy.edu.um.UsuariosDTO;

import java.util.List;

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

    public ResponseEntity<UsuarioTransporte> crearEmpleado(String email, String contrasena, String nombre, String apellido, String rol, String licenciaPiloto, int permiso, String empresa){
        UsuariosDTO usr = new UsuariosDTO();
        usr.setApellido(apellido);
        usr.setNombre(nombre);
        usr.setRol(rol);
        usr.setEmpresa(empresa);
        usr.setPermiso(permiso);
        usr.setEmail(email);
        usr.setContrasena(contrasena);
        UsuarioTransporte usuarioTransporte= new UsuarioTransporte(usr, licenciaPiloto);
        return restTemplate.postForEntity("http://localhost:8080/addUsuarios", usuarioTransporte, UsuarioTransporte.class);
    }
    public ResponseEntity<ClienteTransporte> crearCliente(String email, String contrasena, String nombre, String apellido, String Pasaporte, LocalDate fechaNacimiento){
        UsuariosDTO usr = new UsuariosDTO();
        usr.setApellido(apellido);
        usr.setNombre(nombre);
        usr.setRol("Cliente");
        usr.setEmpresa(null);
        usr.setPermiso(3);
        usr.setEmail(email);
        usr.setContrasena(contrasena);
        ClientesDTO clientesDTO=new ClientesDTO();
        clientesDTO.setPasaporte(Pasaporte);
        clientesDTO.setEmail(email);
        clientesDTO.setFechaNacimiento(fechaNacimiento);
        ClienteTransporte clienteTransporte= new ClienteTransporte(clientesDTO, usr);
        return restTemplate.postForEntity("http://localhost:8080/registrarCliente", clienteTransporte, ClienteTransporte.class);

    }

    public ResponseEntity<List<UsuariosDTO>> getEmpleados(String empresa) {
        return restTemplate.exchange("http://localhost:8080/getEmpleados/"+empresa,  HttpMethod.GET, null, new ParameterizedTypeReference<List<UsuariosDTO>>(){});
    }

    public ResponseEntity<ClientesDTO> obtenerClienteEmail(String email){
        return restTemplate.postForEntity("http://localhost:8080/obtenerClienteEmail", email, ClientesDTO.class);
    }
}
