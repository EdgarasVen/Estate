package lt.estate.app;

import lt.estate.app.dto.DtoBuilding;
import lt.estate.app.dto.DtoOwner;
import lt.estate.app.enums.PropertyType;
import lt.estate.app.rest.RestApiControllerV1;
import lt.estate.app.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AppApplication  implements CommandLineRunner {
	@Autowired
	RestApiControllerV1 controllerV1;
	@Autowired
	EstateService service;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		DtoOwner dtoOwner =new DtoOwner();
		dtoOwner.setName("Josh");
		dtoOwner.setSurname("Shepard");
		dtoOwner.setAddress("Some address 22");
		dtoOwner.setTelephone("893213244");

		DtoOwner dtoOwner2 =new DtoOwner();
		dtoOwner2.setName("Mike");
		dtoOwner2.setSurname("Shepard");
		dtoOwner2.setAddress("Some address 25");
		dtoOwner2.setTelephone("894332323");

		DtoBuilding dtoBuilding= new DtoBuilding();
		dtoBuilding.setAddress("Address 233");
		dtoBuilding.setSize(1100);
		dtoBuilding.setType(PropertyType.HOUSE);
		dtoBuilding.setValue(120000L);

		DtoBuilding dtoBuilding2= new DtoBuilding();
		dtoBuilding2.setAddress("Address 123");
		dtoBuilding2.setSize(1200);
		dtoBuilding2.setType(PropertyType.APARTMENT);
		dtoBuilding2.setValue(80000L);

		DtoBuilding dtoBuilding3= new DtoBuilding();
		dtoBuilding3.setAddress("Address 44");
		dtoBuilding3.setSize(1300);
		dtoBuilding3.setType(PropertyType.INDUSTRIAL);
		dtoBuilding3.setValue(1000000L);

		DtoBuilding dtoBuilding4= new DtoBuilding();
		dtoBuilding4.setAddress("Address 1");
		dtoBuilding4.setSize(1400);
		dtoBuilding4.setType(PropertyType.HOUSE);
		dtoBuilding4.setValue(220000L);

		controllerV1.createOwner(dtoOwner);
		controllerV1.createOwner(dtoOwner2);

		controllerV1.createBuildingAndAddToOwnerById(1L,dtoBuilding);
		controllerV1.createBuildingAndAddToOwnerById(1L,dtoBuilding2);
		controllerV1.createBuildingAndAddToOwnerById(2L,dtoBuilding3);
		controllerV1.createBuildingAndAddToOwnerById(2L,dtoBuilding4);

	}
}
