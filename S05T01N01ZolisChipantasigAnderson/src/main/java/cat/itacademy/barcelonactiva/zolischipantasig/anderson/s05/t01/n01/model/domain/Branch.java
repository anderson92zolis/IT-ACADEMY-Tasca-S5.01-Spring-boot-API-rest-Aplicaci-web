package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sucursal")
public class Branch {


    // Llave primaria de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk_BranchID;

    @Column(name = "branchName")
    private String branchName;
    @Column(name = "branchCountry")
    private String branchCountry;

    // cosntructor
    public Branch() {
    }

    public Branch(String branchName, String branchCountry) {
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

    // METHOD TO KNOW IF COUNTRY IN PART OF EU

    public static boolean isCountryInEU(String country) {
        // Define an array of EU member countries
        String[] euCountries = {"Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark",
                "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy",
                "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal",
                "Romania", "Slovakia", "Slovenia", "Spain", "Sweden"};

        // Check if the given country is in the array of EU member countries
        for (String euCountry : euCountries) {
            if (euCountry.equals(country)) {
                return true;
            }
        }

        // If the given country is not in the array of EU member countries, return false
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch sucursal)) return false;
        return Objects.equals(pk_BranchID, sucursal.pk_BranchID) && Objects.equals(branchName, sucursal.branchName);
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