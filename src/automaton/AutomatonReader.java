package automaton;

/**
 * AutomatonReader class, allow you to use an {@link Automaton} with static methods
 * @see Automaton
 */
public class AutomatonReader {


    /**
     * A static method telling you if a given {@link String} match with a given {@link Automaton}
     * @param autom the {@link Automaton} you want the string to be testing on
     * @param str the {@link String} you want to be tested
     * @return true if the {@link String} match with the {@link Automaton},return false otherwise (falling in an error State)
     */
    public static boolean accept(Automaton autom, String str){
        State current = autom.getInitialState();
        for (char c : str.toCharArray()){
            current = current.nextState(c);
            if (current == null) break;
        }
        return autom.containFinalState(current);

    }


}
