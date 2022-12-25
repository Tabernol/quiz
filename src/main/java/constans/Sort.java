package constans;

public enum Sort {
    nameAsc("name asc"),
    nameDesc("name desc"),
    difficultAsc("difficult asc"),
    difficultDesc("difficult desc");

    Sort(String order) {
        this.order = order;
    }

    private String order;

    public String getOrder() {
        return order;
    }
}
