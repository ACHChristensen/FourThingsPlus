package fourthingsplus.infrastructure;

import fourthingsplus.domain.material.Material;
import fourthingsplus.domain.material.MaterialSearchService;
import fourthingsplus.domain.material.MaterialType;

public class DummyMaterialSearchService implements MaterialSearchService {

    @Override
    public Material find(MaterialType type, int width, int height) {
        return new Material(0, "DUMMY: " + type, width, height);
    }
}
