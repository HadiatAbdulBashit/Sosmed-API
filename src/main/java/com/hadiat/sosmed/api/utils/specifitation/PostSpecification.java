package com.hadiat.sosmed.api.utils.specifitation;

import com.hadiat.sosmed.api.model.Post;
import com.hadiat.sosmed.api.model.User;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {
    public static Specification<Post> postByUser(User user) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user"), user);
    }
}
