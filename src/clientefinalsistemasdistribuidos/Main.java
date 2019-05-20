/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientefinalsistemasdistribuidos;

/**
 *
 * @author saul
 */
public class Main {
    static Conexion c = new Conexion();
    static Controles controles = new Controles();
    public static ApiConexion api;
    
    public static void StartConection(String serverIp){
        api = new ApiConexion(serverIp);
        controles.main();
    }
    
    public static void Main(){
        c.main();
    }
}
