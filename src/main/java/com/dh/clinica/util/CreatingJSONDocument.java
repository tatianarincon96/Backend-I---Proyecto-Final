package com.dh.clinica.util;
import org.json.simple.JSONObject;

public class CreatingJSONDocument {
    public static JSONObject getJson() {
        //Creating a JSONObject object
        JSONObject pacienteObject = new JSONObject();
        //Inserting key-value pairs into the paciente object
        pacienteObject.put("id",1);
        pacienteObject.put("nombre", "Tatiana");
        pacienteObject.put("apellido", "Rincon");
        pacienteObject.put("dni", "99999999");
        pacienteObject.put("fechaIngreso", "2018-12-05");
        pacienteObject.put("domicilio", null);

        return pacienteObject;
    }
}
