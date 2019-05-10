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
public class ProductionCountry implements Serializable {

	private static final long serialVersionUID = 6001186112871071937L;

	@JsonbProperty("iso_3166_1")
	private String iso31661;

	@JsonbProperty("name")
	private String name;

}