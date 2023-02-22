package util.query;

public class QueryBuilderForUser extends QueryBuilder {

    @Override
    public String getQuery(String filter, String order, Integer limit, Integer offSet) {
        if (filter.equals("all")) {
            filter = "";
        } else if (filter.equals("true")) {
            filter = "where is_blocked like 1";
        } else filter = "where is_blocked like 0";

        offSet = (Integer.valueOf(offSet) - 1) * Integer.valueOf(limit);

        return "select * from user " + filter + " order by " + order + " limit " + limit + " offset " + offSet;
    }
}
