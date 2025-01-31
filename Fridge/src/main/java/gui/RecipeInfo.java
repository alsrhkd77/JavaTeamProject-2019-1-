/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dataSet.Iterator;
import dataSet.Recipe;
import dataSet.RecipeIterator;
import dataSet.RecipeSet;
import mainProgress.MainProgress;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class RecipeInfo extends javax.swing.JFrame {
    MainProgress mProgress;
    DefaultListModel recipeList;
    DefaultListModel ingredientList;
    RecipeSet recipeSet;
    /**
     * Creates new form recipe
     */
    public RecipeInfo(String input) {
        initComponents();
        mProgress = new MainProgress();
        recipeList = new DefaultListModel();
        ingredientList = new DefaultListModel();
        recipeSet = new RecipeSet();

        recipeSet = mProgress.getRecipeSet(input);

        ingredientList.addElement("[재료]");
        recipeList.addElement("[레시피]");
        Iterator iter = recipeSet.iterator();
        while (iter.hasNext()){
            Recipe recipe = (Recipe) iter.next();
            ingredientList.addElement(recipe.getIngredient());
            recipeList.addElement(recipe.getRecipe());
        }

        jList2.setModel(recipeList);
        jList1.setModel(ingredientList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        beforePage = new javax.swing.JButton();
        foodImage = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        beforePage.setText("처음으로");
        beforePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beforePageActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jList1);

        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(foodImage, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(beforePage, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(foodImage, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addComponent(beforePage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>

    private void beforePageActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        new First().setVisible(true);
    }

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {
        int index = jList2.getSelectedIndex() - 1;
        if(index<0){
            index = 0;
        }
        if(index>recipeSet.size()+1){
            index = recipeSet.size();
        }

        String url = recipeSet.getRecipeImg(index);

        if(url.equals("none")){
            foodImage.setText("이미지 없음");
        }
        else{
            Image image = null;
            try {
                image = ImageIO.read(new URL(url)).getScaledInstance(301,301,Image.SCALE_FAST);
            } catch (MalformedURLException ex) {
                Logger.getLogger(NowIngredient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NowIngredient.class.getName()).log(Level.SEVERE, null, ex);
            }
            foodImage.setIcon(new ImageIcon(image));
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton beforePage;
    private javax.swing.JLabel foodImage;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration                   
}
