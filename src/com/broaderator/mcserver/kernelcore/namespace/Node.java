package com.broaderator.mcserver.kernelcore.namespace;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.KernelObject;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.ArrayList;
import java.util.List;

public class Node implements KernelObject, NamespaceElement {
    private String id;
    private List<Value> values = new ArrayList<>();
    private List<Node> children = new ArrayList<>();

    Node(String id) {
        this.id = id;
    }

    public boolean is(String otherId) {
        return id.equals(otherId);
    }

    public List<String> childrenIDs() {
        List<String> output = new ArrayList<>();
        for (Node child : this.children)
            output.add(child.id);
        return output;
    }

    public List<String> valueIDs() {
        List<String> output = new ArrayList<>();
        for (Value child : this.values)
            output.add(child.getID());
        return output;
    }

    // reason of existence is that
    // they're more verbose and faster than childrenIDs/valueIDs().contains(...),
    // does not (necessarily) iterate through every element, or iterate 1 to 2 times as a final result
    public boolean containsChild(String childID) {
        for (Node child : this.children) {
            if (child.id.equals(childID)) return true;
        }
        return false;
    }

    public boolean containsValue(String valueID) {
        for (Value val : this.values) {
            if (val.getID().equals(valueID)) return true;
        }
        return false;
    }

    public boolean addChild(Node n) {
        if (!this.containsChild(n.id)) {
            this.children.add(n);
            return true;
        } else {
            throw new NodeAlreadyExistsException(this.getComponentName() + ": Node ID " + n.id + " already exists in children");
        }
    }

    public boolean addValue(Value v) {
        if (!this.containsValue(v.getID())) {
            this.values.add(v);
            return true;
        } else {
            throw new NodeAlreadyExistsException(this.getComponentName() + ": Value ID " + v.getID() + " already exists in values");
        }
    }

    private void cleanUpIDs() {
        _cleanup(this.children);
        _cleanup(this.values);
    }

    private void _cleanup(List<? extends NamespaceElement> list) {
        List<String> uniqueIDs = new ArrayList<>();
        List<Integer> remove = new ArrayList<>();
        for (NamespaceElement n : list) {
            if (uniqueIDs.contains(n.getID())) {
                Logger.debug(this, StringFormat.f("cleaning up duplicate node {0}", n.getID()), $.DL_INFO);
                remove.add(list.indexOf(n));
            } else uniqueIDs.add(n.getID());
        }
        for (Integer n : remove) {
            list.remove(n.intValue());
        }
        uniqueIDs.clear();
        remove.clear();
    }

    @Override
    public String getID() {
        return id;
    }

    public Node getChild(String id) {
        for (Node n : children) {
            if (n.id.equals(id)) {
                return n;
            }
        }
        return null;
    }

    public Object val(String id) {
        return getValue(id).get();
    }

    public void val(String id, Object newValue) {
        if (this.containsValue(id)) {
            getValue(id).set(newValue);
        } else {
            this.addValue(new Value(id, newValue));
        }
    }

    public Value getValue(String id) {
        for (Value v : values) {
            if (v.getID().equals(id))
                return v;
        }
        return null;
    }

    @Override
    public String getComponentName() {
        return StringFormat.f("NamespaceNode#{0}:{1}", this.hashCode(), this.id);
    }

    public String toString() {
        return getComponentName();
    }
}
