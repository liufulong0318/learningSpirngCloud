package com.gxhy.springcloud.elastic_search.respository;

import com.gxhy.springcloud.elastic_search.Model.BlogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BlogRepository extends ElasticsearchRepository<BlogModel, String> {
    List<BlogModel> findByTitleContains(String keyword);
}
