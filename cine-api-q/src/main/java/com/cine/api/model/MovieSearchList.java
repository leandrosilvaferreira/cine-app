package com.cine.api.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.util.List;

@RegisterForReflection
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieSearchList implements Serializable {


	private static final long serialVersionUID = -5590889344293501821L;

	@JsonbProperty("id")
	private Integer id;

	@JsonbProperty("results")
	private List<Movie> results;

	@JsonbProperty("page")
	private Integer page;

	@JsonbProperty("total_results")
	private Integer totalResults;

	@JsonbProperty("dates")
	private MovieIntervalDates dates;

	@JsonbProperty("total_pages")
	private Integer totalPages;

}
