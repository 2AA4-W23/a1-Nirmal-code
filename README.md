# A1 - Piraten Karpen

  * Author: Nirmal Chaudhari
  * Email: chaudn12@mcmaster.ca, nirmal.chaudhari2003@gmail.com

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`mvn 
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 
  * To run the project in development mode without log with required arguments:
    * `mvn -q exec:java -Dexec.args="random combo"`
  * To run with program with logging with required arguments:
    * `mvn -q exec:java -Dmy.trace="true" -Dexec.args="random combo"`


Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * Fully functional feature that is able to accurately and efficiently interact with other features/codes. 
   * Limited technical debt, has an excellent object oriented design.
   * Code is encapsulated. 
   * Feature code has meaningful naming convention that describes exactly what it is used for in the absence of comments.
   * Feature code has no warning errors. 

### Backlog

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F02 | Roll 8 dies |  D | 01/10/23 | 01/12/23 |
| x   | F03 | Score points: count number of gold coins and diamonds, and multiply by 100. | D | 01/12/23 | 01/12/23 |
| x   | F04 | 2 Players | D | 01/12/23 | 01/12/23 |
| x   | F05 | Player keeping random dice at their turn. Minimum of 2 dies | D | 01/12/23 | 01/12/23 |
| x   | F06 | Play 42 games during a simulation (NOT VALID ANYMORE)  |  D  | 01/12/23  | 01/12/23 |
| x   | F07 | Output percentage of wins for the 2 players  |  D  | 01/12/23  | 01/12/23 |
| x   | F08 | end of game with three cranes | D | 01/12/23 | 01/12/23 |
| x   | F09 | Score points: pairs of 3 dies | D | 01/12/23 | 01/13/23 |
|     | F10 | Score points: pairs of 4,5,6,7,8 dies. | D | 01/17/23 |  |
|     | F11 | Strategy to maximize combos when picking dies. | D | 01/17/23 |  |
|     | F12 | Specify command line arg which strategy to use. | D | 01/19/23 | 01/20/23 |
|     | F13 | Player recieves 0 points on 3 skulls | D | 01/18/23 | 01/18/23 |
|     | F14 | Players continue playing until someone reaches 6000 points | D | 01/21/23 | 01/21/23 |
|     | F15 | When player reaches 6000, other player gets 1 turn for redemption | D | 01/21/23 | 01/21/23 |
|     | F16 | Cards with a type, point and val attribute | D | 01/24/23 | 01/24/23 |
|     | F17 | Card features where you can get the value, points and type associated with card. | D | 01/24/23 | 01/24/23 |
|     | F18 | Card deck with 35 cards total, 6 sea battle and 29 blank | D | 01/24/23 | 01/24/23 |
|     | F19 | Card deck pick card feature that picks a card from deck.. | D | 01/24/23 | 01/24/23 |
|     | F20 | Card deck shuffle feature that shuffles the deck of cards. | D | 01/24/23 | 01/24/23 |
|     | F21 | New Strategy for Sea Battle. | D | 01/25/23 | 01/25/23 |
|     | F22 | New Card: Monkey Business, 4 in deck. | D | 01/25/23 | 01/25/23 |
|     | F23 | New monkey business strategy | D | 01/25/23 | 01/25/23 |
|     | F24 | New Card: Sorceress Card, 4 in deck | D | 01/28/23 | 01/28/23 |
|     | F25 | New Sorceress Strategy| D | 01/28/23 | 01/28/23 |
| ... | ... | ... |