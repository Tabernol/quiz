package util.query;

public class QueryBuilderForTest extends QueryBuilder {

    @Override
    public String getQuery(String filter, String order, Integer limit, Integer offSet) {
        if (filter.equals("all")) {
            filter = "";
        } else {
            filter = "where subject like '" + filter + "' ";
        }

        order = "order by " + order;

        offSet = (Integer.valueOf(offSet) - 1) * Integer.valueOf(limit);


        return "select * from test " + filter + order + " limit " + limit + " offset " + offSet;
    }
}
