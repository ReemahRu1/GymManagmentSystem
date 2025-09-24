/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagementsystem.GymClasses;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tebya
 */
public class Member extends User implements Identifiable{

    private String membership;
    private List<Service> services = new ArrayList<>();
    private List<Classes> classes = new ArrayList<>();
    private List<Feedback> feedbacks = new ArrayList<>();

    public Member(int id, String name, String phone, String username, String password, String email,String membership) {
        super(id, name, phone, username, password, email);
        this.membership = membership;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public String toString() {
        return super.toString()+"Member{" + "membership=" + membership + ", services=" + services + ", classes=" + classes + ", feedbacks=" + feedbacks + '}';
    }

    

    @Override
    public String getIdentifier() {
        return "Member";
    }

}