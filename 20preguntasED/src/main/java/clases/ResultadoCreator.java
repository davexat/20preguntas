/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import TDA.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Pc
 */
public class ResultadoCreator {
    private CircularListPRS<VBox> resultados;
    public ResultadoCreator(){
        resultados = new CircularListPRS<>();
    }
    public void agregarResultados(ArbolDecisionPRS<String,String> arbol){
        PilaPRS<String> pilaResultados = arbol.getRespuestas();
        while(!pilaResultados.isEmpty()){
            String animal = pilaResultados.poll();
            VBox contenedor = new VBox();
            ImageView img = asignarImagen(animal);
            Label nombre = new Label(animal);
            decorarVBox(contenedor);
            decorarLabel(nombre);
            decorarImage(img);
            contenedor.getChildren().addAll(img,nombre);
            resultados.add(contenedor);
        }
    }
    private void decorarImage(ImageView imageView){
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
    }
    private void decorarVBox(VBox vbox){
        vbox.setMaxWidth(324);
        vbox.setPrefHeight(360);
        vbox.setPadding(new Insets(10, 10, 20, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #f0f0f0; "
                      + "-fx-border-color: black; "
                      + "-fx-border-width: 2px; "
                      + "-fx-spacing: 25px;");
    }
    private void decorarLabel(Label label){
        label.setFont(Font.font("monospace", FontWeight.BOLD, 35));
        label.setStyle("-fx-wrap-text: true;");
    }
    private ImageView asignarImagen(String animal){
        ImageView img;
        try{
            img = new ImageView(new Image(getClass().getResource("/animales/"+animal+".png").toExternalForm()));
        }catch(Exception e){
            img = new ImageView(new Image(getClass().getResource("/animales/default.png").toExternalForm()));
        }
        return img;
    }
    public CircularListPRS<VBox> getResultados(){
        return resultados;
    }
}
