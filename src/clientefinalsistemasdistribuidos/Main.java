/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientefinalsistemasdistribuidos;

import java.awt.Color;

/**
 *
 * @author saul
 */
public class Main {

    static Conexion dirSet = new Conexion();
    static Controles controles = new Controles();
    public static ApiConexion api;
    
    public static void StartConection(String serverIp) {
        api = new ApiConexion(serverIp, new IOnShitReceived() {
            @Override
            public String MessageReceived(String message) {
                String[] keywords = message.split("\\s+");
                System.out.println(message);
                
                if (keywords.length == 4) {
                    if ("color".equals(keywords[0])) {
                        int r, g, b;
                        r = Integer.parseInt(keywords[1]);
                        g = Integer.parseInt(keywords[2]);
                        b = Integer.parseInt(keywords[3]);
                        
                        Color c = new Color(r, g, b);
                        controles.SetNewColors(c);
                        return message;
                    }
                } else if (keywords.length == 2) {
                    if ("power".equals(keywords[0])) {
                        Boolean turnOn = Boolean.getBoolean(keywords[1]);
                        controles.SetNewOnState(turnOn);
                        return message;
                    }
                }
                return null;
            }
        });
        controles.setVisible(true);
    }
    
    public static void CloseControls() {
        controles.setVisible(false);
        dirSet.setVisible(true);
    }
    
    public static void main() {
        dirSet.setVisible(true);
    }
    
    public static void updateColor(Color c) {
        api.updateColor(c);
    }
    
    public static void ledPowerState(Boolean on) {
        api.updatePowerState(on);
    }
}
