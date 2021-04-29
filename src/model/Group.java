package model;

import java.util.Vector;

public class Group {

    private Vector<Person> group = new Vector<>();

    public void addPersonGroup(Person p){
        group.add(p);
    }

    public Person getPerson(int i){
        return group.get(i);
    }

    public int getSize(){
            return group.size();
        }


}
