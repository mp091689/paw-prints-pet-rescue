package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPetDao implements PetDao{
    private final String PET_SELECT ="SELECT pet_id, species_id, name, age, color, has_special_needs, " +
            "breed, size, gender, description, is_fixed, is_adopted FROM pets;";
    private JdbcTemplate jdbcTemplate;

    public JdbcPetDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<Pet> getPets() {
        List<Pet> pets = new ArrayList<>();
        String sqlGetPets = PET_SELECT;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPets);
        while (results.next()) {
            pets.add(mapRowToPet(results));
        }
        return pets;
    }
    private Pet mapRowToPet(SqlRowSet sqlRowSet) {
        Pet pet = new Pet();
        pet.setPetId(sqlRowSet.getInt("pet_id"));
        pet.setSpeciesId(sqlRowSet.getInt("species_id"));
        pet.setName(sqlRowSet.getString("name"));
        pet.setAge(sqlRowSet.getInt("age"));
        pet.setHasSpecialNeed(sqlRowSet.getBoolean("has_special_needs"));
        pet.setColor(sqlRowSet.getString("color"));
        pet.setBreed(sqlRowSet.getString("breed"));
        pet.setSize(sqlRowSet.getString("size"));
        pet.setGender(sqlRowSet.getString("gender"));
        pet.setIsFixed(sqlRowSet.getBoolean("is_fixed"));
        pet.setIsAdopted(sqlRowSet.getBoolean("is_adopted"));
        pet.setDescription(sqlRowSet.getString("description"));
        return pet;
    }
}
