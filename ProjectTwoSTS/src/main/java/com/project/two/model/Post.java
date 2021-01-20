package com.project.two.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="content", nullable=false, length=1000)
	private String content;
	
	// mappedBy takes the name of the variable in a model (the owner of the relation), NOT the name of the column
	@OneToMany(mappedBy="post", fetch=FetchType.EAGER)
	private Set<ImageLink> images;
	
	@Column(name="video", length=256)
	private String video;
	
	@OneToMany(mappedBy="post", fetch=FetchType.EAGER)
	private Set<Comment> comments;
	
    // Only use FetchType.LAZY if we won't need this after we close the session
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(
        name="post_likes", 
        joinColumns=@JoinColumn(name="post_id"), 
        inverseJoinColumns=@JoinColumn(name="user_id")
    )
	private Set<User> likes;
	
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
    private User user;

    public Post() {}
    
    public Post(int id) {
    	this.id = id;
    }

	public Post(String content, Set<ImageLink> images, String video, Set<Comment> comments, Set<User> likes, User user) {
		super();
		this.content = content;
		this.images = images;
		this.video = video;
		this.comments = comments;
		this.user = user;
		this.likes = likes;
	}

	public Post(int id, String content, Set<ImageLink> images, String video, Set<Comment> comments, Set<User> likes, User user) {
		super();
		this.id = id;
		this.content = content;
		this.images = images;
		this.video = video;
		this.comments = comments;
		this.user = user;
		this.likes = likes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<ImageLink> getImages() {
		return images;
	}

	public void setImages(Set<ImageLink> images) {
		this.images = images;
	}

	public String getVideo() {
		return video;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Set<User> getLikes() {
		return likes;
	}

	public void setLikes(Set<User> likes) {
		this.likes = likes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		Post other = (Post) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [\n\tid=" + id + ",\n\tcontent=" + content + ",\n\timages=" + images + ",\n\tvideo=" + video + ",\n\tcomments="
				+ comments + ",\n\tlikes=" + likes + ",\n\tuser=" + user + "\n]";
	}
    
}
