package lt.estate.app.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Owner extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "property_tax")
    private double tax;

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER)
    List<Building> buildings;

    public void addBuilding(Building building){
        building.setOwner(this);
        buildings.add(building);
        calculateTax();
    }

    public void deleteBuilding(Building building){
        buildings.remove(building);
        calculateTax();
    }

    public void calculateTax(){
        double tax=0;
        if(buildings!=null ){
            for (Building b:buildings
            ) {
                double percent=b.getType().taxRate;
                double result=b.getValue()*percent/100;
                tax+=result;
            }
            this.tax=tax;
        }

    }
}
