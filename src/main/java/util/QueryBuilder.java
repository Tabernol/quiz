package util;

public class QueryBuilder implements QBuilder{
    private String select = "SELECT * from ";
    private String tableName;
    private String where = " where";
    private String filter;
    private String orderBy = " order by";
    private String order;

    public String getQuery(){
        return null;
    }

    @Override
    public void setNameTable(String nameTable) {

    }

    @Override
    public void setWhere(String column) {

    }

    @Override
    public void setOrderBy(String column) {

    }

    @Override
    public void setDirect(Order order) {

    }

    @Override
    public void setLimit(String limit) {

    }

    @Override
    public void setOffSet(String offSet) {

    }
}
