package com.broaderator.mcserver.kernelcore.serializerMods;

import com.broaderator.mcserver.kernelcore.Serializer;
import org.yaml.snakeyaml.constructor.AbstractConstruct;

import java.util.HashMap;
import java.util.List;

public class $_List implements Serializer.Module {

    @Override
    public Class<?> getType() {
        return List.class;
    }

    @Override
    public Object deserialize(HashMap<String, Object> obj) {
        List<Object> serializedList = (List<Object>) obj.get("l");
        for(int i = 0; i < serializedList.size(); i++){
            serializedList.set(i, new $_List().deserialize((HashMap<String, Object>) serializedList.get(i)));
        }
        return null;// todo: new structure/snakeyaml?
    }

    @Override
    public HashMap<String, Object> serialize(Object obj) {
        return null;
    }

    @Override
    public HashMap<String, Class> expectedMap() {
        return null;
    }
}
