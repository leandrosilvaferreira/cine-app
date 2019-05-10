package com.cine.api.service;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class Starter {

	@Inject
	MovieService movieService;


	void onStart(@Observes final StartupEvent ev) {

		this.movieService.getMovieUpcoming(1);
	}


}
