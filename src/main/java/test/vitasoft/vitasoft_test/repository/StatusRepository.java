package test.vitasoft.vitasoft_test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.vitasoft.vitasoft_test.entity.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {

}
