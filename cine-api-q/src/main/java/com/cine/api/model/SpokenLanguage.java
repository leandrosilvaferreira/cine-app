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

@RegisterForReflection
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SpokenLanguage implements Serializable {

	private static final long serialVersionUID = 3324966372021259649L;

	@JsonbProperty("iso_639_1")
	private String iso6391;

	@JsonbProperty("name")
	private String name;

}
