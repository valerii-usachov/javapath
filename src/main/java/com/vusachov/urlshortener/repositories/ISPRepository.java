package com.vusachov.urlshortener.repositories;

import com.vusachov.urlshortener.entity.ISP;
import org.springframework.data.repository.CrudRepository;

public interface ISPRepository extends CrudRepository<ISP, String> {
}
