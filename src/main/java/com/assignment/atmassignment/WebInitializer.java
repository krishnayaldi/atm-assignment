/**
 * 
 */
package com.assignment.atmassignment;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Krishna
 *
 */
public class WebInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AtmAssignmentApplication.class);
	}
}
