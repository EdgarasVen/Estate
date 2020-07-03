package lt.estate.app.dto;

import lombok.Data;
import lt.estate.app.enums.PropertyType;
import lt.estate.app.enums.Status;
import lt.estate.app.model.Building;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class DtoBuilding {
    @NotNull
    @Length(min=1,max=120)
    private String address;
    @NotNull
    private double size;
    @NotNull
    private Long value;
    private PropertyType type;

    public Building toBuilding() {
        Building building= new Building();
        building.setCreated(new Date());
        building.setUpdated(new Date());
        building.setAddress(address);
        building.setType(type);
        building.setValue(value);
        building.setSize(size);
        building.setOwner(null);
        building.setStatus(Status.ACTIVE);
        return building;
    }
}
