package com.akvasoft.construction.service.common;

import com.akvasoft.construction.dto.common.ClientDto;
import com.akvasoft.construction.dto.common.ProjectDetailsDto;
import com.akvasoft.construction.dto.common.Response;
import com.akvasoft.construction.dto.common.SiteDto;
import com.akvasoft.construction.entity.Client;
import com.akvasoft.construction.entity.ConstructionSite;

import java.util.List;

public interface CommonService {

    List<SiteDto> getConstructionSites();
    String saveProject(ProjectDetailsDto projectDetailsDto);
    ClientDto searchClientByName(String name);
    Response makeResponse(boolean status, Object res, String error, String code);
}
