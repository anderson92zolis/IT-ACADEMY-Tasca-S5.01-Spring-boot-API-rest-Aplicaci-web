package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto;

import lombok.Data;

@Data
public class BranchDto {

    private Integer pk_BranchID;
    private String branchName;
    private String branchCountry;

    // METHOD TO KNOW IF COUNTRY IN PART OF EU

    public static boolean isCountryInEU(String country) {
        // Define an array of EU member countries
        String[] euCountries = {"Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark",
                "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy",
                "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal",
                "Romania", "Slovakia", "Slovenia", "Spain", "Sweden"};

        // Check if the given country is in the array of EU member countries
        for (String euCountry : euCountries) {
            if (euCountry.equalsIgnoreCase(country)) {
                return true;
            }
        }

        // If the given country is not in the array of EU member countries, return false
        return false;
    }

}
