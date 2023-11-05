package uy.edu.um.proyectotic.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import uy.edu.um.AvionesDTO;

@Component
public class AvionRestService {
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<AvionesDTO> crearAvion(String matricula, String modelo, String cantidadPersonas, String capacidadCarga, String distancia, String empresa) throws Exception{
        int cantidadPersonasI=Integer.parseInt(cantidadPersonas);
        float capacidadCargaF=Float.parseFloat(capacidadCarga);
        float distanciaF=Float.parseFloat(distancia);
        AvionesDTO avion=new AvionesDTO();
        avion.setMatricula(matricula);
        avion.setEmpresa(empresa);
        avion.setModelo(modelo);
        avion.setDistanciaDeDespegue(distanciaF);
        avion.setCapacidadCarga(capacidadCargaF);
        avion.setCantidadPasajeros(cantidadPersonasI);
        return restTemplate.postForEntity("http://localhost:8080/crearAvion", avion, AvionesDTO.class);

    }
    
}
