package util.query;

public class QueryBuilderForResult implements QBuilder {
    private String filter;
    private String orderBy;
    private Integer limit;
    private Integer offSet;
    private String andSubject;

    @Override
    public String getQuery() {
        return "select * from test inner join result " +
                "on test.id=result.test_id where user_id like "
                + filter + andSubject + " order by " + orderBy + " limit " + limit + " offset " + offSet;
    }


    @Override
    public void setFilter(String idUser) {
        this.filter = idUser;
    }

    @Override
    public void setOrderBy(String column) {
        this.orderBy = column;

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

    public void setAndSubject(String andSubject) {
        if (andSubject.equals("all")) {
            this.andSubject = "";
        } else
            this.andSubject = " and subject like '" + andSubject + "'";
    }
}

