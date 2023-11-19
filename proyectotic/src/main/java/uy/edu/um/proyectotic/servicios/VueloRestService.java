package uy.edu.um.proyectotic.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.List;


import uy.edu.um.AvionesDTO;
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
        vuelo.setFechaLlegada(fechaLlegada);;
        vuelo.setHoraLlegada(horaLlegada);
        vuelo.setFechaSalida(fechaSalida);
        vuelo.setHoraSalida(horaSalida);
        vuelo.setAerolinea(aerolinea);
        vuelo.setMatricula(matricula);

        return restTemplate.postForEntity("http://localhost:8080/crearVuelo", vuelo, VuelosDTO.class);
    }

    public ResponseEntity<List<VuelosDTO>> getVuelos(String empresa) {
        return restTemplate.exchange("http://localhost:8080/getVuelos/"+empresa,  HttpMethod.GET, null, new ParameterizedTypeReference<List<VuelosDTO>>(){});
    }

    public ResponseEntity<List<VuelosDTO>> getVuelosAerolinea(String empresa) {
        return restTemplate.exchange("http://localhost:8080/getVuelosAerolinea/"+empresa,  HttpMethod.GET, null, new ParameterizedTypeReference<List<VuelosDTO>>(){});
    }

    public ResponseEntity<List<VuelosDTO>> getAllVuelosAeropuerto(String empresa) {
        return restTemplate.exchange("http://localhost:8080/getAllVuelosAeropuerto/"+empresa,  HttpMethod.GET, null, new ParameterizedTypeReference<List<VuelosDTO>>(){});
    }
    public ResponseEntity<List<VuelosDTO>> getVuelosAerolineaAceptados(String empresa) {
        return restTemplate.exchange("http://localhost:8080/getVuelosAerolineaAceptados/"+empresa,  HttpMethod.GET, null, new ParameterizedTypeReference<List<VuelosDTO>>(){});
    }
}
