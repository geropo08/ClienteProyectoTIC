package uy.edu.um.proyectotic.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import uy.edu.um.AerolineaTransporte;
import uy.edu.um.AerolineasDTO;

@Component
public class AerolineaRestService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<AerolineaTransporte> crearAerolinea(String nombre,String codigo,String contacto,String ubicacionSede,String email,String contra){
        AerolineasDTO aerolineasDTO =new AerolineasDTO();
        aerolineasDTO.setCodigo(codigo);
        aerolineasDTO.setNombre(nombre);
        aerolineasDTO.setContacto(contacto);
        aerolineasDTO.setSedePrincipal(ubicacionSede);
        AerolineaTransporte aTransporte=new AerolineaTransporte();
        aTransporte.setAerolineasDTO(aerolineasDTO);
        aTransporte.setContrasena(contra);
        aTransporte.setEmail(email);

       return restTemplate.postForEntity("http://localhost:8080/crearAerolineas", aTransporte, AerolineaTransporte.class);
    }
    
}
