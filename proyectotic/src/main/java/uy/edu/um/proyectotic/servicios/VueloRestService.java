package uy.edu.um.proyectotic.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;


import uy.edu.um.VuelosDTO;

@Component
public class VueloRestService {
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<VuelosDTO> crearVuelo(String iataLlegada, String iataSalida, String aerolinea, LocalDate fechaSalida, String horaSalida, LocalDate fechaLlegada, String horaLlegada, String idVuelo, String matricula){
        VuelosDTO vuelo=new VuelosDTO();
        vuelo.setCodigoVuelo(idVuelo);
        vuelo.setAeropuertoSalida(iataSalida);
        vuelo.setAeropuertoLlegada(iataLlegada);
        vuelo.setFechaSalida(fechaSalida);;
        vuelo.setHoraLlegada(horaSalida);
        vuelo.setFechaSalida(fechaSalida);
        vuelo.setHoraSalida(horaSalida);
        vuelo.setAerolinea(aerolinea);
        vuelo.setMatricula(matricula);

        return restTemplate.postForEntity("http://localhost:8080/crearVuelo", vuelo, VuelosDTO.class);
    }
}
