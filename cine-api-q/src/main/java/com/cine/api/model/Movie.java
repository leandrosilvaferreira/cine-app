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
public class Movie implements Serializable {

	private static final long serialVersionUID = 8749813792601807627L;

	@JsonbProperty("adult")
	private Boolean adult;

	@JsonbProperty("backdrop_path")
	private String backdropPath;

	@JsonbProperty("belongs_to_collection")
	private BelongsToCollection belongsToCollection;

	@JsonbProperty("budget")
	private Integer budget;

	@JsonbProperty("genres")
	private List<Genre> genres;

	@JsonbProperty("genre_ids")
	private List<Integer> genreIds;

	@JsonbProperty("homepage")
	private String homepage;

	@JsonbProperty("id")
	private Integer id;

	@JsonbProperty("imdb_id")
	private String imdbId;

	@JsonbProperty("original_language")
	private String originalLanguage;

	@JsonbProperty("original_title")
	private String originalTitle;

	@JsonbProperty("overview")
	private String overview;

	@JsonbProperty("popularity")
	private Double popularity;

	@JsonbProperty("poster_path")
	private String posterPath;

	@JsonbProperty("production_companies")
	private List<ProductionCompany> productionCompanies;

	@JsonbProperty("production_countries")
	private List<ProductionCountry> productionCountries;

	@JsonbProperty("release_date")
	private String releaseDate;

	@JsonbProperty("revenue")
	private Integer revenue;

	@JsonbProperty("runtime")
	private Integer runtime;

	@JsonbProperty("spoken_languages")
	private List<SpokenLanguage> spokenLanguages;

	@JsonbProperty("status")
	private String status;

	@JsonbProperty("tagline")
	private String tagline;

	@JsonbProperty("title")
	private String title;

	@JsonbProperty("video")
	private Boolean video;

	@JsonbProperty("vote_average")
	private Double voteAverage;

	@JsonbProperty("vote_count")
	private Integer voteCount;

	@JsonbProperty("videos")
	private VideoList videos;

}

