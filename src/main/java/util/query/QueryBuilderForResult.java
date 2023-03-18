package util.query;

public class QueryBuilderForResult implements QueryCreator {
    @Override
    public String getSQL(MyQuery query) {
        String filter = query.getFilter();
        String orderBy = query.getOrderBy();
        Integer limit = query.getLimit();
        Integer page = query.getPage();
        String and = query.getAnd();

        Integer offSet = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);

        if (and.equals(ALL)) {
            and = EMPTY;
        } else
            and = AND_SUBJECT_LIKE + "'" + and + "'";

        return SELECT_FROM_TEST_INNER_JOIN_RESULT
                + filter + and + ORDER_BY + orderBy + LIMIT + limit + OFFSET + offSet;
    }
}

