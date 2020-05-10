# Sprint 2 Documentation

## Summary Data

- **Team Number:** 13
- **Team Lead:** Ankeet
- **Sprint Start:** 10/02/2020
- **Sprint End:** 17/02/2020

## Individual Key Contributions

| Team Member | Key Contributions |
| :---------: | :---------------: |
|    Aiden    |  Documentation & Implementation   |
|   Ankeet    |  Organisation & Implementation   |
|    Chris    |  Implementation   |
|   Duarte    |  Implementation   |

## Task Cards

- Implement a rent feature when a player lands on an owned property
- Build a basic GUI to show the board
- Create text-based game to understand how the game should work
- Edit players to incorporate unique tokens
- Create a method to tell what actions a player can perform
- Modify objects from previous sprint with the new features

The image below shows the tasks set out on Trello during our weekly meeting

![Trello task cards](images/trello2.png)

## Gantt Chart

![Gantt Chart](images/ganttChart2.png)

## Requirements Analysis

### Functional Requirements

- F1
  - The software shall implement a renting mechanism in which a player will pay a small fee for landing on an owned property. For any ownable property on the board, a non-negotiable, pre-determined fee must be paid to the owner of said property immediately after landing on said property. If a property is unowned, the player shall not pay a fee to the bank.
- F2
  - The software shall be displayed in a neat and efficient manner on the screen to the players. On this graphical interface, it shall display the current player's balance. In addition, there should be buttons which will in turn either roll the dice, buy a property or sell a property.
- F3
  - The software shall indicate on the GUI what actions the current player can perform. All actions up to this point are: rolling dice, buying a property, selling a property and ending their turn. For example if the player has rolled a non-double, the software should disable the action to roll again. On the contrary if the player rolls a double, the software should keep the action to roll again available.
- F4
  - Each player in the game will have a unique identifiable token. No two players shall have the same token. This token in the future will be displayed on the board and will move around the board.

### Non-Functional Requirements
- NF1
  - To contruct the graphical user interface (GUI) of the software, our team has chosen to use JavaFX based on the premise that everyone in our team has some knowledge of JavaFX. If in the future there is an easier alternative, the question will be posed in the weekly discussion meeting to determine whether to switch.

### Domain Requirements
- D1
  - We are unsure of how the system of rolling 3 doubles and going to jail should function. We will ask the customer in our next meeting.

## Design

### UML Diagram
___

![UML](images/UML2.svg)

### Sequence Diagrams
___

#### Method to perform valid player actions based on their input
![Sequence1](images/performActionSequence.svg)

#### GameLoop initialiser to set the board, bank, dice and players

![Sequence2](images/GameLoopConstructorSequence.svg)

## Test Plan

This current sprint was a mixture of system-wide testing and classic JUnit tests. With the system-wide system, the text-based game ensured that all objects interact with every other object. In addition to this, a basic GUI was developed and testing through basic buttons and text fields.

With the **GUI**, the testing methods ensured that:
1. all visuals on the screen were correctly displayed and were visually appealing
2. all buttons that were displayed on the screen, when clicked upon, performed the attached action

![GUI buttons](images/guiTest.png)

With the **Player** test class, the added testing capability ensured that:
1. the correct token was chosen when a Player object was created
2. the rent functionality was correctly paid to the owner to the property. Else, the player was removed


![Player token](images/playerTest.png)

![Player rent](images/playerRentTest.png)

## Summary of Sprint