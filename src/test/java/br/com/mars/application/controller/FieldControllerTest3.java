package br.com.mars.application.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FieldControllerTest3 {

	@Autowired
	FieldController fieldController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(fieldController).is(null);
	}

}
