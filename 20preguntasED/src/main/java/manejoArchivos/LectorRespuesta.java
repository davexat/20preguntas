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

/**
 *
 * @author Pc
 */
public class LectorRespuesta {
    public HashMap<String, PilaPRS<Boolean>> leerArchivo(String archivoRespuestas){
        HashMap<String, PilaPRS<Boolean>> mapa = new HashMap<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(new File("src/main/resources/files/"+archivoRespuestas)))) {
            String linea = bf.readLine();
            while (linea!=null){
                linea = linea.strip();
                String respuesta = leerRespuesta(linea);
                PilaPRS<Boolean> respuestas = procesarLinea(linea);
                mapa.put(respuesta, respuestas);
                linea = bf.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontró el archivo");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        return mapa;
    }
    public PilaPRS<Boolean> procesarLinea(String linea){
        String datos[] = linea.split(" ");
        PilaPRS<Boolean> respuestas = new PilaPRS<>();
        for (int i=1; i < datos.length; i++){
            final boolean respuesta = datos[i].equalsIgnoreCase("si") || datos[i].equalsIgnoreCase("sí");
            respuestas.push(respuesta);
        }
        return respuestas;
    }
    public String leerRespuesta(String linea){
        String datos[] = linea.split(" ");
        return datos[0];
    }
}
