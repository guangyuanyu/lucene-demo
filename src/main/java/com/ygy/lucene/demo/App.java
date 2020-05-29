package com.ygy.lucene.demo;

import com.ygy.lucene.demo.index.DocIndexer;
import com.ygy.lucene.demo.search.DocSearcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    private static String indexDir = "/Users/ygy/files/lucene-data";

    private DocIndexer docIndexer = new DocIndexer();

    private DocSearcher docSearcher = new DocSearcher();

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    public void run() throws Exception {
        // index
        indexDocs();
        // search
        searchDocs();
    }

    private void indexDocs() throws Exception {
        Doc doc1 = new Doc(1, "java", "hello java");
        Doc doc2 = new Doc(2, "python", "hello python");
        Doc doc3 = new Doc(3, "php", "hello php");
        List<Doc> docs = new ArrayList<Doc>();
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);
        docIndexer.indexDocs(docs, indexDir);
    }

    private void searchDocs() throws Exception {
        System.out.println("==========search 'hello'=============");
        docSearcher.doSearch(indexDir, "hello");

        System.out.println("==========search 'java'=============");
        docSearcher.doSearch(indexDir, "java");
    }
}
