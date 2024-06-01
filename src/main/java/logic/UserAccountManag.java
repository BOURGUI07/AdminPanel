/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;
import domain.User;
import java.util.*;

/**
 *
 * @author lenovo
 */
public class UserAccountManag {
  private List<User> accounts;

    public UserAccountManag(){
        this.accounts=new ArrayList<>();
    }

    public void registerUser(String userName, String password, String email){
        if(userName==null || password==null || email==null){
            throw new IllegalArgumentException();
        }
        
        User user = new User(userName,password);
        user.setUserEmail(email);
        this.accounts.add(user);
    }

    public boolean isRegistrated(String userName, String password){
        if(this.accounts.isEmpty() || userName==null || password==null){
            return false;
        }
        return this.accounts.contains(new User(userName, password));
    }

    public boolean doesUserNameExists(String userName){
        if(this.accounts.isEmpty() || userName==null){
            return false;
        }
        Iterator<User> itr = this.accounts.iterator();
        while(itr.hasNext()){
            User user = itr.next();
            if(user.getUserName().equalsIgnoreCase(userName)){
                return true;
            }
        }
        return false;
    }

    public boolean doesPasswordExists(String password){
        if(this.accounts.isEmpty() || password==null){
            return false;
        }
        Iterator<User> itr = this.accounts.iterator();
        while(itr.hasNext()){
            User user = itr.next();
            if(user.getUserPassword().equalsIgnoreCase(password)){
                return true;
            }
        }
        return false;
    }
}
