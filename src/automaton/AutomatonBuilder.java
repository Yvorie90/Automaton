package automaton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class AutomatonBuilder {

    public Map<String, State> everyStates;

    public Automaton creatWithFile(String filePath) throws FileNotFoundException {
        return addWithFile(new Automaton(), filePath);
    }

    /**
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
     *<br>
     *  Exemple:<br>
     *  Init S0<br>
     *  Final S2<br>
     *  S0 a S1<br>
     *  S1 b S2<br>
     *<br>
     *  Note: there is no order for each instruction in the file, you can tell your init an final states wherever you want
     *
     *
     * @param filePath the path of the file where the automata is stocked
     * @throws FileNotFoundException if the path is not correct
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
                    everyStates.put(words[0],new State(words[0]));
                if (!everyStates.containsKey(words[2]))
                    everyStates.put(words[2],new State(words[2]));
                //autom.addTransition(autom.getState(words[0]), words[1].charAt(0), autom.getState(words[2]));
                everyStates.get(words[0]).addTransition(words[1].charAt(0), everyStates.get(words[2]));
            }
        }

        return autom;

    }



}
