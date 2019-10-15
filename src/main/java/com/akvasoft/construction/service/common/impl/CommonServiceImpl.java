package com.akvasoft.construction.service.common.impl;

import com.akvasoft.construction.dto.common.ClientDto;
import com.akvasoft.construction.dto.common.ProjectDetailsDto;
import com.akvasoft.construction.dto.common.Response;
import com.akvasoft.construction.dto.common.SiteDto;
import com.akvasoft.construction.entity.Client;
import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.repo.ClientRepo;
import com.akvasoft.construction.repo.ConstructionSiteRepo;
import com.akvasoft.construction.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private ConstructionSiteRepo constructionSiteRepo;

    @Autowired
    private ClientRepo clientRepo;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SiteDto> getConstructionSites() {
        return constructionSiteRepo.findAll().stream().map(ConstructionSite::getSiteDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String saveProject(ProjectDetailsDto dto) {
        Client client = saveClient(dto.getClientEntity());
        saveConstructionSite(dto.getSiteEntity(client));
        return "Successfully saved!";
    }

    @Override
    public ClientDto searchClientByName(String name) {
        Client client = clientRepo.findClientByNameEquals(name);
        if (client != null) {
            return client.getClientDto();
        }
        return new ClientDto();
    }

    private void saveConstructionSite(ConstructionSite siteEntity) {
        constructionSiteRepo.save(siteEntity);
    }

    private Client saveClient(Client clientEntity) {
        Client client = clientRepo.findClientByNameEquals(clientEntity.getName());
        if (client != null) {
            clientEntity.setClientId(client.getClientId());
        }
        return clientRepo.save(clientEntity);
    }

    @Override
    public Response makeResponse(boolean status, Object res, String error, String code) {
        Response response = new Response();
        response.setResult(res);
        response.setSuccess(status);
        response.setErrorMessage(error);
        response.setErrorCode(code);
        return response;
    }


}
