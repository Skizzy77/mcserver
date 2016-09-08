package com.broaderator.mcserver.kernelcore.namespace;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.KernelObject;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Namespace extends Node implements KernelObject {
    public static final String PATH_DELIMITER = ".";
    // questionable options
    private static final boolean CREATE_DIRECTORY_DURING_setPath = true;
    private boolean checkSerializable = true;

    public Namespace(String id, boolean serialize) {
        super(id);
    }

    @Override
    public String getComponentName() {
        return StringFormat.f("Namespace#{0}:{1}", this.hashCode(), this.getID());
    }

    public void setPath(String path, Object value) {

    }

    private Node navigateThrough(String path, boolean createNodes) {
        String[] pathElems = path.split(Pattern.quote(PATH_DELIMITER));
        Node currentNode = this;
        for (int i = 0; i < pathElems.length; i++) {
            if (!currentNode.childrenIDs().contains(pathElems[i])) {
                if (createNodes) {
                    currentNode.addChild(new Node(pathElems[i]));
                    Logger.debug(this, StringFormat.f("created new node {0} under {1}", pathElems[i], pathElems[i - 1]), $.DL_DETAILS);
                } else {
                    throw new NullPointerException("in path " + path + ", " + pathElems[i] + " doesn't exist under the previous node, " + currentNode.getID());
                }
            }
            currentNode = currentNode.getChild(pathElems[i]);
        }
        return currentNode;
    }

    private String[] utilSubArray(String[] input, int startIndex, int endIndex) {
        List<String> list = Arrays.asList(input).subList(startIndex, endIndex);
        return list.toArray(new String[list.size()]);
    }
}