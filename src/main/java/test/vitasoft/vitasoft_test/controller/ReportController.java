package test.vitasoft.vitasoft_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.vitasoft.vitasoft_test.entity.Report;
import test.vitasoft.vitasoft_test.entity.Role;
import test.vitasoft.vitasoft_test.entity.Status;
import test.vitasoft.vitasoft_test.entity.User;
import test.vitasoft.vitasoft_test.repository.ReportRepository;
import test.vitasoft.vitasoft_test.repository.UserRepository;

import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private UserRepository userRepository;
    private ReportRepository reportRepository;

    @Autowired
    public ReportController(UserRepository userRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    @GetMapping("/create")
    public String createReport(@RequestParam(name = "userId") Long userId,
                             @RequestParam(name = "isDraft") boolean isDraft,
                             @RequestParam(name = "text") String text) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("No such user"));

        Status status;
        if (isDraft)
            status = Status.DRAFT;
        else
            status = Status.SENT;

        if (isSingleRole(user.getRoles(), Role.USER)) {
            Report report = new Report(user, status, Calendar.getInstance(), text);
            reportRepository.save(report);
        } else {
            return "You have not got such privilege";
        }

        return "Report created";
    }

    @GetMapping("/modify")
    public String modifyReport(@RequestParam(name = "reportId") Long reportId,
                             @RequestParam(name = "userId") Long userId,
                             @RequestParam(name = "text") String text) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("No such user\n"));
        if (isSingleRole(user.getRoles(), Role.USER)) {
            Report report = reportRepository.findById(reportId).orElseThrow(() -> new UsernameNotFoundException("No such user\n"));

            if (report.getStatus() == Status.DRAFT) {
                report.setDate(Calendar.getInstance());
                report.setText(text);
                report.setStatus(Status.DRAFT);
            } else
                return "Report status is not Draft";

        } else
            return "You have not such permissions";

        return "Request modified";
    }

    @RequestMapping("/get")
    public String getReport(@RequestParam(name = "userId") Long userId,
                          @RequestParam(name = "reportId") Long reportId) {
        // there are 2 ways if gets by User or Operator
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("No such user"));
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new UsernameNotFoundException("No such user"));

        if (isSingleRole(user.getRoles(), Role.USER)) {
            return report.toString();
        } else if (isSingleRole(user.getRoles(), Role.OPERATOR)) {
            String text = report.getText();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                sb.append((text.charAt(i)));
                sb.append("-");
            }

            report.setText(sb.toString());

            return report.toString();
        } else {
            return "You have not got such permissions";
        }
    }

    // TODO sort by date
    @RequestMapping("/getAll")
    public String getAllReports(@RequestParam(name = "userId") Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("No such user"));
        if (isSingleRole(user.getRoles(), Role.OPERATOR)) {
            return  reportRepository.findAllByStatus(Status.SENT).stream().collect(Collectors.toList()).toString();
        } else
            return "You have not got permission";
    }

    @GetMapping("/accept")
    public void acceptReport(@RequestParam(name = "userId") Long userId,
                             @RequestParam(name = "reportId") Long reportId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("No such user"));
        if (isSingleRole(user.getRoles(), Role.OPERATOR)) {
            Report report = reportRepository.findById(reportId).orElseThrow(() -> new UsernameNotFoundException("No such report"));
            report.setStatus(Status.ACCEPTED);

            reportRepository.save(report);
        }
    }

    @GetMapping("/decline")
    public void declineReport(@RequestParam(name = "userId") Long userId,
                             @RequestParam(name = "reportId") Long reportId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("No such user"));
        if (isSingleRole(user.getRoles(), Role.OPERATOR)) {
            Report report = reportRepository.findById(reportId).orElseThrow(() -> new UsernameNotFoundException("No such report"));
            report.setStatus(Status.ACCEPTED);

            reportRepository.save(report);
        }
    }

    private boolean isSingleRole(Collection<Role> roles, Role neededRole) {
        return roles.size() == 1 && roles.contains(neededRole);
    }
}
