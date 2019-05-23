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
public class Video implements Serializable {

	private static final long serialVersionUID = -3534380015209579489L;

	@JsonbProperty("id")
	private String id;

	@JsonbProperty("iso_639_1")
	private String iso6391;

	@JsonbProperty("iso_3166_1")
	private String iso31661;

	@JsonbProperty("key")
	private String key;

	@JsonbProperty("name")
	private String name;

	@JsonbProperty("site")
	private String site;

	@JsonbProperty("size")
	private Integer size;

	@JsonbProperty("type")
	private String type;

}
