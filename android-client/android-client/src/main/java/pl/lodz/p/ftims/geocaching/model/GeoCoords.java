package pl.lodz.p.ftims.geocaching.model;

/**
 * Created by michal on 11/19/14.
 */
public class GeoCoords {

    private int foo;  // TODO: Jak to zamodelować? Czy GPS API nie dostarcza własnych klas?
    private int bar;

    public GeoCoords() {
    }

    public GeoCoords(int foo, int bar) {
        this.foo = foo;
        this.bar = bar;
    }

    public int getFoo() {
        return foo;
    }

    public void setFoo(int foo) {
        this.foo = foo;
    }

    public int getBar() {
        return bar;
    }

    public void setBar(int bar) {
        this.bar = bar;
    }
}
