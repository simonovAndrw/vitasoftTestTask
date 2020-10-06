package test.vitasoft.vitasoft_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import test.vitasoft.vitasoft_test.entity.*;
import test.vitasoft.vitasoft_test.repository.*;

import java.util.Arrays;
import java.util.Calendar;

// TODO write test to check how data manipulation works

@SpringBootApplication
public class VitasoftTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitasoftTestApplication.class, args);
	}

}
