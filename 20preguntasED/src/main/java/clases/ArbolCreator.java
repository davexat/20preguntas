/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import TDA.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import manejoArchivos.*;

/**
 *
 * @author Pc
 */
public class ArbolCreator {
    private ArbolDecisionPRS<String, String> arbol;
    public ArbolCreator(){
        arbol = new ArbolDecisionPRS<>();
    }
    public void crearArbol(String archivoPreguntas, String archivoRespuestas){
        LectorPreguntas lp = new LectorPreguntas();
        LectorRespuesta lr = new LectorRespuesta();
        PilaPRS<String> preguntas = lp.leerArchivo(archivoPreguntas);
        HashMap<String, PilaPRS<Boolean>> respuestas = lr.leerArchivo(archivoRespuestas);
        if(preguntas != null && respuestas != null){
            arbol = new ArbolDecisionPRS<>();
            arbol.addPreguntasRespuestas(preguntas, respuestas);
        }else{
            arbol = null;
        }
    }
    
    public ArbolDecisionPRS<String,String> getArbol(){
        return arbol;
    }
    
    public boolean validarContinuacion(){
        return arbol!=null;
    } 
}
