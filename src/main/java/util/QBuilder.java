package util;

public interface QBuilder {
//    void setNameTable(String nameTable);

//    void setWhere(String column);
    String getQuery();

    void setFilter(String name);

    void setOrderBy(String column);

//    void setDirect(Order order);

    void setLimit(Integer limit);

    void setOffSet(Integer offSet);

}

enum Order {
    ASC("ASC"),
    DESC("DESC");
    private String order;

    Order(String order) {
        this.order = order;
    }
}

