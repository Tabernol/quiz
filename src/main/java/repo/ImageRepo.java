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
    int addImage(Image image) throws DataBaseException;

    List<Image> getAll() throws DataBaseException;

    int deleteImage(String publicId) throws DataBaseException;

    List<String> canDeleteImage(String publicId);


}
