package com.dezzy.springbootmodulithcourse;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
class SpringBootModulithCourseApplicationTests {

	@Test
	void contextLoads() {
        ApplicationModules.of(SpringBootModulithCourseApplication.class).verify();
	}

}
