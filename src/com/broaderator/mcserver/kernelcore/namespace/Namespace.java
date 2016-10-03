package com.broaderator.mcserver.kernelcore.namespace;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.KernelObject;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.Serializer;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Namespace extends Node implements KernelObject {
    public static final String PATH_DELIMITER = ".";
    // questionable options
    private static final boolean CREATE_DIRECTORY_DURING_setPath = true;

    public Namespace(String id, boolean serialize) {
        super(id, serialize);
    }

    @Override
    public String getComponentName() {
        return StringFormat.f("Namespace#{0}:{1}", this.hashCode(), this.getID());
    }

    public void setPath(String path, Object value) {
        if(this.checkSerializable && !Serializer.isSerializable(value)){
            throw new UnsupportedOperationException("setPath: provided value, " + value + ", is not serializable");
        }
        String[] parsedPath = path.split(Pattern.quote(PATH_DELIMITER));
        navigateThrough(
                utilSubArray(parsedPath, 0, parsedPath.length-1),
                CREATE_DIRECTORY_DURING_setPath).setValue(parsedPath[parsedPath.length-1], value);
    }

    public Node getNode(String path){
        return navigateThrough(path.split(Pattern.quote(PATH_DELIMITER)), false);
    }

    public Object getValueByPath(String path){
        String[] parsedPath = path.split(Pattern.quote(PATH_DELIMITER));
        return navigateThrough(utilSubArray(parsedPath, 0, parsedPath.length-1), false);
    }

    public Node createNodes(String path){
        return navigateThrough(path.split(Pattern.quote(PATH_DELIMITER)), true);
    }

    private Node navigateThrough(String[] pathElems, boolean createNodes) {
        /*String[] pathElems = path.split(Pattern.quote(PATH_DELIMITER));*/
        Node currentNode = this;
        for (String pathElem : pathElems) {
            if (!currentNode.childrenIDs().contains(pathElem)) {
                if (createNodes) {
                    currentNode.addChild(new Node(pathElem, checkSerializable));
                } else {
                    throw new NullPointerException("in path " + Arrays.toString(pathElems) + ", " + pathElem + " doesn't exist under the previous node, " + currentNode.getID());
                }
            }
            currentNode = currentNode.getChild(pathElem);
        }
        return currentNode;
    }

    private String[] utilSubArray(String[] input, int startIndex, int endIndex) {
        List<String> list = Arrays.asList(input).subList(startIndex, endIndex);
        return list.toArray(new String[list.size()]);
    }
}