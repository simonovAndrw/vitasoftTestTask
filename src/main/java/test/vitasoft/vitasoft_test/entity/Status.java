package test.vitasoft.vitasoft_test.entity;

public enum Status {
    DRAFT("reports:draft"),
    SENT("reports:sent"),
    ACCEPTED("reports:accepted"),
    DECLINED("reports:declined");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
