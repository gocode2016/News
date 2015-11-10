package cn.news.test;

import java.io.File;
import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;
/**
 * 对于中文来说，Lucene提供的search基本上不能使用，使用中文分词器替换即可
 * @author Johnny
 *
 */
public class SearchUtil {
	private IKAnalyzer analyzer = new IKAnalyzer();
    private Version Lucene_Version = Version.LUCENE_43;
    private Directory directory;
    private DirectoryReader reader = null;
    private String id = null;
    private String title = null;
    private String content = null;
    private String introduction = null;
    
    public SearchUtil() {
//        directory = new RAMDirectory();
        try {
        	                  
            directory = FSDirectory.open(new File("E:\\服创项目\\News\\index"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void index(LuceneData data) {
    	id = data.getId();                              
    	title = data.getTitle();                         
    	content = data.getContent();           
    	introduction = data.getIntroduction();
        IndexWriter writer = null;
        try {
            writer = new IndexWriter(directory,new IndexWriterConfig(Lucene_Version, analyzer));
            //writer.deleteAll();
            Document doc = null;
                doc = new Document();
                doc.add(new StringField("id",id,Store.YES));
                doc.add(new StringField("title", title,Store.YES));
                doc.add(new TextField("content", content, Store.YES));
                doc.add(new StringField("introduction",introduction, Store.YES));
                
                String et = title.substring(title.lastIndexOf("@")+1);
                System.out.println(et);
                /**
                 * 在Lucene4.x中，只能给域加权，部门给文档加权，如果要提高文档的加权，需要给
                 * 文档的每个域进行加权
                 * **/
                
                writer.addDocument(doc);
                writer.commit();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(writer!=null) writer.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public IndexSearcher getSearcher() {
        try {
            if(reader==null) {
                reader = DirectoryReader.open(directory);
            } else {
                DirectoryReader tr = DirectoryReader.openIfChanged(reader) ;
                if(tr!=null) {
                    reader.close();
                    reader = tr;
                }
            }
            return new IndexSearcher(reader);
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 指定field进行查询，termquery不能进行数字和日期的查询
     * 日期的查询需要转成数字进行查询，
     * 数字查询使用NumbericRangeQuery
     * @param field
     * @param introduction
     * @param num
     */
    public void searchByTerm(String field,String introduction,int num) {
        try {
            IndexSearcher searcher = getSearcher();
            Query query = new TermQuery(new Term(field,introduction));
            TopDocs tds = searcher.search(query, num);
            System.out.println("一共查询了:"+tds.totalHits);
            for(ScoreDoc sd:tds.scoreDocs) {
                Document doc = searcher.doc(sd.doc);
                System.out.println(doc.get("id")+"---->"+
                        doc.get("introduction")+"["+doc.get("title")+"]-->"+doc.get("content"));
            }
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void searchByTermRange(String field,String start,String end,int num) {
        try {
            IndexSearcher searcher = getSearcher();
            Query query = new TermRangeQuery(field,new BytesRef(start.getBytes()),new BytesRef(end.getBytes()) , true, true);
            TopDocs tds = searcher.search(query, num);
            System.out.println("一共查询了:"+tds.totalHits);
            for(ScoreDoc sd:tds.scoreDocs) {
                Document doc = searcher.doc(sd.doc);
                System.out.println(doc.get("id")+"---->"+
                        doc.get("introduction")+"["+doc.get("title")+"]-->"+doc.get("id")+","+
                        doc.get("attach")+","+doc.get("date"));
            }
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void searchByQueryParse(Query query,int num) {
        try {
            IndexSearcher searcher = getSearcher();
            TopDocs tds = searcher.search(query, num);
            System.out.println("一共查询了:"+tds.totalHits);
            for(ScoreDoc sd:tds.scoreDocs) {
                Document doc = searcher.doc(sd.doc);
                System.out.println(doc.get("id")+"---->"+
                        doc.get("introduction")+"["+doc.get("title")+"]-->"+doc.get("id")+","+
                        doc.get("attach")+","+doc.get("date")+"=="+sd.score);
            }
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /***如果想要获取为存储到索引中得值，可以根据ID去源文件中进行查找并返回**/
    public void searchPage(String query,int pageIndex,int pageSize) {
        try {
            IndexSearcher searcher = getSearcher();
            QueryParser parser = new QueryParser(Version.LUCENE_43,"content",analyzer);
            Query q =null;
            try {
                q = parser.parse(query);
            } catch (org.apache.lucene.queryparser.classic.ParseException e) {
                e.printStackTrace();
            }
            TopDocs tds = searcher.search(q, 500);
            ScoreDoc[] sds = tds.scoreDocs;
            int start = (pageIndex-1)*pageSize;
            int end = pageIndex*pageSize;
            if(end>=sds.length) end = sds.length;
            for(int i=start;i<end;i++) {
                Document doc = searcher.doc(sds[i].doc);
                String id = doc.get("id");
                int arrInt = -1;
                for(int j=0;j<1;j++){
                    if(id.equals(id)){
                        arrInt = j;
                        break;
                    }
                }
                
                System.out.println(sds[i].doc+":"+doc.get("introduction")+"-->"+doc.get("content"));
            }
            
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 根据页码和分页大小获取上一次的最后一个ScoreDoc
     */
    private ScoreDoc getLastScoreDoc(int pageIndex,int pageSize,Query query,IndexSearcher searcher) throws IOException {
        if(pageIndex==1)return null;//如果是第一页就返回空
        int num = pageSize*(pageIndex-1);//获取上一页的数量
        TopDocs tds = searcher.search(query, num);
        return tds.scoreDocs[num-1];
    }
    /***
     * 在使用时，searchAfter查询的是指定页数后面的数据，效率更高，推荐使用
     * @param query
     * @param pageIndex
     * @param pageSize
     */
    public void searchPageByAfter(String query,int pageIndex,int pageSize) {
        try {
            IndexSearcher searcher = getSearcher();
            QueryParser parser = new QueryParser(Version.LUCENE_43,"content",analyzer);
            Query q = null;
            try {
                q = parser.parse(query);
            } catch (org.apache.lucene.queryparser.classic.ParseException e) {
                e.printStackTrace();
            }
            //先获取上一页的最后一个元素
            ScoreDoc lastSd = getLastScoreDoc(pageIndex, pageSize, q, searcher);
            //通过最后一个元素搜索下页的pageSize个元素
            TopDocs tds = searcher.searchAfter(lastSd,q, pageSize);
            for(ScoreDoc sd:tds.scoreDocs) {
                Document doc = searcher.doc(sd.doc);
                String id = doc.get("id");
                int arrInt = -1;
                for(int j=0;j<1;j++){
                    if(id.equals(id)){
                        arrInt = j;
                        break;
                    }
                }
                System.out.println(doc.get("introduction")+"-->"+content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
