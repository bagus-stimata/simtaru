package com.desgreen.gov.database.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_prasarana_pics")
public class TbPrasaranaPics {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id = 0;
	@Column(name = "title")
	private String title = "";
	@Column(name = "description")
	private String description = "";
	@Column(name = "image_name")
	private String imageName = "";


	@ManyToOne
    @JoinColumn(name = "prasarana_bean", referencedColumnName = "id")
	private TbPrasarana prasaranaBean;

	@Transient
	private int tempInt1 = 0;

	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";



    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName the imageName to set
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * @return TbPrasarana return the prasaranaBean
     */
    public TbPrasarana getPrasaranaBean() {
        return prasaranaBean;
    }

    /**
     * @param prasaranaBean the prasaranaBean to set
     */
    public void setPrasaranaBean(TbPrasarana prasaranaBean) {
        this.prasaranaBean = prasaranaBean;
    }

    /**
     * @return int return the tempInt1
     */
    public int getTempInt1() {
        return tempInt1;
    }

    /**
     * @param tempInt1 the tempInt1 to set
     */
    public void setTempInt1(int tempInt1) {
        this.tempInt1 = tempInt1;
    }

    /**
     * @return LocalDateTime return the created
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /**
     * @return LocalDateTime return the lastModified
     */
    public LocalDateTime getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return String return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbPrasaranaPics other = (TbPrasaranaPics) obj;
		if (id != other.id)
			return false;
		return true;
	}

}