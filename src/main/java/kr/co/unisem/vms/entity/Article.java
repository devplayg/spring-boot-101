package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="articles")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="article_id")
    @JsonProperty("article_id")
    private int articleId;

    @Column(name="title")
    @NotBlank(message = "username can't empty!")
    private String title;

    @Column(name="category")
    private Integer category;

    @Column(name="date")
    private LocalDateTime date;
}
