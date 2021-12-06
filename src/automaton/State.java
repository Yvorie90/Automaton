package automaton;

import java.util.HashMap;
import java.util.Map;

public class State {

    private String name;
    private Map<Character, State> transitions;

    public State(String sname){
        name = sname;
        transitions = new HashMap<Character, State>();
    }

    public State(String sname, Map<Character, State> stransitions){
        name = sname;
        transitions = stransitions;
    }

    //public State(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Character, State> getTransitions(){
        return transitions;
    }

    public void setTransitions(Map<Character, State> stranstitions){
        transitions = stranstitions;
    }

    public void addTransition(Character transition, State destination){
        transitions.put(transition, destination);
    }

    public boolean containsTransition(Character transition){
        return transitions.containsKey(transition);
    }

    public void removeTransition(Character transition){
        transitions.remove(transition);
    }

    public State nextState(char trans){
        if (!containsTransition(trans))
            return null;
        return transitions.get(trans);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(name).append(" :\n");
        for (char trans : transitions.keySet())
            str.append("  ").append(trans).append(" -> ").append(transitions.get(trans).getName()).append("\n");


        return str.toString();
    }
}
