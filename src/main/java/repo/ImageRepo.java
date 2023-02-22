package repo;

import connection.MyDataSource;
import dto.ResultDto;
import exeptions.DataBaseException;
import models.Image;
import models.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageRepo {
    Logger logger = LogManager.getLogger(ImageRepo.class);

    //    public int addImage(Long id, Long questionId, String publicId, String url, Integer width, Integer height) throws DataBaseException {
//        String sql = "INSERT INTO image " +
//                "(id, question_id, public_id, url, width, height) values(default, ?, ?, ?, ?, ?)";
//        try (Connection con = MyDataSource.getConnection();
//             PreparedStatement pst = con.prepareStatement(sql)) {
//            pst.setLong(1, questionId);
//            pst.setString(2, publicId);
//            pst.setString(3, url);
//            pst.setInt(4, width);
//            pst.setInt(5, height);
//            return pst.executeUpdate();
//        } catch (SQLException e) {
//            logger.warn("Can not add image");
//            throw new DataBaseException("Can not add image" + e.getMessage(), e);
//        }
//    }
    public int addImage(String publicId, String url, Integer width, Integer height) throws DataBaseException {
        String sql = "INSERT INTO image values(default, ?, ?, ?, ?)";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, publicId);
            pst.setString(2, url);
            pst.setInt(3, width);
            pst.setInt(4, height);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can not add image");
            throw new DataBaseException("Can not add image" + e.getMessage(), e);
        }
    }

    public Image getImage(Long id) throws DataBaseException {
        String sql = "select * from image where id = ?";
        Image image = null;
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                image = new Image();
                image.setId(resultSet.getLong("id"));
                image.setQuestionId(resultSet.getLong("question_id"));
                image.setPublicId(resultSet.getString("public_id"));
                image.setUrl(resultSet.getString("url"));
                image.setWidth(resultSet.getInt("width"));
                image.setHeight(resultSet.getInt("height"));
            }
            resultSet.close();
            return image;
        } catch (SQLException e) {
            logger.warn("Can not get image");
            throw new DataBaseException("Can not get image" + e.getMessage(), e);
        }
    }

    public List<Image> getAll() throws DataBaseException {
        String sql = "select * from image";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            ResultSet resultSet = pst.executeQuery();
            List<Image> imageList = new ArrayList<>();
            while (resultSet.next()) {
                Image image = new Image();
                image.setId(resultSet.getLong("Image_id"));
                image.setPublicId(resultSet.getString("public_id"));
                image.setUrl(resultSet.getString("url"));
                image.setWidth(resultSet.getInt("width"));
                image.setHeight(resultSet.getInt("height"));
                imageList.add(image);
            }
            resultSet.close();
            return imageList;
        } catch (SQLException e) {
            logger.warn("Can not get order Image list");
            throw new DataBaseException("Can not get order Image list" + e.getMessage(), e);
        }
    }

    public int deleteImage(String publicId) throws DataBaseException {
        String sql = "delete from image where public_id like ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, publicId);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't delete Image ");
            throw new DataBaseException("Can't delete Image " + e.getMessage(), e);
        }
    }
}
