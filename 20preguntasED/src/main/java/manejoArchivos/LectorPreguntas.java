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
import javafx.scene.control.Alert;

/**
 *
 * @author Pc
 */
public class LectorPreguntas {
    public PilaPRS<String> leerArchivo(String archivoPreguntas){
        PilaPRS<String> pila = new PilaPRS<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(/*"src/main/resources/files/"+*/archivoPreguntas)))){
            String linea = bf.readLine().trim();
            int limite = 20;
            while (linea != null && (--limite) >= 0){
                pila.push(linea);
                linea = bf.readLine();
                if (linea != null) linea = linea.trim();
            }
        } catch (FileNotFoundException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Archivo de preguntas no encontrado. Verificar, por favor.");
            a.show();
            return null;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return pila;
    }
}
