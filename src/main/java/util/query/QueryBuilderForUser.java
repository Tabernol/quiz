package util.query;

public class QueryBuilderForUser implements QueryCreator {

//    @Override
//    public String getQuery(String filter, String order, Integer limit, Integer page, String role) {
//        if (filter.equals("all")) {
//            filter = "";
//        } else if (filter.equals("true")) {
//            filter = "where is_blocked like 1";
//        } else filter = "where is_blocked like 0";
//
//        int offSet = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
//
//        return "select * from user " + filter + " order by " + order + " limit " + limit + " offset " + offSet;
//    }

    @Override
    public String getSQL(MyQuery query) {
        String filter = query.getFilter();
        String orderBy = query.getOrderBy();
        Integer limit = query.getLimit();
        Integer page = query.getPage();

        if (filter.equals("all")) {
            filter = "";
        } else if (filter.equals("true")) {
            filter = "where is_blocked like 1";
        } else filter = "where is_blocked like 0";

        int offSet = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);

        return "select * from user " + filter + " order by " + orderBy + " limit " + limit + " offset " + offSet;
    }
}
