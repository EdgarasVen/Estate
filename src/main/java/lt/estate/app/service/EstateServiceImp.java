package lt.estate.app.service;

import lombok.extern.slf4j.Slf4j;
import lt.estate.app.dto.DtoBuilding;
import lt.estate.app.model.Building;
import lt.estate.app.model.Owner;
import lt.estate.app.repo.RepoBuilding;
import lt.estate.app.repo.RepoOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EstateServiceImp implements EstateService{
    private final RepoBuilding repoBuilding;
    private final RepoOwner repoOwner;

    @Autowired
    public EstateServiceImp(RepoBuilding repoBuilding, RepoOwner repoOwner) {
        this.repoBuilding = repoBuilding;
        this.repoOwner = repoOwner;
    }

    @Override
    public List<Owner> getAllOwners() {
        List<Owner> list=repoOwner.findAll();
        log.info("IN getAllOwners - list size:{} ",list.size());
        return list;
    }

    @Override
    public List<Building> getAllBuildings() {
        List<Building> list=repoBuilding.findAll();
        log.info("IN getAllBuildings - list size:{} ",list.size());
        return list;
    }

    @Override
    public void createBuilding(Building building) {
        repoBuilding.save(building);
        log.info("IN createBuilding - save successful");
    }

    @Override
    public void createOwner(Owner owner) {
        repoOwner.save(owner);
        log.info("IN createOwner - save successful");
    }

    @Override
    public Building findBuildingById(Long id) {
        List<Building> list=repoBuilding.findAll();
        Building building= list.stream().filter(i -> i.getId().equals(id))
                .findFirst().orElse(null);
        log.info("IN findBuildingById - find successful");
        return building;
    }

    @Override
    public void deleteBuildingById(Long id,Building building) {
        Long ownerId=building.getOwner().getId();
        Owner owner=findOwnerById(ownerId);
        if(owner!=null){
            owner.deleteBuilding(building);
            repoBuilding.delete(building);
            repoOwner.save(owner);
        }
        repoBuilding.delete(building);
        log.info("IN deleteBuildingById - deleted by id: {}",id);

    }

    @Override
    public Owner findOwnerById(Long id) {
        List<Owner> list=repoOwner.findAll();
        Owner owner= list.stream().filter(i -> i.getId().equals(id))
                .findFirst().orElse(null);
        if(owner==null){
            log.info("IN findOwnerById - no owner with such id: {}",id);
        } else {
            log.info("IN findOwnerById - find successful");
        }
        return owner;
    }

    @Override
    public void deleteOwnerById(Long id , Owner owner) {
        List<Building> list=repoBuilding.findAll();
        List<Building> filteredList=list.stream()
                .filter(building -> building.getOwner()!=null)
                .filter(building -> building.getOwner().getId().equals(id))
                .collect(Collectors.toList());
        for (Building b :filteredList
                ) {
            b.setOwner(null);
            repoBuilding.save(b);
            log.info("IN deleteOwnerById - set null to building owner, building id: {}",b.getId());
        }
        repoOwner.deleteById(id);
        log.info("IN deleteOwnerById - deleted by id: {}",id);
    }

    @Override
    public void updateBuilding(Long id, Building newBuilding ,Building building) {
        Owner owner=building.getOwner();
        building.setUpdated(new Date());
        building.setAddress(newBuilding.getAddress());
        building.setSize(newBuilding.getSize());
        building.setValue(newBuilding.getValue());
        building.setType(newBuilding.getType());
        repoBuilding.save(building);
        owner.calculateTax();
        repoOwner.save(owner);
        log.info("IN updateBuilding - building updated");
    }

    @Override
    public void updateOwner(Long id, Owner newOwner, Owner owner) {
        owner.setUpdated(new Date());
        owner.setName(newOwner.getName());
        owner.setAddress(newOwner.getAddress());
        owner.setSurname(newOwner.getSurname());
        owner.setTelephone(newOwner.getTelephone());
        owner.setBuildings(newOwner.getBuildings());
        if(newOwner.getBuildings()!=null) owner.calculateTax();
        repoOwner.save(owner);
        log.info("IN updateOwner - owner updated");
    }

    @Override
    public void createBuildingAndAddToOwnerById(Long id, DtoBuilding dtoBuilding) {
        List<Owner> list =repoOwner.findAll();
        Owner owner= list.stream().filter(i -> i.getId().equals(id))
                .findFirst().orElse(null);
        if(owner!=null){
            owner.setUpdated(new Date());
            owner.addBuilding(dtoBuilding.toBuilding());
            repoOwner.save(owner);
            log.info("IN addBuildingToOwnerById - building added");
        } else {
            log.info("IN addBuildingToOwnerById - owner not found");
        }
    }

    @Override
    public void changeBuildingOwner(Building building, Owner owner) {
        Owner lastOwner =building.getOwner();
        building.setOwner(owner);
        repoBuilding.save(building);
        lastOwner.deleteBuilding(building);
        lastOwner.calculateTax();
        repoOwner.save(lastOwner);
        owner.calculateTax();
        repoOwner.save(owner);
    }

}
