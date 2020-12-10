package fourthingsplus.domain.bom;

import fourthingsplus.domain.carport.Carport;
import fourthingsplus.domain.material.MaterialSearchService;
import fourthingsplus.domain.material.MaterialType;

public class BomService {
    private final MaterialSearchService materials;

    public BomService(MaterialSearchService materials) {
        this.materials = materials;
    }

    public Bom calculateBom(Carport carport) {
        Bom bom = new Bom();

        bom.addItem(materials.find(MaterialType.RIM_WOOD, 200, 30), 2, 3000, "Rim i siden");

        return bom;
    }
}
