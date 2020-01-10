package com.example.demo;

import org.springframework.data.repository.CrudRepository;

//public class TwitterRepository {
    public interface TwitterRepository extends CrudRepository<Twitter, Long> {

    }
//}
