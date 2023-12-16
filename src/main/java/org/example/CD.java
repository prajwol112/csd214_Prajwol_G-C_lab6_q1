package org.example;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CD {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "CD_NAME")
    private String cdName;
    @Basic
    @Column(name = "BRAND_NAME")
    private String brandName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCdName() {
        return cdName;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CD cd = (CD) o;
        return id == cd.id && Objects.equals(cdName, cd.cdName) && Objects.equals(brandName, cd.brandName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cdName, brandName);
    }
}
