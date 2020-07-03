package lt.estate.app.service;

import lt.estate.app.model.Building;
import lt.estate.app.model.Owner;
import lt.estate.app.repo.RepoBuilding;
import lt.estate.app.repo.RepoOwner;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EstateServiceImpTest {
    @Autowired
    private EstateServiceImp serviceImp;

    @MockBean
    private RepoBuilding repoBuilding;

    @MockBean
    private RepoOwner repoOwner;

    private Owner owner1;
    private Owner owner2;
    private Building building1;
    private Building building2;

    @Before
    public void init() {
        owner1 =new Owner();
        owner1.setId(1L);
        owner2 =new Owner();
        owner2.setId(2L);
        Mockito.when(repoOwner.findAll()).thenReturn(
                Stream.of(owner1,owner2)
                        .collect(Collectors.toList())
        );

        building1=new Building();
        building1.setId(1L);
        building2=new Building();
        building2.setId(2L);
        Mockito.when(repoBuilding.findAll()).thenReturn(
                Stream.of(building1,building2)
                        .collect(Collectors.toList())
        );
    }

    @Test
    void createBuilding() {
        Building building=new Building();
        serviceImp.createBuilding(building);
        Mockito.verify(repoBuilding,Mockito.times(1)).save(building);
    }

    @Test
    void createOwner() {
        Owner owner=new Owner();
        serviceImp.createOwner(owner);
        Mockito.verify(repoOwner,Mockito.times(1)).save(owner);
    }

    @Test
    void getAllOwners() {
        Mockito.when(repoOwner.findAll()).thenReturn(
                Stream.of(owner1,owner2)
                        .collect(Collectors.toList())
        );
        serviceImp.getAllOwners();
        assertEquals(2,serviceImp.getAllOwners().size());
    }

    @Test
    void getAllBuildings() {
        Mockito.when(repoBuilding.findAll()).thenReturn(
                Stream.of(building1,building2)
                        .collect(Collectors.toList())
        );
        serviceImp.getAllBuildings();
        assertEquals(2,serviceImp.getAllBuildings().size());
    }

    @Test
    void findBuildingById() {
        assertEquals(building1,serviceImp.findBuildingById(1L));
    }

    @Test
    void findOwnerById() {
        assertEquals(owner1,serviceImp.findOwnerById(1L));
    }

    @Test
    void deleteOwnerById() {
        Owner owner=new Owner();
        serviceImp.deleteOwnerById(1L,owner);
        Mockito.verify(repoOwner, Mockito.times(1)).deleteById(1L);
    }

}