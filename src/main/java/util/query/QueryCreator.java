package util.query;

public interface QueryCreator {
    String getSQL(MyQuery query);

    String ALL = "all";
    String EMPTY = "";
    String TRUE = "true";
    String STUDENT = "student";

    String WHERE_IS_BLOCKED_LIKE_1 = "where is_blocked like 1";
    String WHERE_IS_BLOCKED_LIKE_0 = "where is_blocked like 0";

    String SELECT_FROM_USER = "select * from user ";
    String SELECT_FROM_TEST = "select * from test ";

    String SELECT_FROM_TEST_INNER_JOIN_RESULT =
            "select * from test inner join result on test.id=result.test_id where user_id like ";
    String LIMIT = " limit ";
    String ORDER_BY = " order by ";
    String OFFSET = " offset ";
    String WHERE_SUBJECT_LIKE = "where subject like ";

    String WHERE_STATUS_NOT_LIKE_BLOCKED = " where status not like 'blocked' ";
    String AND_STATUS_NOT_LIKE_BLOCKED = " and status not like 'blocked' ";
    String AND_SUBJECT_LIKE = " and subject like ";
}
