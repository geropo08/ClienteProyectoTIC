package uy.edu.um.proyectotic.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import uy.edu.um.AerolineaTransporte;
import uy.edu.um.AerolineasDTO;
import uy.edu.um.OrigenDestino;
import uy.edu.um.PasajerosVuelosT;
import uy.edu.um.VuelosDTO;

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

    public ResponseEntity<List<String>> getAeropuertosAsociados(String aerolinea){
        return restTemplate.exchange("http://localhost:8080/getAeropuertosAsociados/"+aerolinea,  HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>(){});
    }

    public ResponseEntity<List<AerolineasDTO>> getAerolineas(){
        return restTemplate.exchange("http://localhost:8080/getAerolineas",  HttpMethod.GET, null, new ParameterizedTypeReference<List<AerolineasDTO>>(){});
    }

    public ResponseEntity<List<AerolineasDTO>> getAerolineasAeropuerto(String empresa) {
        return restTemplate.exchange("http://localhost:8080/getAerolineasAeropuerto/"+empresa,  HttpMethod.GET, null, new ParameterizedTypeReference<List<AerolineasDTO>>(){});

    }

    public ResponseEntity<PasajerosVuelosT> asignarVueloPasajero(String codigoVuelo, String pasaporte){
        PasajerosVuelosT pasajerosVuelosT=new PasajerosVuelosT(codigoVuelo,pasaporte);
        return restTemplate.postForEntity("http://localhost:8080/asignarVueloPasajero", pasajerosVuelosT, PasajerosVuelosT.class);

    }

    public ResponseEntity<List<VuelosDTO>> vuelosComprados(String pasaporte){
    
        HttpEntity<String> requestEntity = new HttpEntity<>(pasaporte);
        return restTemplate.exchange("http://localhost:8080/vuelosComprados", HttpMethod.POST, requestEntity,  new ParameterizedTypeReference<List<VuelosDTO>>(){});

    }

    public ResponseEntity<List<String>> getPilotos(String empresa) {
        return restTemplate.exchange("http://localhost:8080/getPilotos/"+empresa,  HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>(){});
    }

    public ResponseEntity<List<String>> getListaAviones(String empresa) {
        return restTemplate.exchange("http://localhost:8080/getListaAviones/"+empresa,  HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>(){});
    }
}
