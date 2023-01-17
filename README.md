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

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * Fully functional feature that is able to accurately and efficiently interact with other features/codes. 
   * Feature code has meaningful naming convention that describes exactly what it is used for in the absence of comments.
   * Feature code has no warning errors. 

### Backlog

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll a dice |  D | 01/01/23 | 01/09/23 |
| x   | F02 | Roll 8 dies |  D | 01/10/23 | 01/12/23 |
| x   | F03 | Score points: count number of gold coins and diamonds, and multiply by 100. | D | 01/12/23 | 01/12/23 |
| x   | F04 | 2 Players | D | 01/12/23 | 01/12/23 |
| x   | F05 | Player keeping random dice at their turn. Minimum of 2 dies | D | 01/12/23 | 01/12/23 |
| x   | F06 | Play 42 games during a simulation  |  D  | 01/12/23  | 01/12/23 |
| x   | F07 | Output percentage of wins for the 2 players  |  D  | 01/12/23  | 01/12/23 |
| x   | F08 | end of game with three cranes | D | 01/12/23 | 01/12/23 |
| x   | F09 | Score points: pairs of 3 dies | D | 01/12/23 | 01/13/23 |
| x   | F10 | Score points: pairs of 4,5,6,7,8 dies | P |  |  |
| x   | F11 | Strategy to maximize combos when picking dies | P |  |  |
| x   | F11 | Specify command line arg which strategy to use. | P |  |  |
| ... | ... | ... |