package com.example.spi.localize2socialize.persistence;

import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOwnerIn(List<Account> owners);
}
