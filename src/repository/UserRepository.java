package repository;

import entities.User;
import java.util.List;

public interface UserRepository {
    void displayUsers();
    void createUser(int id, String name, int age);
    void UpdateUser(User user, String newName, Integer newAge);
    void deleteUser(Integer id);

}
