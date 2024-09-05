package repository;

import entities.Carbon;
import entities.User;

public interface CarbonRepository {

    void addConsumptionToUser(User user, Carbon carbon);
}
