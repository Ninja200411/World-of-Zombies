package Lib;

import java.awt.*;

public interface GameObject {
    Vertex pos();

    Vertex velocity();

    double width();

    double height();

    void paintTo(Graphics g, Vertex translate);

    default void move() {
        pos().add(velocity());
    }

    default boolean isAbove(double y) {
        return pos().y + height() < y;
    }

    default boolean isAbove(GameObject that) {
        return isAbove(that.pos().y);
    }

    default boolean isUnderneath(GameObject that) {
        return that.isAbove(this);
    }

    default boolean isLeftOf(double x) {
        return pos().x + width() < x;
    }

    default boolean isLeftOf(GameObject that) {
        return isLeftOf(that.pos().x);
    }

    default boolean isRightOf(GameObject that) {
        return that.isLeftOf(this);
    }

    default boolean touches(GameObject that) {
        return !(isAbove(that) || isUnderneath(that) || isLeftOf(that) || isRightOf(that));
    }
}
