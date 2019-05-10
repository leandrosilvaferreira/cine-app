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
public class BelongsToCollection implements Serializable {

	private static final long serialVersionUID = 4345517285168333669L;

	@JsonbProperty("id")
	private Integer id;

	@JsonbProperty("name")
	private String name;

	@JsonbProperty("poster_path")
	private String posterPath;

	@JsonbProperty("backdrop_path")
	private String backdropPath;

}
