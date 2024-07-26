package com.blogapp.payload;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.Date;

@Getter
@Setter
public class BlogPostDto {

    private Long id;

    @NonNull
    @Size(min = 3,message = "title should be consist of at least three letters")
    private String title;

    private String content;

    private Date date;
}
