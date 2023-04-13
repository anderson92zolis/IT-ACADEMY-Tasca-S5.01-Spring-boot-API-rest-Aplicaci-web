package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sucursal")
public class Branch {


    //Llave primaria de la tabla
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

    public Branch(Integer pk_BranchID, String branchName, String branchCountry) {
        this.pk_BranchID = pk_BranchID;
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