package pl.spring.demo.properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import pl.spring.demo.config.AppConfiguraion;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class PropertiesTest {

	@Configuration
	@Import(AppConfiguraion.class)
	static class ContextConfiguration {
	}

	@Value("${app.name}")
	private String applicationName;

	@Test
	public void testShouldCheckApplicationName() {
		// given
		final String appName = "starter-kit";
		// when then
		Assert.assertEquals(appName, applicationName);
	}
}
