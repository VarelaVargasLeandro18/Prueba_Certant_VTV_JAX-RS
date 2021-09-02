/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prueba_tecnica_vtv;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

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
public class ApplicationConfig extends Application 
{
    @Override
    public Set<Class<?>> getClasses() 
    {
        Set<Class<?>> resources = new java.util.HashSet<>();
        
        try
        {
            Class jacksonProvider = Class.forName("org.glassfish.jersey.containers");
            
            resources.add(jacksonProvider);
        }
        catch (ClassNotFoundException ex)
        {
            
        }
        
        this.addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) 
    {
        resources.add( rest.RESTAuto.class );
        resources.add( rest.RESTInspeccion.class );
        resources.add( rest.RESTInspector.class );
        resources.add( rest.RESTPropietario.class );
    }
    
}