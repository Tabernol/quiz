package servises;

import dao.impl.TestDao;
import models.Test;

import java.util.List;

public class TestService {
    TestDao testDao = new TestDao();
     public List<Test> getAll(){
         return testDao.getAll();
     }
}
