package com.actualizer.restcontrollers;

import com.actualizer.system.FileDownloader;
import com.actualizer.system.SystemCtl;
import com.actualizer.utils.Mensajes;
import com.actualizer.utils.Router;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@RestController
public class DashboardRestController {

    private JSONObject jo;

    @Autowired
    Environment environment;

    @GetMapping(Router.UPDATE)
    public String update(){
        //environment lector de archivo propierties editado por usuario y ruta
        // iniciar servicio https de ruta de origen de descarga
        // elegir destino descargar archivo en ruta Stage
        boolean isDownloaded = FileDownloader.download(
                "",
                "",
                environment.getProperty("download_url"),
                environment.getProperty("stage")
        );
// Verificar estado de descarga al ser verdadero la descarga completa
// se movera archivo a ruta de archivo propierties Target
        if(isDownloaded){
            // Origen
            Path stage = Paths.get(environment.getProperty("stage"));

            // Destino
            Path target = Paths.get(environment.getProperty("target"));

            // Instrucción de mover el archivo
            try {

              // SystemCtl.systemCtl("stop", environment.getProperty("nombre_servicio"));
                Files.move(stage, target, StandardCopyOption.REPLACE_EXISTING);
             //  SystemCtl.systemCtl("start", environment.getProperty("nombre_servicio"));

                jo = new JSONObject();
                jo.put("message", Mensajes.ACTUALIZACION_EXITOSA);

            } catch (IOException e) {
                e.printStackTrace();
                return "fALLO SERVICIO INTENTE DE NUEVO";
            }

          //  catch (InterruptedException e) {
          //    e.printStackTrace();
         //  }
            catch (JSONException e) {
                e.printStackTrace();
                return "fALLO SERVICIO INTENTE DE NUEVO";
            }

        }

        return jo.toString();
    }

    @GetMapping(Router.SERVICE_START)
    public String iniciarServicio(){

        // Invocar a la clase SystemCtl
        // # systemctl start mysqld
        try {
            SystemCtl.systemCtl("start", environment.getProperty("nombre_servicio"));
            jo = new JSONObject();
            jo.put("message", Mensajes.SERVICIO_ACTIVADO);
        } catch (IOException e) {
                    e.printStackTrace();
            return "fALLO SERVICIO INTENTE DE NUEVO";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "fALLO SERVICIO INTENTE DE NUEVO";
        } catch (JSONException e) {
            e.printStackTrace();
            return "fALLO SERVICIO INTENTE DE NUEVO";
        }

        System.out.println("Iniciando " + environment.getProperty("nombre_servicio"));
        return "Iniciando servicio";

    }


    @GetMapping(Router.SERVICE_STOP)
    public String detenerServicio(){

        // Invocar a la clase SystemCtl
        // # systemctl start mysqld
        try {
            SystemCtl.systemCtl("stop", environment.getProperty("nombre_servicio"));
            jo = new JSONObject();
            jo.put("message", Mensajes.SERVICIO_APAGADO);
        } catch (IOException e) {
            e.printStackTrace();
            return "fALLO SERVICIO INTENTE DE NUEVO";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "fALLO SERVICIO INTENTE DE NUEVO";
        } catch (JSONException e) {
            e.printStackTrace();
            return "fALLO SERVICIO INTENTE DE NUEVO";
        }

        System.out.println("Deteniendo " + environment.getProperty("nombre_servicio"));
        return jo.toString();

    }
//////////////////////////////////////////
    //
@GetMapping(Router.SERVICE_REFRESH)
public String refreshServicio(){

    // Invocar a la clase SystemCtl
    // # systemctl start mysqld
    try {
        SystemCtl.systemCtl("restart", environment.getProperty("nombre_servicio"));
        jo = new JSONObject();
        jo.put("message", Mensajes.SERVICIO_REFRESH);
    } catch (IOException e) {
        e.printStackTrace();
        return "fALLO SERVICIO INTENTE DE NUEVO";
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
        return "fALLO SERVICIO INTENTE DE NUEVO";
    }

    System.out.println("Reiniciando " + environment.getProperty("nombre_servicio"));
    return jo.toString();

}


    ///
    /////////////////////////////////////////




}
