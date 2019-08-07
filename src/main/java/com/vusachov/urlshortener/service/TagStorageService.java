package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.domain.Tag;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagStorageService implements TagStorage {

    private final TagRepository tagRepository;

    public TagStorageService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

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
