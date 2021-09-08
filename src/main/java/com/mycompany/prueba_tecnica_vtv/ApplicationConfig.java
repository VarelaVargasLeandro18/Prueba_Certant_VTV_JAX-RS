/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prueba_tecnica_vtv;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.AutoDAO;
import dao_abstract.IAutoDAO;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */

/*
 * JAXCKSON PROVIDER NO ESTÁ EN SU VERSIÓN MÁS RECIENTE
 * JERSEY NO ESTÁ EN SU VERSIÓN MÁS RECIENTE
 * 
 * CUANDO HAY EXCEPTIONS NO LAS ENCERRÉ EN TRY CATCH POR UNA CUESTIÓN DE SIMPLEZA,
 * DEBERÍA UTILIZAR CODIGOS HTTP
 */

@ApplicationPath("VTV")
public class ApplicationConfig extends ResourceConfig {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
}