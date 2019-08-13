package com.vusachov.urlshortener.repositories;

import com.vusachov.urlshortener.entity.AccountType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountTypeRepository extends CrudRepository<AccountType, String> {

    Optional<AccountType> findByUrlExpPeriod(int urlExpPeriod);
}
