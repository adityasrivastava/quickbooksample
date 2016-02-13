package com.project.quickbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
public abstract class AbstractTestController extends AbstractTest{

	  @Autowired
	  protected WebApplicationContext g_objWebApplicationContext;
	  
	  protected MockMvc g_objMvc;
	  
	  protected void setUp(){
		  g_objMvc = MockMvcBuilders.webAppContextSetup(g_objWebApplicationContext).build();
	  }
}
