package org.fbarros.mp3clinic.kitchen.basetest;

import org.fbarros.mp3clinic.configuration.LoadersConfiguration;
import org.fbarros.mp3clinic.configuration.ProcesorsConfiguration;
import org.fbarros.mp3clinic.data.builder.configuration.BuildersConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ProcesorsConfiguration.class, LoadersConfiguration.class, BuildersConfiguration.class}, loader=AnnotationConfigContextLoader.class)
public abstract class BaseTest {

}
