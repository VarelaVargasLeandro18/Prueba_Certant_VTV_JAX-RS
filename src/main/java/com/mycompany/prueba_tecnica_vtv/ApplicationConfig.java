/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prueba_tecnica_vtv;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.context.support.ClassPathXmlApplicationContext;

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
public class ApplicationConfig extends Application {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
}