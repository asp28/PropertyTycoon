package com.mycompany.propertytycoon.boardpieces;

/**
 *  OpportunityKnocksPiece represents the OpportunityKnocks tile on the board
 *  @author Big Nerd Notation
 */
public class OpportunityKnocksPiece extends BoardPiece {

    private String action;

    /**
     * OpportunityKnocksPiece constructor
     * @param title title of OpportunityKnocks card
     * @param action action of OpportunityKnocks card
     */
    public OpportunityKnocksPiece(String title, String action) {
        super(title);
        this.action = action;
    }

    /**
     * Gets the action related to the OpportunityKnocksPiece
     * @return OpportunityKnocks action
     */
    public String getAction() {
        return action;
    }

}
