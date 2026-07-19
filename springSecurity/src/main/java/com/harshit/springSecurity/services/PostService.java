package com.harshit.springSecurity.services;

import com.harshit.springSecurity.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePostById(PostDTO inputPost, Long postId);
}
