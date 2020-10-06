package test.vitasoft.vitasoft_test.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import test.vitasoft.vitasoft_test.entity.User;
import test.vitasoft.vitasoft_test.entity.Report;
import test.vitasoft.vitasoft_test.entity.Role;
import test.vitasoft.vitasoft_test.entity.Status;
import test.vitasoft.vitasoft_test.repository.EmployeeRepository;
import test.vitasoft.vitasoft_test.repository.ReportRepository;
import test.vitasoft.vitasoft_test.repository.StatusRepository;
import test.vitasoft.vitasoft_test.repository.VRoleRepository;

import java.util.Arrays;
import java.util.Calendar;

@Component
public class DataLoader implements ApplicationRunner {

    private EmployeeRepository employeeRepository;
    private ReportRepository reportRepository;
    private VRoleRepository roleRepository;
    private StatusRepository statusRepository;

    @Autowired
    public DataLoader(EmployeeRepository employeeRepository,
                      ReportRepository reportRepository,
                      VRoleRepository roleRepository,
                      StatusRepository statusRepository) {
        this.employeeRepository = employeeRepository;
        this.reportRepository = reportRepository;
        this.roleRepository = roleRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Status draft = new Status("Draft");
        Status sent = new Status("Sent");
        Status accepted = new Status("Accepted");
        Status declined = new Status("Declined");

        statusRepository.saveAll(Arrays.asList(draft, sent, accepted, declined));

        Role user = new Role("User");
        Role operator = new Role("Operator");
        Role admin = new Role("Admin");

        roleRepository.saveAll(Arrays.asList(user, operator, admin));

        User ivanUser = new User("Ivan", "password");
        User stepanUser = new User("Stepan", "password");
        User dimaOperator = new User("Dima", "password");
        User filippAdmin = new User("Filipp", "password");

        employeeRepository.saveAll(Arrays.asList(ivanUser, stepanUser, dimaOperator, filippAdmin));

        Report reportFromIvan = new Report(ivanUser, sent, Calendar.getInstance(), "Some text from Ivan");
        Report reportFromStepan = new Report(stepanUser, sent, Calendar.getInstance(), "Some text from Stepan");

        reportRepository.saveAll(Arrays.asList(reportFromIvan, reportFromStepan));

        // add roles to employees
        ivanUser.getRoles().addAll(Arrays.asList(user));
        stepanUser.getRoles().addAll(Arrays.asList(user));
        dimaOperator.getRoles().addAll(Arrays.asList(operator));
        filippAdmin.getRoles().addAll(Arrays.asList(admin));

        employeeRepository.saveAll(Arrays.asList(ivanUser, stepanUser, dimaOperator, filippAdmin));
    }
}
