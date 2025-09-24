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
public class Trainer extends User implements Identifiable{

    private String trainershift;
    private List<Classes> classes = new ArrayList<>();

    public Trainer(int id, String name, String phone, String username, String password, String email,String trainershift) {
        super(id, name, phone, username, password, email);
        this.trainershift = trainershift;
    }

    public String getTrainershift() {
        return trainershift;
    }

    public void setTrainershift(String trainershift) {
        this.trainershift = trainershift;
    }

    

    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return super.toString()+"Trainer{" + "trainershift=" + trainershift + ", classes=" + classes + '}';
    }

    @Override
    public String getIdentifier() {
        return "Trainer";
    }

    
}
