package com.project.two.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="image_links")
public class ImageLink {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="link", nullable=false, length=256)
	private String link;
	
	@JsonProperty(access=Access.WRITE_ONLY) // Avoid infinite loop when converting posts to json
    @ManyToOne
    @JoinColumn(name="post_id", referencedColumnName="id", nullable=false)
    private Post post;

    public ImageLink() {}
    
    public ImageLink(String link, Post post) {
		super();
		this.link = link;
		this.post = post;
	}
    
	public ImageLink(int id, String link, Post post) {
		super();
		this.id = id;
		this.link = link;
		this.post = post;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		ImageLink other = (ImageLink) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ImageLink [id=" + id + ", link=" + link + ", post=" + post + "]";
	}
    
}
