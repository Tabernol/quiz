package util;

public class Query {
    private String nameTable;
    private String where;
    private String order;
    private Order direction;
    private String limit;
    private String offSet;

    public Query(String nameTable, String where, String order, Order direction, String limit, String offSet) {
        this.nameTable = nameTable;
        this.where = where;
        this.order = order;
        this.direction = direction;
        this.limit = limit;
        this.offSet = offSet;
    }
}
