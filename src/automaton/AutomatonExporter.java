package automaton;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class AutomatonExporter {


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

    //TODO
    public static boolean exportDotFormat(Automaton autom, String fileName){
        try {
            FileWriter writer = new FileWriter(fileName);

            StringBuilder str = new StringBuilder();


            writer.write(str.toString());
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }


    }









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
