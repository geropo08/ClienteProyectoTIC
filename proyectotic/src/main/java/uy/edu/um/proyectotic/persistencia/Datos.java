package uy.edu.um.proyectotic.persistencia;

import uy.edu.um.VuelosDTO;

public class Datos {
    private static Datos instance;

    private VuelosDTO Vuelo;

    public static Datos getInstace() {
        if(instance == null) {
            instance = new Datos();
        }
        return instance;
    }
    public static void leaveInstance() {
        instance=null;
    }
    
    public void setVuelo(VuelosDTO Vuelo){
        this.Vuelo=Vuelo;
    }


    public VuelosDTO getVuelo() {
        return Vuelo;
    }
    


    public void cleanUserSession() {
        Vuelo = null;// or null
    }
    
}
