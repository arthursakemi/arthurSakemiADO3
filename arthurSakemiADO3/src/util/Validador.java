/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;

public class Validador {

    public static boolean validarInt(String x) {

        try {
            int n = Integer.parseInt(x);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static boolean validarDouble(String x) {
        try {
            double n = Double.parseDouble(x);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
