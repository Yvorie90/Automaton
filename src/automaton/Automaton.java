package automaton;

import java.util.ArrayList;
import java.util.List;

public class Automaton {

    public State initialState;
    public List<State> finalStates;

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
        StringBuilder str = new StringBuilder();

        str.append("Init : ").append(initialState.getName()).append("\n");
        str.append("Final : ");
        for (int i = 0; i < finalStates.size(); i++) {
            str.append(finalStates.get(i).getName());
            if (finalStates.get(i+1) == null)
                str.append("\n");
            else
                str.append(", ");
        }
        str.append("States :\n");

        



        return str.toString();
    }
}
