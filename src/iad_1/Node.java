/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iad_1;

import java.util.List;

/**
 *
 * @author Sasha
 */
public class Node {
    private List<Value> values;
    private Node left;
    private Node right;
    private int index_partition = -1;
    private int classIndex = -1;
    private String message = "";
    private Boolean leaf = false;
    private Boolean look = true;
    private Boolean isFill = false;

    public Node(List<Value> values, Node left, Node right) {
        this.values = values;
        this.left = left;
        this.right = right;
    }

    public Node(List<Value> values) {
        this.values = values;
        left = null;
        right = null;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" + "values=" + values.size() + '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the look
     */
    public Boolean getLook() {
        return look;
    }

    /**
     * @param look the look to set
     */
    public void setLook(Boolean look) {
        this.look = look;
        if (left != null) {
            left.setLook(look);
        }
        if (right != null) {
            right.setLook(look);
        }
    }

    /**
     * @return the leaf
     */
    public Boolean getLeaf() {
        return leaf;
    }

    /**
     * @param leaf the leaf to set
     */
    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * @return the index_partition
     */
    public int getIndexPartition() {
        return index_partition;
    }

    /**
     * @param index_partition the index_partition to set
     */
    public void setIndexPartition(int index_partition) {
        this.index_partition = index_partition;
    }

    public int length() {
        return values.size();
    }


    public Object getParameter(int i, int type) {
        Value value = values.get(i);
        switch (type) {
            case 0:
                return value.getHair();
            case 1:
                return value.getFeathers();
            case 2:
                return value.getEggs();
            case 3:
                return value.getMilk();
            case 4:
                return value.getAirborne();
            case 5:
                return value.getAquatic();
            case 6:
                return value.getPredator();
            case 7:
                return value.getToothed();
            case 8:
                return value.getBackbone();
            case 9:
                return value.getBreathes();
            case 10:
                return value.getVenomous();
            case 11:
                return value.getFins();
            case 12:
            case 13:
            case 14:
            case 15:
                return value.getLegs();
            case 16:
                return value.getTail();
            case 17:
                return value.getDomestic();
            case 18:
                return value.getCatsize();
            default:
                return false;
        }
    }


    public Value getValue(int i) {
        return values.get(i);
    }

    /**
     * @return the classIndex
     */
    public int getClassIndex() {
        return classIndex;
    }

    /**
     * @param classIndex the classIndex to set
     */
    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    /**
     * @return the isFill
     */
    public Boolean getIsFill() {
        return isFill;
    }

    /**
     * @param isFill the isFill to set
     */
    public void setIsFill(Boolean isFill) {
        this.isFill = isFill;
    }
}
