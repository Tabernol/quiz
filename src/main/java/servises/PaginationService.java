package servises;

import exeptions.DataBaseException;
import models.Test;
import repo.TestRepo;
import util.MyTable;
import util.QBuilder;
import util.QueryBuilderForTest;
import util.QueryFactory;

import java.util.List;

public class PaginationService {
    private TestRepo testRepo;


    public PaginationService(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

//    public List<Test> nextPage(MyTable table, String subject, String order, Integer rows, Integer numberOfPage) throws DataBaseException {
//        QueryFactory queryFactory = new QueryFactory();
//        QBuilder qBuilder = queryFactory.getQueryBuilder(table);
//        qBuilder.setFilter(subject);
//        qBuilder.setOrderBy(order);
//        qBuilder.setLimit(rows);
//        qBuilder.setOffSet(1);
//        String query = qBuilder.getQuery();
//       return testRepo.nextPage(query);
//
////        return testRepo.nextPage(subject, order, rows, numberOfPage);
//    }

//    public List<Test> nextPage(String order, Integer rows, Integer numberOfPage) throws DataBaseException {
//        return testRepo.nextPage(order, rows, numberOfPage);
//    }


}
