package buaa.group6.scienceshare.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "magauthors")
public class Magauthor {
    @Id
    private String _id;

    private String id;

    private String name;

    @Field("normalized_name")
    private String normalizedName;

    private List<pubs> pubs;

    private int n_pubs;

    private int n_citation;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    public List<pubs> getPubs() {
        return pubs;
    }

    public void setPubs(List<pubs> pubs) {
        this.pubs = pubs;
    }

    public int getN_pubs() {
        return n_pubs;
    }

    public void setN_pubs(int n_pubs) {
        this.n_pubs = n_pubs;
    }

    public int getN_citation() {
        return n_citation;
    }

    public void setN_citation(int n_citation) {
        this.n_citation = n_citation;
    }
}
