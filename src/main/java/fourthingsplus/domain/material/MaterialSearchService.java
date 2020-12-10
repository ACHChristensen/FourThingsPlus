package fourthingsplus.domain.material;

public interface MaterialSearchService {
    Material find(MaterialType type, int width, int height);
}
