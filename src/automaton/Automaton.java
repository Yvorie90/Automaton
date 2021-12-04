package automaton;

import java.util.List;

public class Automaton {

    public State initialState;
    public List<State> finalStates;

    public Automaton(State initialState, List<State> finalStates) {
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public Automaton(){}

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




}
