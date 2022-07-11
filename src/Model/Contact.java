/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author knfab
 */
public class Contact {
    
    private int contactID;
    private String contactName;
    private String Email;

    public Contact(int contactID, String contactName, String Email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.Email = Email;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    @Override
    public String toString(){
        return(contactName + " - " + Email);
    }
    
    
}
