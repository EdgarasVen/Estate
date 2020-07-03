package lt.estate.app.service;

import lt.estate.app.dto.DtoBuilding;
import lt.estate.app.model.Building;
import lt.estate.app.model.Owner;

import java.time.LocalDate;
import java.util.List;

public interface EstateService {

    List<Owner> getAllOwners();

    List<Building> getAllBuildings();

    void createBuilding(Building building);

    void createOwner(Owner owner);

    Building findBuildingById(Long id);

    void deleteBuildingById(Long id,Building building);

    Owner findOwnerById(Long id);

    void deleteOwnerById(Long id,Owner owner);

    void updateBuilding(Long id, Building newBuilding, Building building);

    void updateOwner(Long id, Owner newOwner, Owner owner);

    void createBuildingAndAddToOwnerById(Long id, DtoBuilding dtoBuilding);

    void changeBuildingOwner(Building building, Owner owner);
}
