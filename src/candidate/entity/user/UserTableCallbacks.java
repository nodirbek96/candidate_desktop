/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candidate.entity.user;

import java.util.List;

public interface UserTableCallbacks {
    boolean createUserTable();
    User insertUser(User user);
    User updateUser(User user);
    boolean deleteUser(Integer id);
    User getUser(Integer id);
    List<User> getAllUsers();
    boolean checkUser(String username,String password);
}