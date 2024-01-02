package fr.dawid.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostalCode {
    private long id;
    private String code;
    private long cityId;

    public String getCode() {
        return this.code;
    }
}
