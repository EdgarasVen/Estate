package lt.estate.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lt.estate.app.enums.PropertyType;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Building extends BaseEntity{

    @Column(name = "name")
    private String address;

    @Column(name = "size")
    private double size;

    @Column(name = "value")
    private Long value;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;

}
