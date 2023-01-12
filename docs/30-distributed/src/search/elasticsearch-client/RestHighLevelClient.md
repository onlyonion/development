org.elasticsearch.client.RestHighLevelClient


## demo
```java
    // index
    Dict doc =  Dict.create(); 
    IndexRequest indexRequest = new IndexRequest("index_name");
    indexRequest.source(JSON.toJSONString(doc), XContentType.JSON);
    try {
        client.index(indexRequest, RequestOptions.DEFAULT);
    } catch (IOException e) {
        
    }
    // update
    UpdateRequest updateRequest = new UpdateRequest("index_name", "index_id");
    updateRequest.doc(JSON.toJSONString(doc), XContentType.JSON);
    updateRequest.docAsUpsert(true);
    client.update(updateRequest, RequestOptions.DEFAULT);

    // delete
    DeleteRequest deleteRequest = new DeleteRequest("index_name", "index_id");
    client.delete(deleteRequest, RequestOptions.DEFAULT);

    // search
    SearchRequest searchRequest = new SearchRequest("index_name");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

    sourceBuilder.timeout(new TimeValue(10, TimeUnit.SECONDS));
    sourceBuilder.from((query.getPage() - 1) * query.getRows());
    sourceBuilder.size(query.getRows());

    BoolQueryBuilder top1 = QueryBuilders.boolQuery();
    top1.filter(QueryBuilders.termQuery("status", "enabled"));
    top1.filter(QueryBuilders.termQuery("hidden", false));
    top1.filter(QueryBuilders.termsQuery("phone", phones));

    top1.filter(QueryBuilders.rangeQuery("points").gte(query.getPointsStart()));
    top1.filter(QueryBuilders.rangeQuery("points").lte(query.getPointsEnd()));
    
    sourceBuilder.query(top1);
    sourceBuilder.sort(new ScoreSortBuilder());

    searchRequest.source(sourceBuilder);
    SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

    List<String> ids = Arrays.stream(response.getHits().getHits()).map(SearchHit::getId).collect(Collectors.toList());

```