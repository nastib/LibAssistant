
package com.javafx.libassistant.beans;

import javafx.beans.property.SimpleStringProperty;


public final class Member {
    
    private  SimpleStringProperty name; 
    private  SimpleStringProperty id ;
    private  SimpleStringProperty mobile; 
    private  SimpleStringProperty email ;

    public Member(){}

    public Member(String name, String id, String mobile, String email) {
    this.name = new SimpleStringProperty(name);
    this.id = new SimpleStringProperty(id);
    this.mobile = new SimpleStringProperty(mobile);
    this.email = new SimpleStringProperty(email);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id); 
    }

    public String getMobile() {
        return mobile.get();
    }

    public void setMobile(String mobile) {
        this.mobile.set(mobile);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email); 
    }

    @Override
    public String toString() {
        return "Member{" + "name=" + name.get() + ", id=" + id.get() + ", mobile=" + mobile.get() + ", email=" + email.get() + '}';
    }  
}
