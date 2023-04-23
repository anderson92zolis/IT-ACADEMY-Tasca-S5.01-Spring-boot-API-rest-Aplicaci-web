package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flowers")
@ApiModel(description = "Details of a Flower")
public class FlowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID unique of a Flower")
    private int pk_FlorID;


    @Column(name= "nameFlower")
    @ApiModelProperty(notes = "Name of a flower")
    private String nameFlower;

    @Column(name= "countryFlower")
    @ApiModelProperty(notes = "Country of a flower")
    private String countryFlower;

    public FlowerEntity(String nameFlower, String countryFlower){
        super();
        this.nameFlower=nameFlower;
        this.countryFlower=countryFlower;
    }

}
