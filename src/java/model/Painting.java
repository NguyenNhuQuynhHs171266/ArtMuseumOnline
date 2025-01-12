/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Painting {
    
     private int paintingId;
    private String title;
    private String artist;
    private String description;
    private int yearCreated;
    private String imagePath;
     private List<Exhibition> exhibitions;

    public Painting() {
        this.exhibitions = new ArrayList<>(); 
    }

    public Painting(int paintingId, String title, String artist, String description, int yearCreated, String imagePath, List<Exhibition> exhibitions) {
        this.paintingId = paintingId;
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.yearCreated = yearCreated;
        this.imagePath = imagePath;
        this.exhibitions = exhibitions;
    }

    public int getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(int paintingId) {
        this.paintingId = paintingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Exhibition> getExhibitions() {
        return exhibitions;
    }

    public void setExhibitions(List<Exhibition> exhibitions) {
        this.exhibitions = exhibitions;
    }

    @Override
    public String toString() {
        return "Painting{" + "paintingId=" + paintingId + ", title=" + title + ", artist=" + artist + ", description=" + description + ", yearCreated=" + yearCreated + ", imagePath=" + imagePath + ", exhibitions=" + exhibitions + '}';
    }
    
    
    

}