
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;


import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author freder
 */
public class VueControleur extends Application {
    
    // modèle : ce qui réalise le calcule de l'expression
    Modele m;
    // affiche la saisie et le résultat
    Text affichage;
    
    @Override
    public void start(Stage primaryStage) {
        
        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele();
        
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        
        int column = 0;
        int row = 0;
        
//        
//        affichage = new Text("");
//        affichage.setFont(Font.font ("Verdana", 20));
//        affichage.setFill(Color.RED);
//        border.setTop(affichage);
//        
//        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
//        m.addObserver(new Observer() {
//            
//            @Override
//            public void update(Observable o, Object arg) {
//                if (!m.getErr()) {
//                    affichage.setText(m.getValue() + "");
//                } else {
//                    affichage.setText("Err");
//                }
//            }
//        });
//        
//        // on efface affichage lors du clic
//        affichage.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            
//            @Override
//            public void handle(MouseEvent event) {
//                affichage.setText("");
//            }
//            
//        });
        
        // cr�ation de la grille
        int compt = 0;
        int x = 0;
        int ybis = 0;
        for(x = 0; x < 8; x++){
        	for(int y = 0; y < 8; y++){
        		
        		Text t = new Text();
        		t.setStyle("-fx-background-color: #000000;");
        		t.setWrappingWidth(30);
        		t.setFont(Font.font ("Verdana", 20));
        		t.setTextAlignment(TextAlignment.CENTER);
        		
        		Rectangle rect = new Rectangle();
        		rect.setWidth(80);
        		rect.setHeight(80);
        		System.out.println(compt%2 +" "+ compt);
        		if(compt%2 == 0){
        			//rect.setStroke(Color.WHITE);
        			t.setText("B");
        			rect.setFill(Color.WHITE);
        		} else {
        			//rect.setStroke(Color.BLACK);
        			t.setText("N");
        			rect.setFill(Color.BLACK);
        		}
        		compt++;

        		
        		gPane.add(rect, x, y);
        	}
        	if(ybis%2 == 0)
        		compt = 1;
        	else
        		compt = 0;
        	ybis++;
        	
        }
//        for (String s : new String[]{"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "+", "0", "(", ")"}) {
//            final Text t = new Text(s);
//            t.setWrappingWidth(30);
//            t.setFont(Font.font ("Verdana", 20));
//            t.setTextAlignment(TextAlignment.CENTER);
//            
//            gPane.add(t, column++, row);
//            
//            if (column > 3) {
//                column = 0;
//                row++;
//            }
//            
//            // un controleur (EventHandler) par bouton écoute et met à jour le champ affichage
//            t.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                
//                @Override
//                public void handle(MouseEvent event) {
//                    affichage.setText(affichage.getText() + t.getText());
//                }
//                
//            });
//            
//            
//            
//        }
        
        
//        
//        final Text t = new Text("=");
//        t.setWrappingWidth(30);
//        gPane.add(t, column++, row);
//        t.setTextAlignment(TextAlignment.CENTER);
//        //t.setEffect(new Shadow());
//        
//        // un controleur écoute le bouton "=" et déclenche l'appel du modèle
//        t.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            
//            @Override
//            public void handle(MouseEvent event) {
//                m.calc(affichage.getText());
//            }
//        });
        
        gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);
        
        Scene scene = new Scene(border, Color.WHITE);
        
        primaryStage.setTitle("Calc FX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
