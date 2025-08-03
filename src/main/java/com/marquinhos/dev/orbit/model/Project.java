package com.marquinhos.dev.orbit.model;

public class Project {
    private Integer id;
    private String name;
    private String description;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Project() {
        super();
    }

    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    };

    public String getDescription() {
        return description;
    };

    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
