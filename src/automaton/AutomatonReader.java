package automaton;

public class AutomatonReader {



    public boolean accept(Automaton autom, String str){
        State current = autom.getInitialState();
        for (char c : str.toCharArray()){
            current = current.nextState(c);
            if (current == null) break;
        }
        return autom.containFinalState(current);

    }


}
