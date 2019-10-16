/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iad_1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sasha
 */
public class CartTree {

    private Node root;
    private double error;
    private double testError;
    private int countErr;

    /**
     * @return the root
     */
    public Node getRoot() {
        return root;
    }

    /**
     * @return the error
     */
    public double getError() {
        return error;
    }

    public CartTree(Node root) {
        this.root = root;
        training();
        error = calculateError();
        MainFrame.getInstance().setError(error + "");
    }

    private void training() {
        if (root != null) {
            createTree(root);
        } else {
            MainFrame.getInstance().showMessage("У дерева нет корня");
        }
    }

    private void createTree(Node node) {
        double[] q_values = new double[19];
        for (int i = 0; i < q_values.length; i++) {
            if (i >= 12 && i <= 15) {
                q_values[i] = getQ(node, i, Value.LEGS[i - 11]);
            } else {
                q_values[i] = getQ(node, i, 0);
            }
        }
        double max = q_values[0];
        int max_index = 0;
        for (int i = 1; i < 19; i++) {
            if (max <= q_values[i]) {
                max = q_values[i];
                max_index = i;
            }
        }
        if (max != 0) {
            setNodes(node, max_index);
            node.setIndexPartition(max_index);
            node.setMessage(Value.messages[max_index]);
        } else {
            node.setLeaf(true);
            int type = node.getValue(0).getType();
            node.setClassIndex(type);
            node.setMessage(String.valueOf(type));
            return;
        }
        createTree(node.getLeft());
        createTree(node.getRight());
    }

    private double getQ(Node node, int type, int value) {
        List<Value> left = new ArrayList<>();
        List<Value> right = new ArrayList<>();
        int r = 0;
        int l = 0;
        for (int i = 0; i < node.length(); i++) {
            if (type >= 12 && type <= 15) {
                if ((int) node.getParameter(i, type) > value) {
                    left.add(node.getValue(i));
                    l++;
                } else {
                    right.add(node.getValue(i));
                    r++;
                }
            } else {
                if (!(boolean) node.getParameter(i, type)) {
                    left.add(node.getValue(i));
                    l++;
                } else {
                    right.add(node.getValue(i));
                    r++;
                }
            }
        }
        return calculateQ(left, right);
    }

    private double calculateQ(List<Value> left, List<Value> right) {
        double Q;
        int leftSize = left.size();
        int rightSize = right.size();
        double Pl = (double) leftSize / (double) (leftSize + rightSize);
        double Pr = (double) rightSize / (double) (leftSize + rightSize);
        double W = 0;
        for (int i = 1; i <= 7; i++) {
            int Kl = 0;
            int Kr = 0;
            for (Value item : left) {
                if (item.getType() == i) {
                    Kl++;
                }
            }
            for (Value value : right) {
                if (value.getType() == i) {
                    Kr++;
                }
            }
            double one = 0;
            if (leftSize > 0) {
                one = (double) Kl / (double) leftSize;
            }
            double two = 0;
            if (rightSize > 0) {
                two = (double) Kr / (double) rightSize;
            }
            W += Math.abs(one - two);
        }
        Q = 2 * Pl * Pr * W;
        System.out.println("Лево: " + Pl + " право: " + Pr + "W = " + W + " Q= " + Q);
        return Q;
    }

    private void setNodes(Node node, int number) {
        List<Value> left = new ArrayList<>();
        List<Value> right = new ArrayList<>();

        if (number >= 12 && number <= 15) {
            for (int i = 0; i < node.length(); i++) {
                if ((int) node.getParameter(i, number) > Value.LEGS[number - 11]) {
                    left.add(node.getValue(i));
                } else {
                    right.add(node.getValue(i));
                }
            }
        } else {
            for (int i = 0; i < node.length(); i++) {
                if (!(boolean) node.getParameter(i, number)) {
                    left.add(node.getValue(i));
                } else {
                    right.add(node.getValue(i));
                }
            }
        }
        node.setLeft(new Node(left));
        node.setRight(new Node(right));
    }

    public void pruning() {
        pruningOne(root);
    }

    private void pruningOne(Node node) {
        if (!node.getLeaf()) {
            node.getLeft().setLook(false);
            node.getRight().setLook(false);
            node.setLeaf(true);
            double er = calculateError();
            System.out.println(node.getMessage() + "!тек.ош = " + error + "|| нов.ош = " + er);
            if ((error - er) <= 0) {
                node.getLeft().setLook(true);
                node.getRight().setLook(true);
                node.setLeaf(false);
                pruningOne(node.getLeft());
                pruningOne(node.getRight());
            } else {
                node.setLeft(null);
                node.setRight(null);
            }
        }
    }

