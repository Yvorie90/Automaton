package automaton;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Automaton {

    private State initialState;
    private List<State> finalStates;

    public Automaton(State initialState, List<State> finalStates) {
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public Automaton(){
        finalStates = new ArrayList<State>();
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public List<State> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(List<State> finalStates) {
        this.finalStates = finalStates;
    }

    public boolean containFinalState(State finalS){
        return finalStates.contains(finalS);
    }

    public void addFinalState(State finalS){
        finalStates.add(finalS);
    }

    @Override
    public String toString() {
        //TODO add a way to translate a ->... , b ->... , ... , z ->... etc to a..z (with if get(i)+1 == get(i+1)
        StringBuilder str = new StringBuilder();
        List<State> list = new ArrayList<State>();
        list.add(initialState);
        LinkedHashSet<State> hashSetState = new LinkedHashSet<State>();

        str.append("Init : ").append(initialState.getName()).append("\n");
        str.append("Final : ");
        for (int i = 0; i < finalStates.size(); i++) {
            str.append(finalStates.get(i).getName());
            try{
                finalStates.get(i+1);
                str.append(", ");
            }catch (IndexOutOfBoundsException e){
                str.append("\n");
                break;
            }
        }
        str.append("States :\n");
        int i = 0;
        while (true){
            try {
                State s = list.get(i);
                for (State st : s.getTransitions().values()) if (hashSetState.add(st)) list.add(st);
                str.append("  ").append(s.getName()).append(" : \n");
                for (char trans : s.getTransitions().keySet())
                    str.append("    ").append(trans).append(" -> ").append(s.getTransitions().get(trans).getName()).append("\n");

                i++;
            }catch(IndexOutOfBoundsException e ) {
                break;
            }
        }

        return str.toString();
    }
}
