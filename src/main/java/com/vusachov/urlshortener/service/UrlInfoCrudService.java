package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.ISP;
import com.vusachov.urlshortener.entity.Url;
import com.vusachov.urlshortener.entity.UrlHost;
import com.vusachov.urlshortener.repositories.ISPRepository;
import com.vusachov.urlshortener.repositories.UrlHostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UrlInfoCrudService implements UrlInfoService {

    UrlHostRepository urlHostRepository;
    ISPRepository ispRepository;

    @Override
    synchronized public void createUrlHost(Url url, String ip, String type, String ispId, String ispName) {

        ISP isp = ispRepository.findById(ispId).orElse(null);

        if (isp == null) {
            isp = ispRepository.save(new ISP(ispId, ispName));
        }

        UrlHost urlHost = new UrlHost(isp, url);
        urlHost.setIp(ip);
        urlHost.setType(type);

        urlHostRepository.save(urlHost);
    }

    @Override
    public List<UrlHost> getAllForUrl(Url url) {
        return urlHostRepository.findAllByUrl(url);
    }
}
