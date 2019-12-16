package buaa.group6.scienceshare.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Author {
    private String name;

    @Field("id")
    private String i;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return i;
    }

    public void setId(String id) {
        this.i = id;
    }
}
