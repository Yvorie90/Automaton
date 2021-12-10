# Automaton
this project allow you to make automatons with simple language and class.

## Installing 
you can get [here](https://github.com/Yvorie90/Automaton/tree/main/jar) the jar containing the .class of my project, feel free to install it on your project.

## How to use

### Summary
- [structure of an automaton](#whats-an-automaton)
- [save it on files](#use-file-to-save-and-write-your-automaton)
- [uses of an automaton](#lets-have-an-example)


#### What's an automaton 
![exemple of an autoamton]() <br>
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
the automaton by itself only have the initial State, the other States are accesible with their transitions<br>
and a list of final State to know the end of the automaton<br>

#### Use file to save and write your automaton


#### Let's have an example











