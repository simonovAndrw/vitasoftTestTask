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
import java.util.Date;

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

        Report reportFromIvan1 = new Report(ivanUser, Status.SENT, new Date(), "Some text from Ivan1");
        Report reportFromIvan2 = new Report(ivanUser, Status.SENT, new Date(), "Some text from Ivan2");
        Report reportFromIvan3 = new Report(ivanUser, Status.SENT, new Date(), "Some text from Ivan3");
        Report reportFromIvan4 = new Report(ivanUser, Status.DRAFT, new Date(), "Some text from Ivan4");
        Report reportFromStepan1 = new Report(stepanUser, Status.SENT, new Date(), "Some text from Stepan1");
        Report reportFromStepan2 = new Report(stepanUser, Status.SENT, new Date(), "Some text from Stepan2");
        Report reportFromStepan3 = new Report(stepanUser, Status.SENT, new Date(), "Some text from Stepan3");
        Report reportFromStepan4 = new Report(stepanUser, Status.DRAFT, new Date(), "Some text from Stepan4");

        reportRepository.saveAll(Arrays.asList(reportFromIvan1, reportFromIvan2, reportFromIvan3, reportFromIvan4,
                reportFromStepan1, reportFromStepan2,reportFromStepan3,reportFromStepan4));

        ivanUser.getRoles().add(Role.USER);
        stepanUser.getRoles().add(Role.USER);
        dimaOperator.getRoles().add(Role.OPERATOR);
        filippAdmin.getRoles().add(Role.ADMIN);

        userRepository.saveAll(Arrays.asList(ivanUser, stepanUser, dimaOperator, filippAdmin));
    }
}
