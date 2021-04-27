/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.user;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author Rayen Ben Driaa
 */
public interface IServiceUser {
    public void AddUser (user user);
     public ObservableList<user> ListUser();
     public void remove(user user);
     public void updateUser (user user);
     public int login (user user);
     }
