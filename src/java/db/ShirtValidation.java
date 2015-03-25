/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ilja
 */
public class ShirtValidation {
        private static final int MAX_COST = 9999;

    public static Map vld(String id, String cost, String size) {
        Map<String, String> errorList = new HashMap<>();
        int costInt = 0;
        try {
            costInt = Integer.parseInt(cost);
        } catch (Exception e) {
            errorList.put("cost", "Only numbers");
        }
        if (costInt != 0 && costInt > MAX_COST) {
            errorList.put("cost", "Cost should be under 10000");
        }

        try {
            Integer.parseInt(id);
        } catch (Exception e) {
            errorList.put("cost", "Dont touch id !");
        }
        if (size.length() == 0) {
            errorList.put("type", "Can not be empty");
        }
        if (size.length() > 4) {
            errorList.put("type", "Max 4 cahracters");

        }
        return errorList;

    }
}
