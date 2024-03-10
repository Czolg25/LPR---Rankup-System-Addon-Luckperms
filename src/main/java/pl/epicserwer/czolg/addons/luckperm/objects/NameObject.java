package pl.epicserwer.czolg.addons.luckperm.objects;

import java.util.Objects;

public class NameObject {
    private final String name;

    public NameObject(){
        this(" ");
    }

    public NameObject(final String name) {
        if(name == null) throw new IllegalArgumentException("name cannot be null");
        if(name.isEmpty()) throw new IllegalArgumentException("name cannot be empty");

        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameObject that = (NameObject) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
