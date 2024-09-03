package services;

import entities.Carbon;
import entities.User;

import java.util.List;

import static entities.User.carbons;

public class CarbonManagement {


    public  static void addConsumptionToUser(User user, Carbon carbon) {

         // user.addCarbonEntry((Carbon) carbon);

        carbons.add(carbon);
    }
}
