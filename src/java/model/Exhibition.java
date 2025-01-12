/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Exhibition {
    private int exhibitionId;
    private String name;
    private Date startDate;
    private Date endDate;

    public Exhibition() {
    }

    public Exhibition(int exhibitionId, String name, Date startDate, Date endDate) {
        this.exhibitionId = exhibitionId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(int exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Exhibition{" + "exhibitionId=" + exhibitionId + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
    
    
    
}
