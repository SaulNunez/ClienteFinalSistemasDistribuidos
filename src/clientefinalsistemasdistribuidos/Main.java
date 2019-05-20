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
    static Conexion dirSet = new Conexion();
    static Controles controles = new Controles();
    public static ApiConexion api;
    
    public static void StartConection(String serverIp){
        api = new ApiConexion(serverIp, new IOnShitReceived(){
            @Override
            public String MessageReceived(String message) {
                
                return null;
            }
        });
        controles.setVisible(true);
    }
    
    public static void CloseControls(){
        controles.setVisible(false);
        dirSet.setVisible(true);
    }
    
    public static void main(){
        dirSet.setVisible(true);
    }
}
