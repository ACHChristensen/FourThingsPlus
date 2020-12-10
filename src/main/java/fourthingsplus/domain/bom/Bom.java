package fourthingsplus.domain.bom;

import fourthingsplus.domain.material.Material;

import java.util.ArrayList;
import java.util.List;

public class Bom {
    private final List<BomItem> items = new ArrayList<>();

    public void addItem(Material material, int size, int length, String description) {
        items.add(new BomItem());
    }

    public static class BomItem {

    }
}
