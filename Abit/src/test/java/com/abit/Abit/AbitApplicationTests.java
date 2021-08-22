package com.abit.Abit;

import com.abit.Abit.entety.Abit;
import com.abit.Abit.repo.AbitRepo;
import com.abit.Abit.service.AbitService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class AbitApplicationTests {


	@Test
	void contextLoads() {


	}

}
