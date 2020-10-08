package test.vitasoft.vitasoft_test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reports")
public class Report implements Serializable, Comparable<Report> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private Date date;
    private String text;

    public Report() {}

    public Report(User user, Status status, Date date, String text) {
        this.user = user;
        this.status = status;
        this.date = date;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long reportId) {
        this.id = reportId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
//                ", user=" + user +
//                ", status=" + status +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public int compareTo(Report report) {
        return this.getDate().compareTo(report.getDate());
    }
}
