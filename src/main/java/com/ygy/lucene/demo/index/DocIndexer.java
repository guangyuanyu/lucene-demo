package com.ygy.lucene.demo.index;

import com.ygy.lucene.demo.Doc;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuguangyuan
 * @date 2020-05-29 16:50
 */
public class DocIndexer {

    public void indexDocs(List<Doc> docs, String indexDir) throws Exception {
        long startTime = System.currentTimeMillis();//记录索引开始时间

        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = FSDirectory.open(Paths.get(indexDir));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter indexWriter = new IndexWriter(directory, config);

        for(int i = 0; i < docs.size();i++){
            Document doc = new Document();
            //添加字段
            doc.add(new IntField("id", docs.get(i).getId(), Field.Store.YES));
            doc.add(new TextField("title", docs.get(i).getTitle(), Field.Store.YES));
            doc.add(new TextField("content", docs.get(i).getContent(), Field.Store.YES));
            indexWriter.addDocument(doc);
        }

        indexWriter.commit();
        System.out.println("共索引了"+indexWriter.numDocs()+"个文件");
        indexWriter.close();
        System.out.println("创建索引所用时间："+(System.currentTimeMillis()-startTime)+"毫秒");
    }
}
