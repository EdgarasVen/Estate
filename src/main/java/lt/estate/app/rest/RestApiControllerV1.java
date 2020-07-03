package lt.estate.app.rest;

import lt.estate.app.dto.DtoBuilding;
import lt.estate.app.dto.DtoOwner;
import lt.estate.app.model.Building;
import lt.estate.app.model.Owner;
import lt.estate.app.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableSwagger2
@RestController
@RequestMapping(value = "/api/v1/")
public class RestApiControllerV1 {

    private final EstateService service;

    @Autowired
    public RestApiControllerV1(EstateService service) {
        this.service = service;
    }

    @GetMapping("owners")
    public ResponseEntity<Map<Object, Object>> getAllOwners(){
        Map<Object, Object> response = new HashMap<>();
        List<Owner> owners = service.getAllOwners();
        response.put("owners",owners);
        response.put("size",owners.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("buildings")
    public ResponseEntity<Map<Object, Object>> getAllBuildings(){
        Map<Object, Object> response = new HashMap<>();
        List<Building> buildings = service.getAllBuildings();
        response.put("buildings",buildings);
        response.put("size",buildings.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("owners/{id}")
    public ResponseEntity<Map<Object, Object>> getOwnerById(@PathVariable Long id){
        Map<Object, Object> response = new HashMap<>();
        Owner owner = service.findOwnerById(id);
        if(owner!=null){
            response.put("owner",owner);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("buildings/{id}")
    public ResponseEntity<Map<Object, Object>> getBuildingById(@PathVariable Long id){
        Map<Object, Object> response = new HashMap<>();
        Building building = service.findBuildingById(id);
        if(building!=null){
            response.put("building",building);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("buildings")
    public ResponseEntity<Map<Object, Object>> createBuilding(@RequestBody final @Valid DtoBuilding dtoBuilding){
        Map<Object, Object> response = new HashMap<>();
        Building building =dtoBuilding.toBuilding();
        service.createBuilding(building);
        response.put("building",building);
        return ResponseEntity.ok(response);
    }

    @PostMapping("owners")
    public ResponseEntity<Map<Object, Object>> createOwner(@RequestBody final @Valid DtoOwner dtoOwner){
        Map<Object, Object> response = new HashMap<>();
        Owner owner =dtoOwner.toOwner();
        service.createOwner(owner);
        response.put("owner",owner);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping ("buildings/{id}")
    public ResponseEntity<Map<Object, Object>> deleteBuildingById(@PathVariable final Long id){
        Map<Object, Object> response = new HashMap<>();
        Building building = service.findBuildingById(id);
        if(building!=null){
            service.deleteBuildingById(id,building);
            response.put("building",building);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("owners/{id}")
    public ResponseEntity<Map<Object, Object>> deleteOwnerById(@PathVariable final Long id){
        Map<Object, Object> response = new HashMap<>();
        Owner owner = service.findOwnerById(id);
        if(owner!=null){
            service.deleteOwnerById(id,owner);
            response.put("owner",owner);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping ("buildings/{id}")
    public ResponseEntity<Map<Object, Object>> updateBuildingById(
            @PathVariable final Long id,
            @RequestBody final @Valid DtoBuilding dtoBuilding){
        Map<Object, Object> response = new HashMap<>();
        Building building = service.findBuildingById(id);
        if(building==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            service.updateBuilding(id,dtoBuilding.toBuilding(),building);
            response.put("building",building);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping ("owners/{id}")
    public ResponseEntity<Map<Object, Object>> updateOwnerById(
            @PathVariable final Long id,
            @RequestBody final @Valid DtoOwner dtoOwner){
        Map<Object, Object> response = new HashMap<>();
        Owner owner = service.findOwnerById(id);
        if(owner==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            service.updateOwner(id,dtoOwner.toOwner(),owner);
            response.put("owner",owner);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping ("buildings/owner/{id}")
    public ResponseEntity<Map<Object, Object>> createBuildingAndAddToOwnerById(
            @PathVariable final Long id,
            @RequestBody final @Valid DtoBuilding dtoBuilding){
        Map<Object, Object> response = new HashMap<>();
        service.createBuildingAndAddToOwnerById(id,dtoBuilding);
        return ResponseEntity.ok(response);
    }

    @PutMapping ("buildings/{building}/owner/{owner}")
    public ResponseEntity<Map<Object, Object>> changeBuildingOwner(
            @PathVariable("building") final Long buildingId,
            @PathVariable("owner") final Long ownerId){
        Building building=service.findBuildingById(buildingId);
        Owner owner=service.findOwnerById(ownerId);
        if(building!=null && owner!=null){
            service.changeBuildingOwner(building,owner);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
