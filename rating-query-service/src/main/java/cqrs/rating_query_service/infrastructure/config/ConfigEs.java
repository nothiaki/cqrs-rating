package cqrs.rating_query_service.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "cqrs.rating_query_service.infrastructure.persistence.elasticsearch")
public class ConfigEs extends ElasticsearchConfiguration {

  @Override
  public ClientConfiguration clientConfiguration() {
      return ClientConfiguration.builder()
              //.connectedToLocalhost()
              .connectedTo("elasticsearch:9200")
              .build();
  }

}
