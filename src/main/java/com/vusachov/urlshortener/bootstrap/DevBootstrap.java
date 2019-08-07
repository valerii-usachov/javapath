package com.vusachov.urlshortener.bootstrap;

import com.vusachov.urlshortener.domain.Hash;
import com.vusachov.urlshortener.domain.Tag;
import com.vusachov.urlshortener.domain.Url;
import com.vusachov.urlshortener.domain.User;
import com.vusachov.urlshortener.repositories.HashRepository;
import com.vusachov.urlshortener.repositories.TagRepository;
import com.vusachov.urlshortener.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Profile("dbinit")
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private HashRepository hashRepository;
    private TagRepository tagRepository;

    public DevBootstrap(UserRepository userRepository, HashRepository hashRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.hashRepository = hashRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        hashRepository.saveAll(getHashes());
    }

    private List<Hash> getHashes() {
        List<Hash> hashes = new ArrayList<>(2);

        Optional<User> userOptional = userRepository.findByUsername("test@user.com");

        if (!userOptional.isPresent()) {
            throw new RuntimeException("Expected User Not Found");
        }

        Optional<Tag> programmingTag = tagRepository.findByName("programming");
        if (!programmingTag.isPresent()) {
            throw new RuntimeException("Expected Tag Not Found");
        }

        Optional<Tag> javaTag = tagRepository.findByName("java");
        if (!javaTag.isPresent()) {
            throw new RuntimeException("Expected Tag Not Found");
        }

        Optional<Tag> educationTag = tagRepository.findByName("education");
        if (!educationTag.isPresent()) {
            throw new RuntimeException("Expected Tag Not Found");
        }

        //Url 1
        Url springIoUrl = new Url("https://spring.io/");

        //Hash 1 for springIoUrl
        Hash hash1 = new Hash(springIoUrl, "640c68796f50a8dd67088901a6cf18f1");
        hash1.setUser(userOptional.get());
        hash1.getTags().add(programmingTag.get());
        hash1.getTags().add(javaTag.get());

        hashes.add(hash1);

        //Hash 2 for springIoUrl
        Hash hash2 = new Hash(springIoUrl, "sprIo");
        hash2.setUser(userOptional.get());
        hash2.getTags().add(javaTag.get());

        hashes.add(hash2);

        //Url 2
        Url baeldungUrl = new Url("https://www.baeldung.com/jpa-hibernate-projections");

        //Hash 1 for baeldungUrl by anon User
        Hash hash3 = new Hash(baeldungUrl, "jpaHibPr");
        hash3.getTags().add(educationTag.get());

        hashes.add(hash3);

        return hashes;
    }
}

