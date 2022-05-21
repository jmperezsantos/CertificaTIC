package org.certificatic.collectionsexample;

public class UserEntity {

    //Propiedades privadas
    private int id;
    private String name;
    private String lastname;
    private int age;
    private boolean active;

    //Constructor por defecto
    public UserEntity() {
    }

    //Constructor con todas las propiedades
    public UserEntity(int id,
                      String name,
                      String lastname,
                      int age,
                      boolean active) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.active = active;
    }

    //Métodos de acceso

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //Método toString para 'Depuración'
    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", active=" + active +
                '}';
    }
}
