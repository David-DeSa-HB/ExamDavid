package fr.dawid.test.repository;

import fr.dawid.test.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRepository extends AbstractRepository<City> {

    public CityRepository() {
        super("department");
    }

    @Override
    protected City getObject(ResultSet rs) {
        City city = new City();
        try {
            city.setId(rs.getLong("id"));
            city.setDepartmentId(rs.getLong("department_id"));
            city.setSiren(rs.getString("siren"));
            city.setPopulation(rs.getLong("population"));
            city.setName(rs.getString("name"));
            city.setCode(rs.getString("code"));
//            city.setZip(rs.getString("code"));

        } catch (SQLException e) {
            System.out.println("Something goes wrong while creating a City object : " + e.getMessage());
            city = null;
        }
        return city;
    }
}
