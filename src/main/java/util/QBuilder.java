package util;

public interface QBuilder {
    void setNameTable(String nameTable);

    void setWhere(String column);

    void setOrderBy(String column);

    void setDirect(Order order);

    void setLimit(String limit);

    void setOffSet(String offSet);

}

enum Order {
    ASC("ASC"),
    DESC("DESC");
    private String order;

    Order(String order) {
        this.order = order;
    }
}

