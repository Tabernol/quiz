package util.query;

import util.MyTable;

abstract class QueryBuilder {

    abstract String getQuery(String filter, String order, Integer limit, Integer page, String role);
}
