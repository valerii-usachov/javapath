package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.Tag;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;

import java.util.List;

public interface TagService {

    Tag get(Long id) throws ResourceNotFoundException;

    List<Tag> getAll();
}
