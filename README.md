# Blog Post & Comment REST API

This project is a simple RESTful API for a blog platform that manages blog posts and their associated comments. Built with Spring Boot, Spring Data JPA, and PostgreSQL, the API allows you to create, retrieve, update, and delete blog posts as well as add and remove comments on posts.

## Features

- **Blog Posts:**  
  Create, read, update, and delete blog posts.
- **Comments:**  
  Create and delete comments for specific blog posts.
- **Relationships:**  
  One-to-many relationship between BlogPosts and Comments.
- **Automatic Timestamping:**  
  Uses Hibernate's `@CreationTimestamp` to auto-populate creation times.
- **Circular Reference Handling:**  
  Uses Jackson annotations to manage bi-directional relationships.

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Jackson (for JSON serialization)
