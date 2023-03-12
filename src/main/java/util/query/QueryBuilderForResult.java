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

        if (and.equals("all")) {
            and = "";
        } else
            and = " and subject like '" + and + "'";

        return "select * from test inner join result " +
                "on test.id=result.test_id where user_id like "
                + filter + and + " order by " + orderBy + " limit " + limit + " offset " + offSet;
    }
}