    public void test(List<Value> test_values, Boolean flag) {
        if (root != null) {
            countErr = 0;
            for (final Value test_value : test_values) {
                MainFrame.getInstance().setTextToFrame(test_value.toString());
                testOne(test_value, root, flag);

                if (flag) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                setUnFill(root);
            }
            testError = (double) countErr / (double) test_values.size();
            MainFrame.getInstance().setErrorTest(String.valueOf(testError));
            MainFrame.getInstance().endTesting();
            if (flag) {
                MainFrame.getInstance().setVisible(true);
            }
        } else {
            MainFrame.getInstance().showMessage("У дерева нет корня");
        }

    }

    private void testOne(final Value value, Node node, Boolean flag) {
        if (flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        node.setIsFill(true);

        if (node.getLeaf()) {
            if (value.getType() != node.getClassIndex()) {
                countErr++;
            }
            MainFrame.getInstance().showMessage(value.toString() + "CLASS = " + node.getClassIndex());
            MainFrame.getInstance().repaintTree();
        } else {
            Object val;
            switch (node.getIndexPartition()) {
                case 0:
                    val = value.getHair();
                    break;
                case 1:
                    val = value.getFeathers();
                    break;
                case 2:
                    val = value.getEggs();
                    break;
                case 3:
                    val = value.getMilk();
                    break;
                case 4:
                    val = value.getAirborne();
                    break;
                case 5:
                    val = value.getAquatic();
                    break;
                case 6:
                    val = value.getPredator();
                    break;
                case 7:
                    val = value.getToothed();
                    break;
                case 8:
                    val = value.getBackbone();
                    break;
                case 9:
                    val = value.getBreathes();
                    break;
                case 10:
                    val = value.getVenomous();
                    break;
                case 11:
                    val = value.getFins();
                    break;
                case 16:
                    val = value.getTail();
                    break;
                case 17:
                    val = value.getDomestic();
                    break;
                case 18:
                    val = value.getCatsize();
                    break;
                default:
                    val = value.getLegs();
            }
            if (node.getIndexPartition() >= 12 && node.getIndexPartition() <= 15) {
                if ((int) val > Value.LEGS[node.getIndexPartition() - 11]) {
                    MainFrame.getInstance().repaintTree();
                    testOne(value, node.getLeft(), flag);

                } else {
                    MainFrame.getInstance().repaintTree();
                    testOne(value, node.getRight(), flag);
                }
            } else {
                if (!(boolean) val) {
                    MainFrame.getInstance().repaintTree();
                    testOne(value, node.getLeft(), flag);
                } else {
                    MainFrame.getInstance().repaintTree();
                    testOne(value, node.getRight(), flag);
                }
            }
        }
    }

    private void setUnFill(Node node) {
        node.setIsFill(false);
        if (node.getLeft() != null) {
            setUnFill(node.getLeft());
        }
        if (node.getRight() != null) {
            setUnFill(node.getRight());
        }
    }

    private double calculateError() {
        int er_count = 0;
        ArrayList<Node> leavs = getLeafs();
        for (Node leav : leavs) {
            if (leav.getLook()) {
                int max_count = 0;
                int value_class = 0;
                int[] counts = new int[7];
                int t = 0;
                for (int j = 1; j < 8; j++, t++) {
                    for (int l = 0; l < leav.getValues().size(); l++) {
                        if (leav.getValue(l).getType() == j) {
                            counts[t]++;
                        }
                    }
                }
                for (int j = 0; j < 7; j++) {
                    if (max_count < counts[j]) {
                        max_count = counts[j];
                        value_class = j + 1;
                    }
                }
                for (int j = 1; j < 8; j++) {
                    if (j != value_class) {
                        if (counts[j - 1] > 0) {
                            er_count += counts[j - 1];
                        }
                    }
                }
            }
        }
        return (double) er_count / (double) root.getValues().size();
    }

    public ArrayList<Node> getLeafs() {
        ArrayList<Node> leafs = new ArrayList<>();
        getLeafs(root, leafs);
        return leafs;
    }

    private void getLeafs(Node node, ArrayList<Node> leafs) {
        if (!node.getLeaf()) {
            getLeafs(node.getLeft(), leafs);
            getLeafs(node.getRight(), leafs);
        } else {
            leafs.add(node);
        }
    }

    private void showLeafs() {
        ArrayList<Node> n = getLeafs();
        for (int i = 0; i < n.size(); i++) {
            for (int j = 0; j < n.get(i).getValues().size(); j++) {
                MainFrame.getInstance().showMessage(n.get(i).getValue(j).getAnimal_name() + "-" + n.get(i).getValue(j).getType());
            }
        }
    }

    /**
     * @return the testError
     */
    public double getTestError() {
        return testError;
    }
}
