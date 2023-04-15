package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto;

import java.util.Arrays;
import java.util.Objects;


public class BranchDto {


    // Llave primaria de la tabla
    private Integer pk_BranchID;

    private String branchName;

    private String branchCountry;


    private String branchType;

    // constructor
    public BranchDto() {
    }

    public BranchDto(String branchName, String branchCountry) {
        this.branchName = branchName;
        this.branchCountry = branchCountry;
    }

    // getters

    public Integer getPk_BranchID() {
        return pk_BranchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    //setter


    public void setPk_BranchID(Integer pk_BranchID) {
        this.pk_BranchID = pk_BranchID;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setBranchCountry(String branchCountry) {
        this.branchCountry = branchCountry;
    }
    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    // METHOD TO KNOW IF COUNTRY IN PART OF EU

    public void isCountryInEU(String country) {

        // Define an array of EU member countries

        String[] euCountries = {"Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark",
                "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy",
                "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal",
                "Romania", "Slovakia", "Slovenia", "Spain", "Sweden"};

        // Check if the given country is in the array of EU member countries
        String outsideOfEu= "Outside of Eu";
        boolean isInsideEU = Arrays.stream(euCountries).
                anyMatch(c -> c.equalsIgnoreCase(country));
        if (isInsideEU = true){
            setBranchType("EU");
        } else {
            setBranchType(outsideOfEu);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BranchDto branchDto)) return false;
        return Objects.equals(pk_BranchID, branchDto.pk_BranchID) && Objects.equals(branchName, branchDto.branchName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk_BranchID, branchName);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "pk_BranchID=" + pk_BranchID +
                ", branchName='" + branchName + '\'' +
                ", branchCountry='" + branchCountry + '\'' +
                '}';
    }

}
