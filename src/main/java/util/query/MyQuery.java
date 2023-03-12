package util.query;

import models.User;

public class MyQuery {
    private String filter;
    private String orderBy;
    private Integer limit;
    private Integer page;
    private String and;
    private String role;

    public MyQuery(String filter, String orderBy, Integer rows, Integer page, String role) {
        this.filter = filter;
        this.orderBy = orderBy;
        this.limit = rows;
        this.page = page;
        this.role = role;
    }

    public MyQuery(String filter, String orderBy, Integer rows, Integer page) {
        this.filter = filter;
        this.orderBy = orderBy;
        this.limit = rows;
        this.page = page;
    }

    public MyQuery() {
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getAnd() {
        return and;
    }

    public void setAnd(String and) {
        this.and = and;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
