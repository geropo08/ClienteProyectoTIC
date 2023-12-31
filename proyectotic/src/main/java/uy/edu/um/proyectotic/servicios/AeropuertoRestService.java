package uy.edu.um.proyectotic.servicios;


import java.util.List;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import uy.edu.um.AceptacionVuelosT;
import uy.edu.um.AeropuertoTransporte;

import uy.edu.um.AeropuertosDTO;
import uy.edu.um.AsociacionTransporte;
import uy.edu.um.DisponibilidadPuertasT;
import uy.edu.um.PuertasPistasTransporte;
import uy.edu.um.proyectotic.controladores.Aeropuerto.asociarAerolineaController;




@Component
public class AeropuertoRestService {
    @Autowired
    private RestTemplate restTemplate;

    
    public ResponseEntity<AeropuertoTransporte> crearAeropuerto(String mail, String contrasena, String pais, String codigo, String nombre, String cantidadPuertas,String cantidadMangas,String cantidadPuestos) throws Exception{
        int cantidadMangasI=0;
        int cantidadPuertasI=0;
        int cantidadPuestosI=0;
        try{
            cantidadMangasI=Integer.parseInt(cantidadMangas);
            cantidadPuertasI=Integer.parseInt(cantidadPuertas);
            cantidadPuestosI=Integer.parseInt(cantidadPuestos);
        } catch (Exception e){
            throw new Exception();
        }
        AeropuertosDTO aeropuertosDTO=new AeropuertosDTO();
        aeropuertosDTO.setCodigoIATA(codigo);
        aeropuertosDTO.setNombre(nombre);
        aeropuertosDTO.setPais(pais);
        aeropuertosDTO.setCantidadMangas(cantidadMangasI);
        aeropuertosDTO.setCantidadPuertas(cantidadPuertasI);
        aeropuertosDTO.setCantidadPuestos(cantidadPuestosI);
        AeropuertoTransporte aeropuertoTransporte=new AeropuertoTransporte(aeropuertosDTO,mail,contrasena);
        
        return restTemplate.postForEntity("http://localhost:8080/crearAeropuertos", aeropuertoTransporte, AeropuertoTransporte.class);
    }

    public ResponseEntity<AsociacionTransporte> asociarAerolinea(String aerolinea, String aeropuerto){
        AsociacionTransporte aTransporte=new AsociacionTransporte(aerolinea,aeropuerto);
        return restTemplate.postForEntity("http://localhost:8080/asociarAerolineasAeropuertos", aTransporte, AsociacionTransporte.class);

    }
    public ResponseEntity<PuertasPistasTransporte> crearPuertas(String aerolinea, List<String> listaPuertas){
        PuertasPistasTransporte pTransporte=new PuertasPistasTransporte(aerolinea,listaPuertas);
        return restTemplate.postForEntity("http://localhost:8080/crearPuertas", pTransporte, PuertasPistasTransporte.class);
    }
    public ResponseEntity<PuertasPistasTransporte> crearPistas(String aerolinea, List<String> listaPistas){
        PuertasPistasTransporte pTransporte=new PuertasPistasTransporte(aerolinea,listaPistas);
        return restTemplate.postForEntity("http://localhost:8080/crearPistas", pTransporte, PuertasPistasTransporte.class);
    }
    public ResponseEntity<List> disponibilidadPuertas(String aeropuerto, String hora, LocalDate fecha){
        DisponibilidadPuertasT disponibilidadPuertas =  new DisponibilidadPuertasT(aeropuerto, hora, fecha);

        return restTemplate.postForEntity("http://localhost:8080/puertasDisponibles", disponibilidadPuertas, List.class);
    }
    public ResponseEntity<List> disponibilidadPistas(String aeropuerto, String hora, LocalDate fecha){
        DisponibilidadPuertasT disponibilidadPistas =  new DisponibilidadPuertasT(aeropuerto, hora, fecha);

        return restTemplate.postForEntity("http://localhost:8080/pistasDisponibles", disponibilidadPistas, List.class);
    }

    public ResponseEntity<List<AeropuertosDTO>> getAeropuertos(){
        return restTemplate.exchange("http://localhost:8080/getAeropuertos", HttpMethod.GET, null, new ParameterizedTypeReference<List<AeropuertosDTO>>() {});
    }

    public ResponseEntity<AceptacionVuelosT> aceptarVuelo(String codigoVuelo, String aeropuerto, String puerta, String pista){
        AceptacionVuelosT aceptacionVuelosT=new AceptacionVuelosT(codigoVuelo, puerta, pista, aeropuerto);
        return restTemplate.postForEntity("http://localhost:8080/aceptarVuelos", aceptacionVuelosT, AceptacionVuelosT.class);
    }
    public ResponseEntity<AceptacionVuelosT> denegarVuelo(String codigoVuelo, String aeropuerto){
        AceptacionVuelosT denegarVuelosT=new AceptacionVuelosT(codigoVuelo, "", "", aeropuerto);
        return restTemplate.postForEntity("http://localhost:8080/denegarVuelos", denegarVuelosT, AceptacionVuelosT.class);
    }

    public ResponseEntity<List<String>> paisesDisponibles(){
            return restTemplate.exchange("http://localhost:8080/paisesDisponibles", HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});

    }
    
}
