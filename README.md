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
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * < Your DoD goes here >

### Backlog

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll a dice |  D | 01/01/23 | 01/09/23 |
| x   | F02 | Roll 8 dies |  S | 01/10/23 |  |
| x   | F03 | Score points: count number of gold coins and diamonds, and multiply by 100. | B(F02) | |
| x   | F04 | 2 Players | B(F08) | |
| x   | F05 | Player keeping random dice at their turn | B(F02) | |
| x   | F06 | Play 42 games during a simulation  |  B(F04)  |   |
| x   | F07 | Output percentage of wins for the 2 players  |  B(F06)  |   |
| x   | F08 | end of game with three cranes | B(F05) | |
| ... | ... | ... |

