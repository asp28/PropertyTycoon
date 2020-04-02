/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ankeet
 */
public class StageManager {

    private static StageManager instance;

    private Stage currStage;

    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }
    
    public void setStage(Stage s) {
        currStage = s;
    }

    public void init() {
        try {
            String canon = new File(".").getCanonicalPath();
            System.out.print("\n\n\n" + canon + "\n\n\n");
        } catch (IOException e) {
            
        }
        Parent root = loadHierarchy("src/main/java/com/mycompany/propertytycoon/gui/mainmenu/homepage.fxml");
        Scene scene = new Scene(root);

        currStage.setTitle("Property Tycoon");
        currStage.setScene(scene);
        currStage.show();
    }

    private Parent loadHierarchy(String fxmlFilePath) {
        Parent p = null;
        try {
            URL url = new File(fxmlFilePath).toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            p = FXMLLoader.load(url);
        } catch (IOException e) {
            System.err.print("Caught IO exception");
        }
        return p;
    }
    
    public void changeScene(final View view) {
        try {
            Parent viewHierarchy = loadHierarchy(view.getFXMLPath());
            Scene scene = new Scene(viewHierarchy);
            currStage.setScene(scene);
            currStage.show();
            
        }
        catch (Exception e) {
            System.err.print("");
        }
    }
    
    public void quit() {
        currStage.close();
    }
    
}
