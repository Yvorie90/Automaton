package automaton;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Automaton class, allow you to create an {@link Automaton} with {@link State}
 * @see AutomatonBuilder
 * @see AutomatonExporter
 */
public class Automaton {

    private State initialState;
    private List<State> finalStates = new ArrayList<State>();

    /**
     * Constructor of {@link Automaton}. Construct an Automaton with an Initial {@link State} and a {@link List} of Final {@link State}
     * @param initialState the Initial {@link State}
     * @param finalStates the {@link List} of Final {@link State}
     */
    public Automaton(State initialState, List<State> finalStates) {
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    /**
     * Constructor of {@link Automaton}. Construct an Automaton by just instancing the list of Final {@link State} as an {@link ArrayList}
     * not recommended, it is mostly used by {@link AutomatonBuilder}
     */
    public Automaton(){}

    /**
     * @return the Inital {@link State}
     */
    public State getInitialState() {
        return initialState;
    }

    /**
     * Allow you to set or reset the Initial {@link State}
     * @param initialState the new Initial {@link State} you want to give
     */
    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    /**
     * @return the {@link List} of Final {@link State}
     */
    public List<State> getFinalStates() {
        return finalStates;
    }

    /**
     * Allow you to reset the {@link List} of Final {@link State}
     * @param finalStates the new {@link List} of Final {@link State} you want to give
     */
    public void setFinalStates(List<State> finalStates) {
        this.finalStates = finalStates;
    }

    /**
     * Test if the {@link State} given is one of the Final State of the {@link Automaton}
     * @param finalS the {@link State} you want to test
     * @return  true if the {@link List} of Final {@link State} contains the State given, return false otherwise
     */
    public boolean containFinalState(State finalS){
        return finalStates.contains(finalS);
    }

    /**
     * Allow you to add a {@link State} to the {@link List} of Final {@link State}
     * @param finalS the {@link State} you want to add
     */
    public void addFinalState(State finalS){
        finalStates.add(finalS);
    }

    /**
     * @return a String showing the {@link Automaton} and his Initial and Final {@link State} and all the States and there transition between
     * don't confuse with the needed syntax of an automaton file, please see {@link AutomatonBuilder}
     */
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
