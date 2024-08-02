/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDA;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Pc
 */
public class ArbolDecisionPRS<P,R> {
    Node<P> raiz;
    Collection<R> respuestas;
    public static int ejecuciones = 0;
    protected class Node<P>{
        P pregunta;
        ArbolDecisionPRS<P,R> izq;
        ArbolDecisionPRS<P,R> der;
        Node(P e){
            pregunta = e;
            izq = der = null;
        }
    }
    public PilaPRS<R> getRespuestas(){
        return (PilaPRS<R>) respuestas;
    }
    public ArbolDecisionPRS<P,R> correrIzq(){
        return raiz.izq;
    }
    public ArbolDecisionPRS<P,R> correrDer(){
        return raiz.der;
    }
    public int getAltura(){
        if (isEmpty()) return 0;
        if (isLeaf()) return 1;
        int alturaIzq = (raiz.izq == null) ? 0 : raiz.izq.getAltura();
        int alturaDer = (raiz.der == null) ? 0 : raiz.der.getAltura();
        return 1 + Math.max(alturaIzq, alturaDer);
    }
    public boolean addPreguntasRespuestas(PilaPRS<P> preguntas, Map<R, PilaPRS<Boolean>> respuestas){
        if (preguntas == null || respuestas == null) return false;
        if (preguntas.isEmpty()) return false;
        // Sacar la primera pregunta
        P p = preguntas.poll();
        // Sacar el primer conjunto de respuestas
        Set<R> answers = respuestas.keySet();
        PilaPRS<R> pila = new PilaPRS<>();
        pila.addAll(answers);
        // Reemplazar la rama por la primera pregunta y la primera respuesta
        raiz = new Node(p);
        this.respuestas = pila;
        // Extraer las primeras respuestas de cada pregunta (si es posible)
        // Si no es posible, no hacer nada
        // Verificar si es true o false para crear dos mapas diferentes
        Map<R, PilaPRS<Boolean>> mapaTrue = new HashMap<>();
        Map<R, PilaPRS<Boolean>> mapaFalse = new HashMap<>();
        for (R r: answers){
            PilaPRS<Boolean> datos = respuestas.get(r);
            if(!datos.isEmpty()){
                boolean b = datos.poll();
                if (b)  mapaTrue.put(r, datos);
                else    mapaFalse.put(r, datos);
            }
        }
        if (!preguntas.isEmpty()){
            PilaPRS<P> preguntasIzq = new PilaPRS<>();
            preguntasIzq.addAll(preguntas);
            PilaPRS<P> preguntasDer = new PilaPRS<>();
            preguntasDer.addAll(preguntas);
            raiz.izq = new ArbolDecisionPRS<>();
            raiz.der = new ArbolDecisionPRS<>();
            raiz.der.addPreguntasRespuestas(preguntasDer, mapaFalse);
            raiz.izq.addPreguntasRespuestas(preguntasIzq, mapaTrue);
        }
        return true;
    }
    public ArbolDecisionPRS(){
        raiz = null;
        respuestas = new PilaPRS<>();
    }
    public ArbolDecisionPRS(P p){
        raiz = new Node(p);
        respuestas = new PilaPRS<>();
    }
    public P getContenido(){
        return raiz.pregunta;
    }
    public ArbolDecisionPRS<P,R> getIzq(){
        return raiz.izq;
    }
    public ArbolDecisionPRS<P,R> getDer(){
        return raiz.der;
    }
    public boolean addRespuesta(R r){
        if (r == null) return false;
        if (raiz == null) return false;
        respuestas.add(r);
        return true;
    }
    public boolean isEmpty(){
        return raiz == null;
    }
    public boolean isLeaf(){
        return raiz.izq == null && raiz.der == null;
    }
    public boolean addLeft(ArbolDecisionPRS<P,R> arbol){
        if (raiz == null) return false;
        raiz.izq = arbol;
        return true;
    }
    public boolean addRight(ArbolDecisionPRS<P,R> arbol){
        if (raiz == null) return false;
        raiz.der = arbol;
        return true;
    }
}
