package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.domain.Tag;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.repositories.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TagStorageService implements TagStorage {

    private final TagRepository tagRepository;

    @Override
    public Tag get(Long id) throws ResourceNotFoundException {
        return tagRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Tag> getAll() {
        List<Tag> tags = new ArrayList<>();
        tagRepository.findAll().iterator().forEachRemaining(tags::add);

        return tags;
    }
}
