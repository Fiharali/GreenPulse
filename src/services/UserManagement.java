package services;

import Config.DBConnection;
import entities.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserManagement  {

    UserRepository userRepository = new UserRepository();


    public List<User> getAllUsers() {
       return  userRepository.all();
    }

    public  void createUser( String name , int age) {
            userRepository.create(name,age);
            System.out.println("Compte créé avec succès !");
    }


    public  User getUserById( int id) {
       return userRepository.find(id);

    }


    public  void deleteUser( int id) {
        if (userRepository.destroy(id) && getUserById(id) != null) {
            System.out.println("user a été supprimé avec succès  ");
        }else{
            System.out.println("L'utilisateur n'a pas été supprimé ou n'existe pas.");
        }

    }

    public  void UpdateUser(int id , String name , int age ) {
            if (userRepository.update(id,name,age) && getUserById(id) != null) {
                System.out.println("user  mis à jour avec succès ! ");
            }else{
                System.out.println("L'utilisateur n'a pas été modifier ou n'existe pas.");
            }

        }

    }









