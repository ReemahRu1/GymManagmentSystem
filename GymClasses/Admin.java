/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagementsystem.GymClasses;

/**
 *
 * @author tebya
 */
public class Admin extends User implements Identifiable{

    public Admin(int id, String name, String phone, String username, String password, String email) {
        super(id, name, phone, username, password, email);
    }

    @Override
    public String getIdentifier() {
        return "Admin";
    }


   
}