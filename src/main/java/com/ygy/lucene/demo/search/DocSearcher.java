package com.ygy.lucene.demo.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Paths;

/**
 * @author yuguangyuan
 * @date 2020-05-29 16:53
 */
public class DocSearcher {

    public void doSearch(String indexDir , String queryStr) throws Exception {
        Directory directory = FSDirectory.open(Paths.get(indexDir));
        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new StandardAnalyzer();
        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse(queryStr);

        long startTime = System.currentTimeMillis();
        TopDocs docs = searcher.search(query,10);

        System.out.println("查找"+queryStr+"所用时间："+(System.currentTimeMillis()-startTime));
        System.out.println("查询到"+docs.totalHits+"条记录");


        //遍历查询结果
        for(ScoreDoc scoreDoc : docs.scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            String content = doc.get("content");
            System.out.println(content);
        }
        reader.close();
    }
}
