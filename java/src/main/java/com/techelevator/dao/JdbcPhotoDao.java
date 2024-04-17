package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Photo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPhotoDao implements PhotoDao {
    private final String SELECT = "SELECT photo_id, file_name, pet_id FROM photos ";
    private final JdbcTemplate jdbcTemplate;

    public JdbcPhotoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Photo> getByPetId(int petId) {
        List<Photo> photos = new ArrayList<>();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(SELECT + " WHERE pet_id = ?", petId);

            while (results.next()) {
                Photo photo = mapRow(results);
                photos.add(photo);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return photos;
    }

    @Override
    public Photo getById(int id) {
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(SELECT + "WHERE photo_id = ?", id);
            if (results.next()) {
                return mapRow(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return null;
    }

    @Override
    public Photo createPhoto(Photo photo) {
        String sql = "INSERT INTO photos (file_name, pet_id) VALUES (?, ?) RETURNING photo_id";
        try {
            int id = jdbcTemplate.queryForObject(sql, int.class, photo.getFileName(), photo.getPetId());
            return getById(id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM photos WHERE photo_id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private Photo mapRow(SqlRowSet rs) {
        Photo photo = new Photo();
        photo.setPhotoId(rs.getInt("photo_id"));
        photo.setFileName(rs.getString("file_name"));
        photo.setPetId(rs.getInt("pet_id"));
        return photo;
    }
}
