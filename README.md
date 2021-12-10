# Automaton
this project allow you to make automatons with simple language and class.

## Installing 
you can get [here](https://github.com/Yvorie90/Automaton/tree/main/jar) the jar containing the .class of my project, feel free to install it on your project.

## How to use

### Summary
- [structure of an automaton](#whats-an-automaton)
- [save it on files](#use-file-to-save-and-write-your-automaton)
- [uses of an automaton](#lets-have-an-example)


### What's an automaton 
here is a very simple and clear exemple <br>
![exemple of an autoamton](https://cdn.discordapp.com/attachments/778226108834512937/918875995270967336/Untitled_Diagram.drawio_1.png) <br>
an automaton is basically a bunch of named states linked with character transitions <br>
there is one inital state and one or multiple final states <br>

**how does it look like in the code** <br>
an Automaton class
```java
public class Automaton {

    private State initialState;
    private List<State> finalStates = new ArrayList<State>();
    
  ...
}  
```
using State class
```java
public class State {

    private String name;
    private Map<Character, State> transitions;

  ...
}  

```
the automaton by itself only have the initial state, the other states are accesible with their transitions<br>
and a list of final states to know the end of the automaton<br>

you can use native constructor and a bunch of getter and setter to modify directly, doc is [here](https://github.com/Yvorie90/Automaton/tree/main/doc)<br>
but I advise you tu use AutomatonBuilder and files


### Use file to save and write your automaton

what's the format of automaton files<br>

[this one](https://github.com/Yvorie90/Automaton/blob/main/data/automaton/SmileyAutomata.txt) is an exemple of a simple automaton of smiley like ":-)"<br>
```
Init E0 
Final E4
E0 : E1
E0 ; E2
E1 = E3
E1 - E3
E2 - E3
E3 ) E4
E3 ( E4
```
set your initial State whit "Init" and the name of your inital state<br>
set your final(s) state(s) with "Final" and the name of your state<br>
to set your transition tell the state where the transition begin, the character transition and then the state where it goes to<br>

important things:<br>
you don't have to initialize anything just use states, they'll be created if they're not already <br>
you can use ".." to set multiple transitions once, like alpha (a,b,c,d, ... ,y,z) like that:<br>
```
A1 a..z A2
A2 A..Z A3
A3 0..9 A4
```
you can set multiple final states once using coma (no space) like that:
```
Final A1,A2,A3
```
you can put Init, Final and transition wherever you want in your file<br>
just remember using line brak between instructions<br>
(two last thing, if you set multiple time Init it will keep the last one and if you put more than a character as a transition it will keep the first one)<br>

**How to use AutomatonBuilder**<br>
don't forget to check the [doc](https://github.com/Yvorie90/Automaton/tree/main/doc)

```java
//create a new Automaton by using AutomatonBuilder and giving it the filepath
Automaton autom = new AutomatonBuilder().creatWithFile("data/automaton/SmileyAutomata.txt");
```
**How to use AutomatonExporter**<br>
```java
// AutomatonExporter has only static method so you don't have to initialize it
Automaton autom = ...
AutomatonExporter.exportAutomatonFormat(autom, "myAutomaton");
```
It will make a new file without extention containing your automaton,<br>
so you can choose it when you set the name (I advise a simple .txt)

### Let's have an example of use

 didn't guessed
first let's write an automaton, we'll use [this one](https://github.com/Yvorie90/Automaton/blob/main/data/automaton/HeureMinuteAutomata.txt)<br>
just to mention, we could have write it this way:
```
E0 0..1 H1
E0 2 H2
H1 0..9 H
H2 0..3 H
H : M1
M1 0..5 M2
M2 0..9 M
Init E0
Final M
```
now we have our file, we saw how to use AutomatonBuilder
```java
Automaton automHHMM = new AutomatonBuilder().creatWithFile("data/automaton/SmileyAutomata.txt");
```
now we have our automaton, let's use it now<br>
we'll use the class AutomatonReader, like AutomatonExporter it has only static methods<br>
if you didn't guessed this automaton is for 'hours : minutes' format<br>
now let's do some test<br>
<br>
those one return true
```
//the accept method match the automaton with the given string (don't forget to check the doc) 
AutomatonReader.accept(automHHMM, "11:23");
AutomatonReader.accept(automHHMM, "00:00");
AutomatonReader.accept(automHHMM, "23:59");
```
those one return false<br>
```
AutomatonReader.accept(automHHMM, "11;23");
AutomatonReader.accept(automHHMM, "24:00");
AutomatonReader.accept(automHHMM, "05:60");
AutomatonReader.accept(automHHMM, "fzehf");
```

### use the appli (only french for now)

if you want to test your automaton you can use my application !! :D <br>
just download this project and run the main class in [appli](https://github.com/Yvorie90/Automaton/blob/main/src/Appli.java)<br>

first it'll open a choice menu in your terminal<br>
![screenshot menu0](https://cdn.discordapp.com/attachments/778226108834512937/918913659953111090/unknown.png)<br>
you can stop it by giving 0<br>
![screenshot stop](https://media.discordapp.net/attachments/778226108834512937/918917519467741194/unknown.png)<br>
if you give:<br>
1 - it'll open a window to let your search your automaton file<br>
2 - it'll open another menu with [those](https://github.com/Yvorie90/Automaton/tree/main/data/automaton) default automaton to let you test <br>
<br>
**choice 1 choose your automaton file**<br>
you can choose to close the window<br>
![screenshot win menu1 close](https://media.discordapp.net/attachments/778226108834512937/918915779007754260/unknown.png?width=466&height=430)<br>
it'll bring you back the the choice menu<br>
![screenshot win menu1 close term](https://media.discordapp.net/attachments/778226108834512937/918915667992932372/unknown.png)<br>
<br>
you can choose your automaton file (we'll test with the smiley automaton)<br>
![screenshot win menu1 open autom](https://cdn.discordapp.com/attachments/778226108834512937/918919119301124166/unknown.png)<br>
it'll tell you the path of your file to ensure you it is the right one and bring you to another choice menu<br>
<br>
in this menu you can give:<br>
0 - to get back to the first choice menu<br>
1 - it'll show you your automaton (using the toString method)<br>
![screenshot matching menu tostring](https://cdn.discordapp.com/attachments/778226108834512937/918919934459928616/unknown.png)<br>
if you give anything else it'll take this as a string to compare with the given automaton and tell you true or false<br>
![screenshot matching menu true](https://media.discordapp.net/attachments/778226108834512937/918921720100966410/unknown.png)<br>
![screenshot matching menu false](https://media.discordapp.net/attachments/778226108834512937/918921885905977364/unknown.png)<br>
it'll show you again this menu until you don't give 0 (or close your terminal)<br>
<br>
**choice 2 choose a default automaton**<br>
in this other choice menu you can just choose an automaton and bring you to the toString / testing menu<br>
(you can always give 0 to come back to the first menu)<br>
![screenshot menu 2](https://media.discordapp.net/attachments/778226108834512937/918922742567419914/unknown.png)<br>
let's choose the email automaton<br>
![screenshot matching email](https://cdn.discordapp.com/attachments/778226108834512937/918923345066590268/unknown.png)<br>
as you can see it's exactly the same menu as before<br>
here is some exemple with the email automaton to finish this showcase properly<br>
![screenshot test matching email](https://cdn.discordapp.com/attachments/778226108834512937/918924539503730708/unknown.png)<br>
<br>
important thing:<br>
in any menu, if you give any improper input the app tell you it's improper and ask you again<br>
![screenshot improper input](https://cdn.discordapp.com/attachments/778226108834512937/918925992741969930/unknown.png)<br>

## if you have any question
feel free to text me on discord Yvorie90#0349<br>

















