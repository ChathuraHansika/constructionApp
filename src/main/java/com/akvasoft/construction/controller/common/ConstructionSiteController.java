package com.akvasoft.construction.controller.common;

import com.akvasoft.construction.dto.common.Response;
import com.akvasoft.construction.dto.common.ProjectDetailsDto;
import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/construction-site")
public class ConstructionSiteController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/sites", method = RequestMethod.GET)
    public Response findAllSites() {
        Response response = new Response();
        response.setResult(commonService.getConstructionSites());
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/saveSite", method = RequestMethod.POST)
    public Response saveSite(@Validated @RequestBody ProjectDetailsDto projectDetailsDto) {
        Response response = new Response();
        response.setResult(commonService.saveProject(projectDetailsDto));
        response.setSuccess(true);
        return response;

    }

    @RequestMapping(value = "/searchClient", method = RequestMethod.GET)
    public Response saveSite(@RequestParam(name = "name") String name) {
        Response response = new Response();
        response.setResult(commonService.searchClientByName(name));
        response.setSuccess(true);
        return response;

    }

}
