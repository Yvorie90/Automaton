package automaton;

import java.util.HashMap;
import java.util.Map;

/**
 *  State class, allowing you to create State and link them with transitions using characters
 *  States are used to create {@link Automaton}
 *  @see AutomatonBuilder
 */
public class State {

    private String name;
    private Map<Character, State> transitions;

    /**
     * Constructor of {@link State}. Construct a State with a given name
     * @param name name of the {@link State}
     */
    public State(String name){
        this.name = name;
        transitions = new HashMap<Character, State>();
    }

    /**
     * Constructor of {@link State}. Construct a State with a given name and a map of transitions
     * @param name name of the {@link State}
     * @param transitions map of transitions : Char → State
     */
    public State(String name, Map<Character, State> transitions){
        this.name = name;
        this.transitions = transitions;
    }

    /**
     * @return the name of a {@link State}
     */
    public String getName() {
        return name;
    }

    /**
     * Allow you to reset the name of a {@link State}
     * @param name the new name you want to give
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the map of transitions of a {@link State}
     */
    public Map<Character, State> getTransitions(){
        return transitions;
    }

    /**
     * Allow you to reset the map of transitions of a {@link State}
     * @param transitions the new map of transition you want to replace with
     */
    public void setTransitions(Map<Character, State> transitions){
        this.transitions = transitions;
    }

    /**
     * Allow you to add a transition to a {@link State}
     * @param transition the Character you want to be the transition (the kay of the map)
     * @param destination the {@link State} you want the transition to point to (the value of the map)
     */
    public void addTransition(Character transition, State destination){
        transitions.put(transition, destination);
    }

    /**
     * Test if the transition given is a transition of the current State
     * @param transition the transition character you want to test
     * @return true if the map of transitions contains the transition, return false otherwise
     */
    public boolean containsTransition(Character transition){
        return transitions.containsKey(transition);
    }

    /**
     * Allow you to remove a transition from a {@link State}
     * @param transition the transition character you want to remove
     */
    public void removeTransition(Character transition){
        transitions.remove(transition);
    }

    /**
     * @param transition a transition of the {@link State}
     * @return the {@link State} pointed by the transition, null if the transition is not a transition of the {@link State}
     * @see State#containsTransition(Character)
     */
    public State nextState(char transition){
        if (!containsTransition(transition))
            return null;
        return transitions.get(transition);
    }

    /**
     * @return A string showing the {@link State} and his transitions
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(name).append(" :\n");
        for (char trans : transitions.keySet())
            str.append("  ").append(trans).append(" -> ").append(transitions.get(trans).getName()).append("\n");


        return str.toString();
    }
}
