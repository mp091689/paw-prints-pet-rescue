package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pet;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPetDao implements PetDao{
    private final String PET_SELECT ="SELECT pet_id, species_id, name, age, color, " +
            "has_special_needs, breed, size, gender, description, is_fixed, " +
            "is_adopted FROM pets";
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

    @Override
    public Pet getPetById(int petId) {
        Pet aPet = null;
        String sql = PET_SELECT + " WHERE pet_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId);
            if (results.next()) {
                aPet = mapRowToPet(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return aPet;
    }
    @Override
    public Pet createPet(Pet aPet) {
        String sql = "INSERT INTO pets (species_id, name, age, color, has_special_needs, breed, " +
                "size, gender, description, is_fixed, is_adopted) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?) RETURNING pet_id;";
        try {
            int id = jdbcTemplate.queryForObject(
                    sql, int.class, aPet.getSpeciesId(), aPet.getName(), aPet.getAge(),
                    aPet.getColor(), aPet.getHasSpecialNeed(), aPet.getBreed(),
                    aPet.getSize(), aPet.getGender(), aPet.getDescription(), aPet.getIsFixed(),
                    aPet.getIsAdopted()
            );

            aPet = getPetById(id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return aPet;
    }

    @Override
    public Pet updatePet(Pet aPet) {
        Pet updatedPet = null;
        String sql = "UPDATE pets SET species_id = ?, name = ?, age = ?, " +
                "has_special_needs = ?, color = ?, breed = ?, size = ?, gender = ?, " +
                "description = ?, is_fixed = ?, is_adopted = ?  WHERE pet_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(
                    sql, aPet.getSpeciesId(), aPet.getName(), aPet.getAge(), aPet.getHasSpecialNeed(),
                    aPet.getColor(), aPet.getBreed(), aPet.getSize(), aPet.getGender(),
                    aPet.getDescription(), aPet.getIsFixed(), aPet.getIsAdopted(), aPet.getPetId()
            );

            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedPet = getPetById(aPet.getPetId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedPet;
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
