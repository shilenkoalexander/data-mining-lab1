/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iad_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sasha
 */
public class Handler {
    private CartTree tree;

    public void readData(boolean test, boolean flag) {
        MainFrame.getInstance().showMessage("Входные данные:");
        String dataFilename = test ? "data_test.txt" : "data.txt";
        File file = new File(dataFilename);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                List<Value> values = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    String[] parths = line.split(",");
                    String name = parths[0];
                    int type = Integer.parseInt(parths[17]);
                    int legs = Integer.parseInt(parths[13]);
                    Boolean[] val = new Boolean[15];
                    int j = 0;
                    for (int i = 1; i < 17; i++, j++) {
                        if (i == 13) {
                            j--;
                            continue;
                        }
                        int buf = Integer.parseInt(parths[i]);
                        val[j] = buf == 1;
                    }
                    Value value = new Value(
                            name, val[0], val[1], val[2], val[3], val[4], val[5],
                            val[6], val[7], val[8], val[9], val[10], val[11], legs,
                            val[12], val[13], val[14], type
                    );
                    values.add(value);
                    MainFrame.getInstance().showMessage(value.toString());
                }

                if (test) {
                    MainFrame.getInstance().showMessage("Тестируем");
                    tree.test(values, flag);
                } else {
                    MainFrame.getInstance().showMessage("Создаем дерево");
                    createTree(values);
                }
            } catch (IOException ex) {
                Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Файл-данных не найден");
        }
    }

    private void createTree(List<Value> values) {
        tree = new CartTree(new Node(values));
    }

    public CartTree getTree() {
        return tree;
    }
}
