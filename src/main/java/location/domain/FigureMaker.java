package location.domain;

public class FigureMaker {
    public static Figure create(Points points) {
        return ShapeEnum.valueOf(points.size()).create(points);
    }
}
