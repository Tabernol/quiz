package util.query;

import models.User;

public class QueryBuilderForTest implements QueryCreator {

//    @Override
//    public String getQuery(String filter, String order, Integer limit, Integer page, String role) {
//        if (filter.equals("all")) {
//            filter = "";
//        } else {
//            filter = "where subject like '" + filter + "' ";
//        }
//        String and = "";
//        if (role.equals("student")){
//            if(filter.isEmpty()){
//                and = "where status not like 'blocked' ";
//            }
//            else {
//                and = "and status not like 'blocked' ";
//            }
//        }
//        order = "order by " + order;
//        int offSet = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
//        return "select * from test " + filter + and + order + " limit " + limit + " offset " + offSet;
//    }

    @Override
    public String getSQL(MyQuery query) {
        String filter = query.getFilter();
        String orderBy = query.getOrderBy();
        Integer limit = query.getLimit();
        Integer page = query.getPage();
        String role = query.getRole();

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
        orderBy = "order by " + orderBy;
        int offSet = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);

        return "select * from test " + filter + and + orderBy + " limit " + limit + " offset " + offSet;
    }
}
