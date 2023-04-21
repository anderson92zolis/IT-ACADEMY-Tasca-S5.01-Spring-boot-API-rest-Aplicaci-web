package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flowers")
public class FlowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk_FlorID;

    @Column(name= "nameFlower")
    private String nameFlower;

    @Column(name= "countryFlower")
    private String countryFlower;

    public FlowerEntity(String nameFlower, String countryFlower){
        super();
        this.nameFlower=nameFlower;
        this.countryFlower=countryFlower;
    }

}
