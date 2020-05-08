package com.wxhj.cloud.elasticsearch.base;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.wxhj.cloud.elasticsearch.annotation.ESColumn;
import com.wxhj.cloud.elasticsearch.annotation.ESDocument;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxpjf
 * @date 2020/4/24 14:30
 */
//@Component
public abstract class ElasticSearchBaseMapper<T extends ElasticSearchBaseEntity> {
    @Resource
    RestHighLevelClient restHighLevelClient;


    // private ESDocument esDocument = this.getTClass().getAnnotation(ESDocument.class);

    private String esIndex = this.getTClass().getAnnotation(ESDocument.class).index();


    // private Field[] fields=this.getTClass().getFields();
    //            Optional.ofNullable(this.getTClass().getAnnotation(ESDocument.class)).orElseThrow(() -> {
//                throw new RuntimeException("es的模型必须有ESDocument注解");
//            }).index();


    private Field[] fields = this.getTClass().getDeclaredFields();


    private static Integer SHARD_NUMBER = 3;
    private static Integer REPLICAS_NUMBER = 2;
    private static String DEF_COLUMN = "text";
    private static boolean DEF_INDEX = true;

    private T buildId(T t, String id) {
        if (t == null) {
            return null;
        }
        t.putId(id);
        return t;
    }

    private void indexShards(CreateIndexRequest request) {
        request.settings(Settings.builder().put("index.number_of_shards", SHARD_NUMBER)
                .put("index.number_of_replicas", REPLICAS_NUMBER));
    }

    public void createIndex() throws IOException {
        //
        if (existIndex()) {
            return;
        }
        //
        Map<String, Map<String, Object>> properties = new HashMap<>();
        for (Field fieldTemp : fields) {
            Map<String, Object> columnTypeMap;
            ESColumn esColumn = fieldTemp.getAnnotation(ESColumn.class);
            if (esColumn == null) {
                columnTypeMap = ImmutableMap.of("type", DEF_COLUMN, "index", DEF_INDEX);
            } else {
                columnTypeMap = ImmutableMap.of("type", esColumn.columnType().getColumnType(), "index", esColumn.index());
            }
            properties.put(fieldTemp.getName(), columnTypeMap);
        }
        //
        CreateIndexRequest request = new CreateIndexRequest(this.esIndex);
        indexShards(request);
        Map<String, Object> indexMap = ImmutableMap.of("dynamic", false, "properties", properties);
        request.mapping(JSON.toJSONString(indexMap), XContentType.JSON);
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }


    //根据id查询
    public T selectById(String id) throws IOException {

        GetRequest getRequest = new GetRequest(this.esIndex, id);
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        T t = JSON.parseObject(getResponse.getSourceAsString(), this.getTClass());
        return buildId(t, getResponse.getId());
    }

    //根据条件查询
    public List<T> listByQuery(QueryBuilder queryBuilder) throws IOException {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(queryBuilder);
        SearchRequest request = new SearchRequest(this.esIndex);
        request.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        SearchHit[] searchHitList = search.getHits().getHits();
        List<T> tlist = new ArrayList<>(searchHitList.length);
        for (SearchHit searchHitTemp : searchHitList) {
            T tTemp = JSON.parseObject(searchHitTemp.getSourceAsString(), this.getTClass());
            tlist.add(buildId(tTemp, searchHitTemp.getId()));
        }
        return tlist;
    }

    public List<T> listByQuery(T t) throws IllegalAccessException, IOException {
        MatchQueryBuilder queryBuilders = generateQueryBuilder(t);
        return listByQuery(queryBuilders);
    }


    //
//    public void replace(T t) throws IOException {
//        IndexRequest request = new IndexRequest(this.esIndex).id(t.fetchId());
//        //request
//        request.source(JSON.toJSONString(t), XContentType.JSON);
//        restHighLevelClient.index(request, RequestOptions.DEFAULT);
//    }

    public void insertBatch(List<T> tList) throws IOException {
        BulkRequest request = new BulkRequest();
        tList.forEach(q ->
                request.add(new IndexRequest(this.esIndex).id(q.fetchId())
                        .source(JSON.toJSONString(q)
                                , XContentType.JSON))
        );
        restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
    }

    public void deleteById(String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(this.esIndex).id(id);
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

    }

    public void deleteBatch(List<String> idList) throws IOException {
        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(
                new DeleteRequest(this.esIndex).id(item)
        ));
        restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
    }

    //AbstractQueryBuilder builder
    public void deleteByQuery(QueryBuilder queryBuilder) throws IOException {
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(this.esIndex);
        deleteByQueryRequest.setQuery(queryBuilder);
        restHighLevelClient.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
    }

    public void deleteByQuery(T t) throws IOException, IllegalAccessException {

        MatchQueryBuilder matchQueryBuilder = generateQueryBuilder(t);
        deleteByQuery(matchQueryBuilder);
    }

    private MatchQueryBuilder generateQueryBuilder(T t) throws IllegalAccessException {
        MatchQueryBuilder queryBuilders = null;
        for (Field fieldTemp : fields) {
            Object objectTemp = fieldTemp.get(t);
            if (objectTemp != null) {
                queryBuilders = QueryBuilders.matchQuery(fieldTemp.getName(), objectTemp);
            }
        }
        return queryBuilders;
    }

    public void deleteIndex(String indexName) throws IOException {
        if (!this.existIndex()) {
            return;
        }
        restHighLevelClient.indices().delete(new DeleteIndexRequest(indexName), RequestOptions.DEFAULT);
    }

    /**
     * @return 存在返回true不存在返回false
     * @throws IOException
     */
    public boolean existIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest(this.esIndex);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        request.indicesOptions(IndicesOptions.strictExpandOpen());
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    public void update(T t) throws IOException {
        UpdateRequest request = new UpdateRequest(this.esIndex, t.fetchId());
        request.doc(JSON.toJSONString(t), XContentType.JSON);
        restHighLevelClient.update(request, RequestOptions.DEFAULT);
    }

    public void upsert(T t) throws IOException {

        UpdateRequest request = new UpdateRequest(this.esIndex, t.fetchId());
        request.doc(JSON.toJSONString(t), XContentType.JSON)
                .upsert(JSON.toJSONString(t), XContentType.JSON);
        restHighLevelClient.update(request, RequestOptions.DEFAULT);
    }

    protected abstract Class<T> getTClass();

}
