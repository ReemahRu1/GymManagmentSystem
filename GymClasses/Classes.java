/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagementsystem.GymClasses;

import java.util.ArrayList;
import java.util.List;

public class Classes {

    private int id;
    private String day;
    private String time;
    private String name;
    private String description;
    private int capacity;
    private Trainer trainer;
    private String room;
    private List<Member> members = new ArrayList<>();

    public Classes(int id, String day, String time, String name, String description, int capacity, Trainer trainer, String room) {
        this.id = id;
        this.day = day;
        this.time = time;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.trainer = trainer;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Member> getMembers() {

        return members;

    }

    public void addMember(Member member) {

        this.members.add(member);

    }

    public String getTrainerName() {
        if(trainer != null){
        return trainer.getName();
        }
        return "";
    }

    public void removeMember(Member member) {

        this.members.remove(member);

    }

    @Override
    public String toString() {
        return "Classes{" + "id=" + id + ", day=" + day + ", time=" + time + ", name=" + name + ", description=" + description + ", capacity=" + capacity + ", trainer=" + trainer + ", room=" + room + ", members=" + members + '}';
    }

}
