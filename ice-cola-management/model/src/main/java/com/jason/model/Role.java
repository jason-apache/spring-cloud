package com.jason.model;

import javax.persistence.Id;
import java.io.Serializable;

public class Role implements Serializable {

    @Id
    private Long id;
    private String name;
    private String title;
    private String world;
    private String img;

    public Role() {
    }

    public Role(Long id, String name, String title, String world, String img) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.world = world;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Role setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getWorld() {
        return world;
    }

    public Role setWorld(String world) {
        this.world = world;
        return this;
    }

    public String getImg() {
        return img;
    }

    public Role setImg(String img) {
        this.img = img;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        if (title != null ? !title.equals(role.title) : role.title != null) return false;
        if (world != null ? !world.equals(role.world) : role.world != null) return false;
        return img != null ? img.equals(role.img) : role.img == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (world != null ? world.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", world='" + world + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
