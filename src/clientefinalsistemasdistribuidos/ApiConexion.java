/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientefinalsistemasdistribuidos;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saul
 */
public class ApiConexion extends Thread {

    Socket cliente;
    BufferedReader entrada, texto;
    PrintStream salida;
    InputStreamReader e;
    IOnShitReceived iOnShitReceived;

    public ApiConexion(String ip, IOnShitReceived iOnShitReceived) {
        try {
            this.cliente = new Socket(ip, 4444);
            salida = new PrintStream(cliente.getOutputStream());
            e = new InputStreamReader(cliente.getInputStream());
            entrada = new BufferedReader(e);
        } catch (IOException ex) {
            Logger.getLogger(ApiConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String enviado = entrada.readLine();
                iOnShitReceived.MessageReceived(enviado);
            }
        } catch (IOException ex) {
            Logger.getLogger(ApiConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateColor(Color c) {
        salida.println(String.format("color %d %d %d", c.getRed(), c.getGreen(), c.getBlue()));
        System.out.println("Update sent");
    }
    
    public void updatePowerState(boolean on){
        salida.println(String.format("power %b", on));
        System.out.println("Update sent");
    }

    @Override
    protected void finalize() {
        try {
            super.finalize();

            salida.close();
            entrada.close();
            cliente.close();
        } catch (Throwable ex) {
            Logger.getLogger(ApiConexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
