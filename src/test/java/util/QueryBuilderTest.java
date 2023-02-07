package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.query.QueryBuilderForTest;

public class QueryBuilderTest {

    QueryBuilderForTest queryBuilderForTest;
    @Test
    public void queryBuilderWithSubjectTest(){
        queryBuilderForTest = new QueryBuilderForTest();
        queryBuilderForTest.setFilter("filter");
        queryBuilderForTest.setLimit(2);
        queryBuilderForTest.setOrderBy("sub");
        queryBuilderForTest.setOffSet(5);
        Assertions.assertEquals(
                "select * from test where subject like 'filter' order by sub limit 2 offset 8",
                queryBuilderForTest.getQuery());
    }


    @Test
    public void queryBuilderwithoutSubjectTest(){
        queryBuilderForTest = new QueryBuilderForTest();
        queryBuilderForTest.setFilter("all");
        queryBuilderForTest.setLimit(2);
        queryBuilderForTest.setOrderBy("sub");
        queryBuilderForTest.setOffSet(5);
        Assertions.assertEquals(
                "select * from test where subject like 'filter' order by sub limit 2 offset 8",
                queryBuilderForTest.getQuery());
    }
}
