package lt.estate.app.dto;

import lombok.Data;
import lt.estate.app.enums.Status;
import lt.estate.app.model.Owner;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class DtoOwner {
    @NotNull
    @Length(min=1,max=120)
    private String name;
    @NotNull
    @Length(min=1,max=120)
    private String surname;
    @NotNull
    @Length(min=1,max=120)
    private String address;
    @NotNull
    private String telephone;

    public Owner toOwner() {
        Owner owner =new Owner();
        owner.setCreated(new Date());
        owner.setUpdated(new Date());
        owner.setName(name);
        owner.setSurname(surname);
        owner.setAddress(address);
        owner.setTelephone(telephone);
        owner.setStatus(Status.ACTIVE);
        return owner;
    }
}
