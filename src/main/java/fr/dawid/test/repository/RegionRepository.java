package fr.dawid.test.repository;

import fr.dawid.test.entity.Region;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionRepository extends AbstractRepository<Region> {

    public RegionRepository() {
        super("region");
    }

    @Override
    protected Region getObject(ResultSet rs) {
        Region region = new Region();
        try {
            region.setId(rs.getLong("id"));
            region.setName(rs.getString("name"));
            region.setCode(rs.getString("code"));
        } catch (SQLException e) {
            System.out.println("Something goes wrong while creating a Region object : " + e.getMessage());
            region = null;
        }
        return region;
    }
}
