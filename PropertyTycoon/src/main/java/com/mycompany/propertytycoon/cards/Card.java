package com.mycompany.propertytycoon.cards;

/**
 *  Card represents the OpportunityKnocks and PotLuck cards
 *  @author Big Nerd Notation
 */
public class Card {
    
    private String description;
    private String action;
    
    /**
     * Card constructor
     * @param desc - description of the card
     * @param action - what the card does
     */
    public Card(String desc, String action) {
        description = desc;
        this.action = action;
    }

    /**
     * Gets the description of the Card
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the action of the Card
     * @return action
     */
    public String getAction() {
        return action;
    }
    
}
