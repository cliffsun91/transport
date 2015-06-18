package com.cliffsun.transport.config;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

import com.cliffsun.transport.ResourceModule;
import com.google.inject.Module;


public class TransportGuiceServletContextListener extends GuiceResteasyBootstrapServletContextListener{


	@Override
    protected List<? extends Module> getModules(ServletContext context)
    {
        return Arrays.asList(new TransportServletModule());
    }
}
