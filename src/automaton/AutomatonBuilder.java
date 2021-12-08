package automaton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * AutomatonBuilder class, allow you to create a builder for an {@link Automaton} from file
 * @see Automaton
 * @see AutomatonExporter
 */
public class AutomatonBuilder {

    private Map<String, State> everyStates = new HashMap<String, State>();

    /**
     * Allow you to create an {@link Automaton} with a file by giving the path of the file
     * @param filePath the path of your automaton file
     * @return a new {@link Automaton} created with your file
     * @throws FileNotFoundException if the file is not founded (check if your path is correct)
     */
    public Automaton creatWithFile(String filePath) throws FileNotFoundException {
        return addWithFile(new Automaton(), filePath);
    }

    /* OLD JAVADOC (goto readme)
     * A methode to create an automata from a file<br>
     *<br>
     * Your file need to respect this:<br>
     *  - You need to do each instruction on a unique line<br>
     *  - The initial state need to be unique and expressed like this :<br>
     *    "Init YourInitialState"<br>
     *  - A Final state need to be expressed like this :<br>
     *    "Final YourFinalState"<br>
     *  - Each transition need to be expressed like this :<br>
     *    "TheStateTheTransitionStart TheCharacterTransition TheStateTheTransitionEnd"<br>
     *    (now support expression like a..z or 0..9, it will add transitions a to z from ASCII<br>
     *    /!\ a..z ≠ z..a /!\ )<br>
     *<br>
     *  Exemple:<br>
     *  Init S0<br>
     *  Final S2<br>
     *  S0 a S1<br>
     *  S1 0..9 S2<br>
     *<br>
     *  Note: there is no order for each instruction in the file, you can tell your init an final states wherever you want
     *
     *
     * @param filePath the path of the file where the automata is stocked
     * @throws FileNotFoundException if the path is not correct
     */

    /**
     * Allow you to concat (add data) from a given {@link Automaton} with another from a file by giving the path
     * useful if you want to partition your automata in multiple files
     * @param autom the {@link Automaton} you want to add thing to
     * @param filePath the path of your automaton file
     * @return the {@link Automaton} with new datas added
     * @throws FileNotFoundException if the file is not founded (check if your path is correct)
     */
    public Automaton addWithFile(Automaton autom, String filePath) throws FileNotFoundException {
        Scanner myFile = new Scanner(new FileInputStream(filePath));

        String[] words;

        while (myFile.hasNext()){
            words = myFile.nextLine().split(" ");


            if (words.length == 2){
                if (words[0].equals("Init")){
                    if(!everyStates.containsKey(words[1]))
                        everyStates.put(words[1],new State(words[1]));
                    autom.setInitialState(everyStates.get(words[1]));
                }else if (words[0].equals("Final")){
                    String[] fin = words[1].split(",");
                    for(String s : fin) {
                        if(!everyStates.containsKey(s))
                            everyStates.put(s,new State(s));
                        autom.addFinalState(everyStates.get(s));
                    }
                }
            }


            if (words.length == 3){
                if (!everyStates.containsKey(words[0]))
                    everyStates.put(words[0], new State(words[0]));
                if (!everyStates.containsKey(words[2]))
                    everyStates.put(words[2], new State(words[2]));

                if (words[1].length() == 4 && words[1].charAt(1) == '.' && words[1].charAt(2) == '.'){ // transition like a..z
                    char tmp = words[1].charAt(0);
                    while (tmp <= words[1].charAt(3)){ //precondition: a  <  z !!!
                        everyStates.get(words[0]).addTransition(tmp, everyStates.get(words[2]));
                        tmp++;
                    }

                }

                if (words[1].length() == 1) { //1 character transition
                    everyStates.get(words[0]).addTransition(words[1].charAt(0), everyStates.get(words[2]));
                }
            }

        }

        return autom;

    }


}
