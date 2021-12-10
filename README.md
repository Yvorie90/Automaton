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
AutomatonExporter.exportAutomatonFormat(autom, "myAutomaton")

```
It will make a new file without extention containing your automaton,<br>
so you can choose it when you set the name (I advise a simple .txt)

### Let's have an example of use











