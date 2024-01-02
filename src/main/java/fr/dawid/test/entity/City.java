package fr.dawid.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {
    private long id;
    private long departmentId;
    private String siren;
    private long population;
    private String name;
    private String code;
    public PostalCode zip;

    public String getPostalCode() {
        return zip.getCode();
    }

}
