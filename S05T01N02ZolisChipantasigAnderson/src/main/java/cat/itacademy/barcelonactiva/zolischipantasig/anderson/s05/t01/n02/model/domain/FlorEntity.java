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
public class FlorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk_FlorID;

    @Column(name= "nameFlower")
    private String nomFlor;

    @Column(name= "countryFlower")
    private String paisFlor;

    public FlorEntity(String nomFlor, String paisFlor){
        this.nomFlor=nomFlor;
        this.paisFlor=paisFlor;
    }

}
