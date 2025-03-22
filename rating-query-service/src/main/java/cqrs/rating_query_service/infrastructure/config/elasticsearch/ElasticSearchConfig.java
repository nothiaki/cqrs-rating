package cqrs.rating_query_service.infrastructure.config.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@Configuration
public class ElasticSearchConfig {

  @Value("${elasticsearch.host}")
  private String elasticsearchHost;

  @Bean
  public ElasticsearchClient elasticsearchClient() {
    RestClient restClient = RestClient
      .builder(HttpHost.create(elasticsearchHost))
      .build();

    ElasticsearchTransport transport = new RestClientTransport(
      restClient, new JacksonJsonpMapper()
    );

    return new ElasticsearchClient(transport);
  }

}
