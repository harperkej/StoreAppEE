package com.arberkuci.storeapp.web.json;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Method;
import java.util.*;


public class JsonBuilder {

    //TODO: To be continue the implementation of mapping from any type of object to JsonArray.

    public JsonArray convertUserDTOsToJson(List<?> objects) {
        JsonArrayBuilder res = Json.createArrayBuilder();
        if (objects != null) {
            objects.stream().filter(e -> e != null).map(JsonBuilder::convertUserDtoToJson).forEach(res::add);
        }
        return res.build();
    }

    private static JsonObjectBuilder convertUserDtoToJson(Object object) {
        Map<String, Class<?>> propertiesAndItsType = new HashMap<>();

        List<Method> methodsList = Arrays.asList(object.getClass().getMethods());

        methodsList.forEach(e -> {

            if (e.getName().startsWith("get")) {
                propertiesAndItsType.put(e.getName(), e.getReturnType());
            }

        });


        JsonObjectBuilder result = Json.createObjectBuilder();
        //result.add(method.getName().replace("get", ""), method.invoke(object));

        methodsList.forEach(method -> {

            if (method.getName().startsWith("get")) {
                Class<?> type = propertiesAndItsType.get(method.getName());
                Object value;
                try {
                    value = method.invoke(object);
                } catch (Exception ex) {
                    throw new RuntimeException("Exception occured while trying to invoke method -> " + method.getName());
                }
                if (value == null) {
                    result.add(method.getName().replace("get", ""), (String) null);
                }
            }

        });

        return result;
    }

}
