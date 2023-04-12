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
    int addImage(String publicId, String url, Integer width, Integer height) throws DataBaseException;

    List<Image> getAll() throws DataBaseException;

    int deleteImage(String publicId) throws DataBaseException;

    List<String> canDeleteImage(String publicId);

    String INSERT_IMAGE = "INSERT INTO image values(default, ?, ?, ?, ?)";

    String GET_ALL = "select * from image";

    String DELETE_IMAGE = "delete from image where public_id like ?";

    String CAN_DELETE_THIS_IMAGE = "select name from question" +
            " inner join test on test.id = question.test_id \n" +
            " inner join image on question.url = image.url\n" +
            " where public_id like ?";
}
