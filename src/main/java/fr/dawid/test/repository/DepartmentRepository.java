package fr.dawid.test.repository;

import fr.dawid.test.entity.Department;
import fr.dawid.test.entity.Region;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentRepository extends AbstractRepository<Department> {

    public DepartmentRepository() {
        super("department");
    }

    @Override
    protected Department getObject(ResultSet rs) {
        Department department = new Department();
        try {
            department.setId(rs.getLong("id"));
            department.setRegionId(rs.getLong("region_id"));
            department.setName(rs.getString("name"));
            department.setCode(rs.getString("code"));
        } catch (SQLException e) {
            System.out.println("Something goes wrong while creating a Department object : " + e.getMessage());
            department = null;
        }
        return department;
    }

    public List<Department> findDepartmentsByRegion(Region region){
            StringBuilder select = new StringBuilder(getSelect());
            if (region != null && !region.isEmpty()) {
                select.append(" WHERE ");
                for (Map.Entry<String, Object> region : region.entrySet()) {
                    select.append(region.getKey()); // colonne de la table
                    select.append(" = '");
                    select.append(region.getValue()); // valeur recherchée
                    select.append("' AND ");
                }
                select.delete(select.length() - 5, select.length());
            }
            List<Department> objects = new ArrayList<>();
            String query = select.toString();

            System.out.println("Query called : " + query);
            if (tmpEntities.containsKey(query)) {
                queriesFromMap++;
                return tmpEntities.get(query);
            }

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while(rs.next()) {
                    objects.add(getObject(rs));
                }
                queriesFromDB++;
                tmpEntities.put(query, objects);
            } catch (SQLException e) {
                catchException(e, query);
            }
            return objects;
        }

    }

}
