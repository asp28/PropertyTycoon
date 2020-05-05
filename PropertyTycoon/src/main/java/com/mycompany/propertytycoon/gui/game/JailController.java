/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.boardpieces.FreeParkingPiece;
import com.mycompany.propertytycoon.cards.Card;
import com.mycompany.propertytycoon.cards.OpportunityKnocks;
import com.mycompany.propertytycoon.cards.PotLuck;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class JailController implements Initializable {

    @FXML
    private Button card, pay, cancel;
    
    private StageManager SM = StageManager.getInstance();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (SM.getGame().getActivePlayer().getOutOfJailCard() == null) {
            System.out.print(SM.getGame().getActivePlayer().getOutOfJailCard() + "\n");
            card.setDisable(true);
        }
        if (SM.getGame().getActivePlayer().getBalance() < 50) {
            System.out.print(SM.getGame().getActivePlayer().getBalance() + "\n");
            pay.setDisable(true);
        }
        card.setOnAction(e -> {
        //add card back to its arraylist
        //remove card from players object
        //set player to not in jail
        Card c = SM.getGame().getActivePlayer().getOutOfJailCard();
        if (c instanceof OpportunityKnocks) {
            OpportunityKnocks ok = (OpportunityKnocks) c;
            SM.getGame().addToOppo(ok);
            SM.getGame().getActivePlayer().removeGOJFoppo();
            SM.getGame().getActivePlayer().setInJail(false);
        } else {
            PotLuck pl = (PotLuck) c;
            SM.getGame().addToPotLuck(pl);
            SM.getGame().getActivePlayer().removeGOJFpotluck();
            SM.getGame().getActivePlayer().setInJail(false);
        }
        SM.getLog().addToLog(SM.getGame().getActivePlayer().getName() + " used a get out of jail free card.");
        SM.changeScene(View.GAME);
        });
        pay.setOnAction(e -> {
        //take 50 from balance
        //set in jal to false
        //add 50 to free parking
        //change scene
        //log
        SM.getGame().getActivePlayer().decreaseBalance(50);
        FreeParkingPiece fp = (FreeParkingPiece) SM.getGame().getBoard().getBoardPiece(20);
        fp.setBalance(fp.getBalance() + 50);
        SM.getGame().getActivePlayer().setInJail(false);
        SM.getLog().addToLog(SM.getGame().getActivePlayer().getName() + " paid their way out of jail.");
        SM.changeScene(View.GAME);
        });
        cancel.setOnAction(e -> {
            SM.changeScene(View.GAME);
        });
    }    
    
}
