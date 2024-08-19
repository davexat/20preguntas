/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejoArchivos;

import TDA.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javafx.scene.control.Alert;

/**
 *
 * @author Pc
 */
public class LectorRespuesta {
    public HashMap<String, PilaPRS<Boolean>> leerArchivo(String archivoRespuestas){
        HashMap<String, PilaPRS<Boolean>> mapa = new HashMap<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(/*"src/main/resources/files/"+*/archivoRespuestas)))) {
            String linea = bf.readLine();
            while (linea!=null){
                linea = linea.strip();
                String respuesta = leerRespuesta(linea);
                PilaPRS<Boolean> respuestas = procesarLinea(linea);
                mapa.put(respuesta, respuestas);
                linea = bf.readLine();
            }
        } catch(IllegalArgumentException e){
            Alert a = new Alert(Alert.AlertType.ERROR, e.getMessage());
            a.show();
            return null;
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Archivo de respuestas no encontrado. Verificar, por favor.");
            a.show();
            return null;
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "ERROR FATAL. Verificar, por favor.");
            a.show();
        }
        return mapa;
    }
    public PilaPRS<Boolean> procesarLinea(String linea){
        String datos[] = linea.split(" ");
        PilaPRS<Boolean> respuestas = new PilaPRS<>();
        for (int i=1; i < datos.length; i++){
            final boolean respuesta = validarRespuesta(datos[i]);
            respuestas.push(respuesta);
        }
        return respuestas;
    }
    public String leerRespuesta(String linea){
        String datos[] = linea.split(" ");
        return datos[0];
    }
    public boolean validarRespuesta(String s){
        if(esStringDiferente(s)) throw new IllegalArgumentException("Archivo de respuesta con formato incorrecto.");
        final boolean verdadero1 = s.equalsIgnoreCase("si");
        final boolean verdadero2 = s.equalsIgnoreCase("sí");
        final boolean verdadero3 = s.equals("1");
        
        return verdadero1 || verdadero2 || verdadero3;
    }
    
    public boolean esStringDiferente(String s){
        String sNew = s.toLowerCase();
        return !(sNew.equals("si") || sNew.equals("sí") || sNew.equals("1") || sNew.equals("no") || sNew.equals("0"));
    }
}
