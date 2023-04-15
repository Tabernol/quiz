package util.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.User;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
