package com.example.spring_security_blog_app.controller;

import com.example.spring_security_blog_app.entity.Blog;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @PostMapping("/")
    public String postBlog(@RequestBody Blog blog){

        System.out.println("in post method");
        System.out.println(blog);
        return "Success";
    }

    @GetMapping("/")
    public Blog getBlog(@RequestParam int id){
        Blog blog = new Blog();
        blog.setBlogText("text1");
        blog.setBlogTitle("Title1");
        return blog;
    }
}
