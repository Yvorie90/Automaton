
import automaton.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Appli {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        //TODO retirer la Jframe qui s'affiche en plus lors de l'affichage du FileDialog
        menu();
    }


    public static String chooseFile(JFrame framee){
        //framee.setSize(800,600);
        framee.setVisible(true);
        String absPAth = new File(".").getAbsolutePath();
        FileDialog fd = new FileDialog(framee,"choose your automaton", FileDialog.LOAD);
        framee.setLocationRelativeTo(fd);
        fd.setDirectory(absPAth);
        fd.setVisible(true);
        framee.setVisible(false);
        if (fd.getFile()==null)
            return fd.getFile();
        return fd.getDirectory()+fd.getFile();
    }

    public static void menu() throws IOException {
        //String path = chooseFile(new JFrame());
        while (true) {
            System.out.println("veuillez choisir une action :\n 1 - charger un automate avec un fichier\n 2 - choisir un des automates par défaut\n 0 - arrêter le programme\n");
            switch (reader.readLine()) {
                case "0":
                    System.exit(0);
                case "1":
                    menu1();
                    break;
                case "2":
                    menu2();
                    break;
                default:
                    System.out.println("veuillez rentrer un chiffre valide");
                    break;
            }
        }

    }

    public static void menu1() throws IOException {
        while (true) {
            String path = chooseFile(new JFrame());
            if (path != null) System.out.println("votre automate : \n" + path);
            else {
                System.out.println("vous avez annulé votre choix, retour au menu");
                return;
            }
            try {
                AutomatonBuilder autombuild = new AutomatonBuilder();
                Automaton autom = autombuild.creatWithFile(path);
                menu3(autom);
                return;
            } catch (FileNotFoundException e) {
                System.out.println("veuillez saisir un chemin valide");
            }
        }

    }

    public static void menu2() throws IOException {
        while (true) {
            System.out.println("vueillez choisir parmis ces automates :\n 1 - smiley\n 2 - HHMM(format heure minute)\n 3 - email\n 0 - pour revenir au menu");
            Automaton autom = new Automaton();
            switch (reader.readLine()) {
                case "0":
                    return;
                case "1":
                    autom = new AutomatonBuilder().addWithFile(autom, "data/automaton/SmileyAutomata.txt");
                    menu3(autom);
                    break;
                case "2":
                    autom = new AutomatonBuilder().addWithFile(autom, "data/automaton/HeureMinuteAutomata.txt");
                    menu3(autom);
                    break;
                case "3":
                    autom = new AutomatonBuilder().addWithFile(autom, "data/automaton/EmailAddressAutomata.txt");
                    menu3(autom);
                    break;
                default:
                    System.out.println("veuillez rentrer un chiffre valide");
                    break;
            }
        }
    }

    public static void menu3(Automaton autom) throws IOException {
        System.out.println("veuillez saisir la chaine de caractères que vous voulez comparer avec l'automate sinon:\n 1 - afficher l'automate\n 0 - pour revenir au menu\n");
        String str = reader.readLine();
        if (str.equals("0"))
            return;
        else if (str.equals("1"))
            System.out.println(autom);
        else
            System.out.println(str+ " " + AutomatonReader.accept(autom, str));
        menu3(autom);
    }

}
