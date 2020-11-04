package com.actualizer.utils;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

public class PropertiesReader {

    @Autowired
    Environment environment;

    public String HTTP_URL = "...";
    public String NOMBRE_SERVICIO = environment.getProperty("nombre_servicio");

}
