package util.query;

public class QueryBuilderForTest implements QBuilder {
    private String filter;
    private String order;
    private Integer limit;
    private String offSet;

    @Override
    public String getQuery() {
        return "select * from test " + filter + order + " limit " + limit + " offset " + offSet;
    }

    @Override
    public void setFilter(String name) {
        if (name.equals("all")) {
            this.filter = "";
        } else {
            this.filter = "where subject like '" + name + "' ";
        }

    }

    @Override
    public void setOrderBy(String column) {
        this.order = "order by " + column;

    }

    @Override
    public void setLimit(Integer rows) {
        this.limit = rows;
    }

    @Override
    public void setOffSet(Integer numberOfPage) {
        Integer off = (Integer.valueOf(numberOfPage) - 1) * Integer.valueOf(limit);
        this.offSet = off.toString();
    }
}
