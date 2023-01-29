package util;

public class QueryBuilderForResult implements QBuilder {
    private String filter = " ";
    private String order;
    private Integer limit;
    private Integer offSet;
    @Override
    public String getQuery() {
        return "select * from test inner join result " +
                "on test.id=result.test_id where user_id like "
                + filter + " order by " + order + " limit " + limit + " offset " + offSet;
    }


    @Override
    public void setFilter(String idUser) {
        this.filter = idUser;
    }

    @Override
    public void setOrderBy(String column) {
        this.order = column;

    }

    @Override
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public void setOffSet(Integer numberOfPage) {
        Integer off = (Integer.valueOf(numberOfPage) - 1) * Integer.valueOf(limit);
        this.offSet = off;
    }

//    public static void main(String[] args) {
//        QueryBuilderForResult queryBuilderForResult = new QueryBuilderForResult();
//        queryBuilderForResult.setFilter("9");
//        queryBuilderForResult.setOrderBy("name");
//        queryBuilderForResult.setLimit("5");
//        queryBuilderForResult.setOffSet("1");
//        String query = queryBuilderForResult.getQuery();
//        System.out.println(query);
//    }
}