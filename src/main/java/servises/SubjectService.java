package servises;

import dao.impl.SubjectDao;
import models.Subject;

import java.util.List;

public class SubjectService {
    SubjectDao subjectDao = new SubjectDao();
    public List<Subject> getAll(){
       return subjectDao.getAll();
    }
}
