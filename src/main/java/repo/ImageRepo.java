package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import models.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ImageRepo {
    public int addImage(String publicId, String url, Integer width, Integer height) throws DataBaseException;

    public List<Image> getAll() throws DataBaseException;

    public int deleteImage(String publicId) throws DataBaseException;

    public List<String> canDeleteImage(String publicId);
}
