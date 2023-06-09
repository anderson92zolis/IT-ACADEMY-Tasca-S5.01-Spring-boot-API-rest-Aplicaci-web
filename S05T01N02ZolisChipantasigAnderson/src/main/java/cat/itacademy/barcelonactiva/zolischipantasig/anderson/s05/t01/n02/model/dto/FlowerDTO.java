package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO {

    private int pk_FlorID;

    private String nameFlower;

    private String countryFlower;

    private String flowerType;

    @JsonIgnore
    private List<String> COUNTRIES_EU =  Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark",
            "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy",
            "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal",
            "Romania", "Slovakia", "Slovenia", "Spain", "Sweden");

    public FlowerDTO(String nameFlower, String countryFlower){
        this.nameFlower=nameFlower;
        this.countryFlower=countryFlower;
    }

    // getter & setter because a problem with lombok
    public String getNameFlower() {
        return nameFlower;
    }

    public String getCountryFlower() {
        return countryFlower;
    }



    public void setNameFlower(String nameFlower) {
        this.nameFlower = nameFlower;
    }

    public void setCountryFlower(String countryFlower) {
        this.countryFlower = countryFlower;
        this.flowerType = COUNTRIES_EU.stream().anyMatch(c -> c.equalsIgnoreCase(countryFlower)) ? "EU" : "NOT EU";
    }

}



