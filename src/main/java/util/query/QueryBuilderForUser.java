package util.query;

public class QueryBuilderForUser implements QueryCreator {


    @Override
    public String getSQL(MyQuery query) {
        String filter = query.getFilter();
        String orderBy = query.getOrderBy();
        Integer limit = query.getLimit();
        Integer page = query.getPage();

        if (filter.equals(ALL)) {
            filter = EMPTY;
        } else if (filter.equals(TRUE)) {
            filter = WHERE_IS_BLOCKED_LIKE_1;
        } else filter = WHERE_IS_BLOCKED_LIKE_0;

        int offSet = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);

        return SELECT_FROM_USER + filter + ORDER_BY + orderBy + LIMIT + limit + OFFSET + offSet;
    }
}
