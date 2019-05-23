package com.cine.api.service;

import com.cine.api.cache.JCache;
import com.cine.api.client.TMDBApi;
import com.cine.api.model.GenreList;
import com.cine.api.model.Movie;
import com.cine.api.model.MovieSearchList;
import lombok.extern.java.Log;
import org.apache.commons.collections.CollectionUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.PostConstruct;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.stream.Collectors;

@ApplicationScoped
@Log
@CacheDefaults(cacheName = "default")
public class MovieService implements Serializable {

	private static final long serialVersionUID = -5920346214772878192L;

	GenreList genreList;

	@PostConstruct
	void init() {

		this.genreList = this.tmdbApi.getGenreList();
	}

	@Inject
	@RestClient
	TMDBApi tmdbApi;

	public Movie getMovieLatest() {

		return this.tmdbApi.getMovieLatest();
	}

	@JCache
	@CacheResult
	public MovieSearchList getMovieUpcoming(final Integer page) {

		final MovieSearchList movieUpcoming = this.tmdbApi.getMovieUpcoming(page);

		this.populateGenres(movieUpcoming);

		return movieUpcoming;
	}

	private void populateGenres(final MovieSearchList movieUpcoming) {

		if(movieUpcoming != null && this.genreList != null && CollectionUtils.isNotEmpty(this.genreList.getGenres())) {
			movieUpcoming.getResults().stream().forEach(movie -> movie.setGenres(this.genreList.getGenres().stream().filter(genre -> movie.getGenreIds().contains(genre.getId())).collect(Collectors.toList())));
		}
	}

	@JCache
	@CacheResult
	public Movie getMovieById(final Integer movieId, final boolean appendVideosToResponse) {

		return this.tmdbApi.getMovieById(movieId, !appendVideosToResponse ? null : "videos");
	}

	@JCache
	@CacheResult
	public MovieSearchList getSearchMovie(final String query, final Integer year, final Integer primaryReleaseYear, final Integer page) {

		final MovieSearchList movieUpcoming = this.tmdbApi.getSearchMovie(query, year, primaryReleaseYear, page);

		this.populateGenres(movieUpcoming);

		return movieUpcoming;

	}

	@JCache
	@CacheResult
	public GenreList getGenreList() {

		return this.tmdbApi.getGenreList();
	}

}
