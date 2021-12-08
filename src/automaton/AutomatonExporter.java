package automaton;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * AutomatonExporter class, Allow you to create a file of your {@link Automaton} with static methods
 * this file has a specific syntax used to regenerate your {@link Automaton} by using {@link AutomatonBuilder}
 * @see Automaton
 * @see AutomatonBuilder
 */
public class AutomatonExporter {

    /**
     * Allow you to create you automaton file by giving the {@link Automaton} and a name for your file
     * @param autom the {@link Automaton} you want to put in a file
     * @param fileName the name of the file
     * @return true if the file is correctly created, return false otherwise
     * @see AutomatonExporter#getFormatedAutomaton(Automaton)
     */
    public static boolean exportAutomatonFormat(Automaton autom, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(getFormatedAutomaton(autom));
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     *  !!! NOT IMPLEMENTED YET !!!
     *
     * Allow you to create you automaton file by giving the {@link Automaton} and a name for your file
     * using the .dot format to create image of your automaton
     * @param autom the {@link Automaton} you want to put in a file
     * @param fileName the name of the file
     * @return true if the file is correctly created, return false otherwise
     */
    public static boolean exportDotFormat(Automaton autom, String fileName){ //TODO
        try {
            FileWriter writer = new FileWriter(fileName + ".dot");
            StringBuilder str = new StringBuilder();




            writer.write(str.toString());
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    /**
     * @param autom the {@link Automaton} you want to get the String at the format used in {@link AutomatonBuilder}
     * @return the string representing the given {@link Automaton}
     * @see AutomatonExporter#exportAutomatonFormat(Automaton, String)  
     * @see AutomatonBuilder
     */
    public static String getFormatedAutomaton(Automaton autom){
            StringBuilder str = new StringBuilder();
            List<State> list = new ArrayList<State>();
            list.add(autom.getInitialState());
            LinkedHashSet<State> hashSetState = new LinkedHashSet<State>();

            str.append("Init ").append(autom.getInitialState().getName()).append("\n");
            str.append("Final ");
            for (int i = 0; i < autom.getFinalStates().size(); i++) {
                str.append(autom.getFinalStates().get(i).getName());
                try{
                    autom.getFinalStates().get(i+1);
                    str.append(",");
                }catch (IndexOutOfBoundsException e){
                    str.append("\n");
                    break;
                }
            }
            str.append("\n");
            int i = 0;
            while (true){
                try {
                    State s = list.get(i);
                    for (State st : s.getTransitions().values()) if (hashSetState.add(st)) list.add(st);
                    for (char trans : s.getTransitions().keySet())
                        str.append(s.getName()).append(" ").append(trans).append(" ").append(s.getTransitions().get(trans).getName()).append("\n");
                    str.append("\n");
                    i++;
                }catch(IndexOutOfBoundsException e ) {
                    break;
                }
            }
            return str.toString();
    }

}
