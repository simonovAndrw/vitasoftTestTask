package test.vitasoft.vitasoft_test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.vitasoft.vitasoft_test.entity.User;

@Repository
public interface EmployeeRepository extends CrudRepository<User, Long> {

}
