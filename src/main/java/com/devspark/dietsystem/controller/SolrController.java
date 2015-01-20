package com.devspark.dietsystem.controller;

import com.devspark.dietsystem.util.SolrConnectionService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * User: dfanaro
 * Date: 6/25/13
 * Time: 3:40 PM
 */
@Controller
public class SolrController {

    private static final String DOCUMENTS_DELETED = "The documents were deleted from Solr";
    private static final String COMMITTED = "Solr changes committed to the server";
    private static final String ADDED = "Documents added to Solr";

    private SolrConnectionService solrService;

    @Autowired
    public SolrController(SolrConnectionService solrService) {
        this.solrService = solrService;
    }

    @RequestMapping(value = "/solr/deleteDocuments", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteDocuments() throws IOException, SolrServerException {
        solrService.deleteDocuments();
        return DOCUMENTS_DELETED;
    }

    @RequestMapping(value = "/solr/commit", method = RequestMethod.GET)
    @ResponseBody
    public String commitInSolr() throws IOException, SolrServerException {
        solrService.doCommit();
        return COMMITTED;
    }

    @RequestMapping(value = "/solr/addDocument", method = RequestMethod.PUT)
    @ResponseBody
    public String addDocument() throws IOException, SolrServerException {
        solrService.addDocument();
        return ADDED;
    }

    @RequestMapping(value = "/solr/addDocuments", method = RequestMethod.PUT)
    @ResponseBody
    public String addDocuments() throws IOException, SolrServerException {
        solrService.addDocuments();
        return ADDED;
    }

    @RequestMapping(value = "/solr/exeSearch", method = RequestMethod.GET)
    @ResponseBody
    public String exeSearch() throws IOException, SolrServerException {
        solrService.executeSearch();
        return ADDED;
    }
}
