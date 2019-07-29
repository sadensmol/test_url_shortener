package me.sadensmol.urlshortener.model;

import javax.persistence.*;

@Entity
@Table(name = "url")
public class URL {

    //todo rework with own generator to have id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullUrl;

    public URL (){
    }
    public URL (String fullUrl){
        this.fullUrl = fullUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}