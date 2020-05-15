<p align="center">
  <img src="/DevelopmentDocuments/Sprints/companyLogo.png" alt="Team logo"/>
</p>

# PropertyTycoon

This is the game Property Tycoon developed by team 13 for the module Software Engineering at the University Of Sussex (2020).

## Documentation

All documentation is located in the file 'DevelopmentDocuments'. There is a simple file system with one file for each sprint cycle that contains all the relevant documents for that sprint. There is also an overall project plan that states what we set out to do in each sprint at the beginning of the project.

Trello was used to plan and organise task cards for every sprint. The Trello board can be accessed [here](https://trello.com/b/VlTDakv4/software-engineering-project)

## Code

Inside the 'PropertyTycoon' file you will find all the code that was developed along with any additional resources used to develop the game. Inside the 'src' you will find an intricite passage of packages that were used to keep organisation simple and effective. The project main in located at 'com.mycompany.propertytycoon'

GitHub was used to to maintain our code base. The repository can be found [here](https://github.com/asp28/PropertyTycoon)

## GUI

The GUI is working as of JAVA 8 on NetBeans IDE version 8.2. The GUI follows a simple layout of selecting a gamemode, potentially a time length, then the number of players and finally a name and token for each of those players.

Once in the game you can easily play, with a simple and easy to follow display. 
- Clicking on a property will display the information about it
- If there is something you must do before you end your turn, the end turn button will be disabled until you do it.
- You can trade, mortgage and sell anytime during your turn.
- If you are sent to jail you can wait 3 turns or click the jail button and select an option on how you wish to escape.
- To buy property, a popup will appear if you are eligible to buy it.
- Auctions occur when a player does not wish to buy a property that they can buy.

The menu on the right shows the current players token, balance, name, cards owned, button they can use and the log of the current game.

All graphics and logos seen in the project are solely designed and created by the team.

## Dependencies
- Maven
- ApachePOI
- hamcrest-core
- junit

## Authors
Team 13

## Licence

MIT License

Copyright (c) 2020 Big Nerd Notation

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
