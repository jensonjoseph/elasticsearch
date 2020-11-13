package com.jensonjo.elasticsearch;

import com.jensonjo.elasticsearch.model.Book;
import com.jensonjo.elasticsearch.service.BookService;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ElasticsearchApplication implements CommandLineRunner {

	@Autowired
	private ElasticsearchOperations es;

	@Autowired
	private RestHighLevelClient restHighLevelClient;

	@Autowired
	private BookService bookService;

	public static void main(String args[]) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		printElasticSearchInfo();

		bookService.save(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
		bookService.save(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
		bookService.save(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));

		//fuzzey search
		Page<Book> books = bookService.findByAuthor("Rambabu", PageRequest.of(0,10));

		//List<Book> books = bookService.findByTitle("Elasticsearch Basics");

		books.forEach(x -> System.out.println(x));


	}

	//useful for debug, print elastic search details
	private void printElasticSearchInfo() {

		System.out.println("--ElasticSearch--");

//		RestClient cs = restHighLevelClient..getLowLevelClient();

	}

}
