package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


public class BranchDto implements Serializable {


    // Llave primaria de la tabla
    private Integer pk_BranchID;

    private String branchName;

    private String branchCountry;

    private String branchType;

    // Define an array of EU member countries
    private final List<String> COUNTRIES_EU =  Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark",
            "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy",
            "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal",
            "Romania", "Slovakia", "Slovenia", "Spain", "Sweden");

    // constructor
    public BranchDto() {
    }

    public BranchDto(String branchName, String branchCountry) {
        this.branchName = branchName;
        this.branchCountry = branchCountry;
        this.branchType= setBranchType();

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
    public String getBranchType() {
        return branchType;
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

    // METHOD TO KNOW IF COUNTRY IN PART OF EU

    public String setBranchType(){
        if (isInEU()){
            this.branchType= "EU";
        } else {
            this.branchType= "outside EU";
        };
        return this.branchType;
    }

    public Boolean isInEU() {
        // Check if the given country is in the array of EU member countries
        return (COUNTRIES_EU.contains(branchCountry));
    }

    @Override
    public String toString() {
        return "BranchDto{" +
                "pk_BranchID=" + pk_BranchID +
                ", branchName='" + branchName + '\'' +
                ", branchCountry='" + branchCountry + '\'' +
                ", branchType='" + branchType + '\'' +
                '}';
    }

}
