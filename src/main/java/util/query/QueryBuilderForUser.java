package util.query;

public class QueryBuilderForUser implements QBuilder {

    private String filter;
    private String order;
    private Integer limit;
    private Integer offSet;

    @Override
    public String getQuery() {
        return "select * from user " + filter + " order by " + order + " limit " + limit + " offset " + offSet;
    }

    @Override
    public void setFilter(String name) {
        if (name.equals("all")) {
            this.filter = "";
        } else if (name.equals("true")) {
            this.filter = "where is_blocked like 1";
        } else this.filter = "where is_blocked like 0";
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
}
