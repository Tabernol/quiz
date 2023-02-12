package util.query;

import util.MyTable;

public class QueryFactory {

    public QBuilder getQueryBuilder(MyTable table){
        QBuilder qBuilder = null;
        switch (table) {
            case TEST:
                qBuilder = new QueryBuilderForTest();
                break;
            case USER:
                qBuilder = new QueryBuilderForUser();
                break;
            case RESULT:
                qBuilder = new QueryBuilderForResult();
                break;
            default:
                throw new IllegalArgumentException("Wrong type table: " + table);
        }
        return qBuilder;
    }
}

