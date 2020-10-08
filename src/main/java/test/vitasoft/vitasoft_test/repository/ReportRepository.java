package test.vitasoft.vitasoft_test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.vitasoft.vitasoft_test.entity.Report;
import test.vitasoft.vitasoft_test.entity.Status;
import test.vitasoft.vitasoft_test.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    Optional<List<Report>> findAllByStatus(Status status);

    Optional<List<Report>> findAllByUser(User user);

}
