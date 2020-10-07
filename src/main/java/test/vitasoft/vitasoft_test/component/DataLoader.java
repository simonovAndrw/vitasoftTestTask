package test.vitasoft.vitasoft_test.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import test.vitasoft.vitasoft_test.entity.Report;
import test.vitasoft.vitasoft_test.entity.Role;
import test.vitasoft.vitasoft_test.entity.Status;
import test.vitasoft.vitasoft_test.entity.User;
import test.vitasoft.vitasoft_test.repository.UserRepository;
import test.vitasoft.vitasoft_test.repository.ReportRepository;

import java.util.Arrays;
import java.util.Calendar;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private ReportRepository reportRepository;

    @Autowired
    public DataLoader(UserRepository userRepository,
                      ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User ivanUser = new User("Ivan", "ivan@email.com","$2y$12$1bf.DUugXwyIo85STb1ABOXgcEOkrAOCsS766rqSjkxcZbV5ctOkm");
        User stepanUser = new User("Stepan", "stepan@email.com", "$2y$12$1bf.DUugXwyIo85STb1ABOXgcEOkrAOCsS766rqSjkxcZbV5ctOkm");
        User dimaOperator = new User("Dima", "dima@email.com","$2y$12$1bf.DUugXwyIo85STb1ABOXgcEOkrAOCsS766rqSjkxcZbV5ctOkm");
        User filippAdmin = new User("Filipp", "filipp@email.com","$2y$12$1bf.DUugXwyIo85STb1ABOXgcEOkrAOCsS766rqSjkxcZbV5ctOkm");

        userRepository.saveAll(Arrays.asList(ivanUser, stepanUser, dimaOperator, filippAdmin));

        Report reportFromIvan = new Report(ivanUser, Status.SENT, Calendar.getInstance(), "Some text from Ivan");
        Report reportFromStepan = new Report(stepanUser, Status.SENT, Calendar.getInstance(), "Some text from Stepan");

        reportRepository.saveAll(Arrays.asList(reportFromIvan, reportFromStepan));

        ivanUser.getRoles().add(Role.USER);
        stepanUser.getRoles().addAll(Arrays.asList(Role.USER, Role.OPERATOR));
        dimaOperator.getRoles().add(Role.OPERATOR);
        filippAdmin.getRoles().add(Role.ADMIN);

        userRepository.saveAll(Arrays.asList(ivanUser, stepanUser, dimaOperator, filippAdmin));
    }
}
