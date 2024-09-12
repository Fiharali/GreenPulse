package services;

import entities.Carbon;
import entities.User;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserManagement  {

    UserRepository userRepository = new UserRepository();
    CarbonManagement carbonManagement = new CarbonManagement();


    public List<User> getAllUsers() {
       return  userRepository.all();
    }

    public  void createUser( String name , int age) {
           if( userRepository.create(name,age))
                System.out.println("Compte créé avec succès !");
    }


    public Optional<User> getUserById(int id) {
       return userRepository.find(id);

    }


    public  void deleteUser( int id) {
        if (userRepository.destroy(id) && getUserById(id).isPresent()) {
            System.out.println("user a été supprimé avec succès  ");
        }else{
            System.out.println("L'utilisateur  n'existe pas.");
        }

    }

    public  void UpdateUser(int id , String name , int age ) {
            if (userRepository.update(id,name,age) && getUserById(id).isPresent()) {
                System.out.println("user  mis à jour avec succès ! ");
            }else{
                System.out.println("L'utilisateur n'a pas été modifier ou n'existe pas.");
            }

        }

    public Double filterByPeriod(int id, LocalDate startDate, LocalDate endDate) {

        Optional<User> user = carbonManagement.getCarbonByUserId(id);

        if (user.isEmpty()) {
            throw new NoSuchElementException("No user found with the specified ID: " + id);
        }

        List<Carbon> filteredCarbons = user.get().getCarbons().stream()
                .filter(carbon -> !carbon.getStartDate().isAfter(endDate) && !carbon.getEndDate().isBefore(startDate))
                .collect(Collectors.toList());

        if (filteredCarbons.isEmpty()) {
            return 0.0;
        }

        double totalConsumption = filteredCarbons.stream()
                .mapToDouble(Carbon::calculerImpact)
                .sum();



        return totalConsumption / filteredCarbons.size();
    }

    public List<User> detectInactiveUsers(LocalDate endDate,LocalDate startDate) {
        List<User> allUsers = userRepository.all();

        List<User> inactiveUsers = allUsers.stream()
                .filter(user -> user.getCarbons().stream()
                        .noneMatch(carbon -> carbon.getStartDate().isAfter(endDate) || carbon.getEndDate().isBefore(startDate)))
                .collect(Collectors.toList());

        return inactiveUsers;
    }

    }













