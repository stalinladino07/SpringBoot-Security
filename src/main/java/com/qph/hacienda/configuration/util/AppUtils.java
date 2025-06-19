package com.qph.hacienda.configuration.util;

import com.qph.hacienda.security.model.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppUtils {

    public static List<Menu> buildMenuTree(List<Menu> menus) {
        Map<Long, Menu> menuMap = new HashMap<>();
        List<Menu> rootMenus = new ArrayList<>();

        // Paso 1: Indexar por idMenu
        for (Menu menu : menus) {
            menu.setItems(new ArrayList<>()); // Inicializar lista children
            menuMap.put(menu.getIdMenu(), menu);
        }

        // Paso 2: Asociar hijos con sus padres
        for (Menu menu : menus) {
            if (menu.getIdFather() != null) {
                Menu parent = menuMap.get(menu.getIdFather());
                if (parent != null) {
                    parent.getItems().add(menu);
                }
            } else {
                rootMenus.add(menu);
            }
        }

        // Paso 3: Convertir listas vacías en null
        for (Menu root : rootMenus) {
            cleanEmptyItems(root);
        }

        return rootMenus;
    }

    // Método auxiliar recursivo
    private static void cleanEmptyItems(Menu menu) {
        List<Menu> children = menu.getItems();
        if (children == null || children.isEmpty()) {
            menu.setItems(null);
        } else {
            for (Menu child : children) {
                cleanEmptyItems(child);
            }
        }
    }
}
