/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author lenovo
 */
public class User {
  private String username;
  private String password;
  private String email;

  public User(String name, String password){
        if(name==null || password==null){
            throw new IllegalArgumentException();
        }
        this.username=name;
        this.password=password;
  }

  public String getUserName(){
        return this.username;
    }

  public String getUserPassword(){
        return this.password;
    }

  public String getUserMail(){
        return this.email;
    }

  public void setUserEmail(String newEmail){
        if(newEmail==null){
            throw new IllegalArgumentException();
        }
        this.email=newEmail;
    }

  public void changeUserName(String newUserName){
        if(newUserName==null){
            throw new IllegalArgumentException();
        }
        this.username=newUserName;
    }

  public void changeUserPassword(String newUserPassword){
        if(newUserPassword==null){
            throw new IllegalArgumentException();
        }
        this.password=newUserPassword;
    }

  public boolean equals(Object user){
        if(this==user){
            return true;
        }

        if(!(user instanceof User)){
            return false;
        }

        User castedUser = (User) user;
        if(this.username.equals(castedUser.getUserName()) && this.password.equals(castedUser.getUserPassword())){
            return true;
        }
        return false;
  }
}
