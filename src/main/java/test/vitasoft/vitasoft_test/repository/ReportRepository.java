package test.vitasoft.vitasoft_test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.vitasoft.vitasoft_test.entity.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

}
