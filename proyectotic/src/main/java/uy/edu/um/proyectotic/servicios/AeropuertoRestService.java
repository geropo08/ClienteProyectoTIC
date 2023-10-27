package uy.edu.um.proyectotic.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import uy.edu.um.AeropuertoTransporte;

import uy.edu.um.AeropuertosDTO;

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


    
}
