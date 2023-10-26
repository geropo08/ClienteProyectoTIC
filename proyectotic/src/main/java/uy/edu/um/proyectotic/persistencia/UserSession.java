package uy.edu.um.proyectotic.persistencia;

public class UserSession {
    private static UserSession instance;

    private String userName;
    private int permiso;
    private String empresa;



    public static UserSession getInstace() {
        if(instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    public static void leaveInstance() {
        instance=null;
    }
    
    public void setUsername(String userName){
        this.userName=userName;
    }

    public void setPermiso(int permiso){
        this.permiso=permiso;
    }

    public void setEmpresa(String empresa){
        this.empresa=empresa;
    }

    public String getUserName() {
        return userName;
    }
    public int getPermiso() {
        return permiso;
    }
    public String getEmpresa() {
        return empresa;
    }


    public void cleanUserSession() {
        userName = "";// or null
        permiso = -1;// or null
        empresa="";
    }


    
}
