package org.fbarros.mp3clinic.data.builder;

import org.fbarros.mp3clinic.data.builder.configuration.BuildersConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BuildersConfiguration.class, loader=AnnotationConfigContextLoader.class)
public abstract class BaseTestCase {

}
