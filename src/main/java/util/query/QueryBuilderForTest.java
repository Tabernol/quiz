package util.query;

public class QueryBuilderForTest extends QueryBuilder {

    @Override
    public String getQuery(String filter, String order, Integer limit, Integer page, String role) {
        if (filter.equals("all")) {
            filter = "";
        } else {
            filter = "where subject like '" + filter + "' ";
        }

        String and = "";
        if (role.equals("student")){
            if(filter.isEmpty()){
                and = "where status not like 'blocked' ";
            }
            else {
                and = "and status not like 'blocked' ";
            }
        }


        order = "order by " + order;

        int offSet = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);


        return "select * from test " + filter + and + order + " limit " + limit + " offset " + offSet;
    }
}
