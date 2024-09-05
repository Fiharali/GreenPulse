package services;

import entities.Carbon;
import entities.User;
import repository.CarbonRepository;

import static entities.User.carbons;

public class CarbonManagement  implements CarbonRepository {

    @Override
    public   void addConsumptionToUser(User user, Carbon carbon) {
        carbons.add(carbon);
    }
}
