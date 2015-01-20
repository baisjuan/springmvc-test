package com.devspark.dietsystem.util;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * User: dfanaro
 * Date: 6/25/13
 * Time: 1:52 PM
 */
@Service
public class SolrConnectionService {

    private String url = "http://localhost:8983/solr";
    private String deleteDocumentsQuery = "/update/?stream.body=<delete><query>*:*</query></delete>&commit=true";

    SolrServer server = new HttpSolrServer(url);

    public void deleteDocuments() throws IOException, SolrServerException {
        server.deleteByQuery("*:*");
    }

    public void doCommit() throws IOException, SolrServerException {
        server.commit();
    }

    public void addDocument() throws IOException, SolrServerException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "polaco", 1.0f);
        doc.addField("name", "test", 1.0f);
        doc.addField("price", 1);
        server.add(doc);
    }

    public void addDocuments() throws IOException, SolrServerException {
        for (int i = 0; i < 1000; ++i) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("cat", "book");
            doc.addField("id", "book-" + i);
            doc.addField("name", "The Legend of Po part " + i);
            server.add(doc);
            if (i % 100 == 0) server.commit();  // periodically flush
        }
    }

    public void executeSearch() throws SolrServerException {
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set(CommonParams.Q, "cat:book");
        params.set(CommonParams.DF, "edismax");
        params.set(CommonParams.START, "0");
        params.set(CommonParams.ROWS, "500");

        QueryResponse response = server.query(params);
        SolrDocumentList results = response.getResults();

        try{
            // Create file
            FileWriter fstream = new FileWriter("out.xml");
            BufferedWriter out = new BufferedWriter(fstream);
            for (int i = 0; i < results.size(); ++i) {
                out.write(String.valueOf(results.get(i)));
                out.write("\n");
            }
            //Close the output stream
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
