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
public class ProductionCompany implements Serializable {

	private static final long serialVersionUID = -7761996853256357173L;

	@JsonbProperty("id")
	private Integer id;

	@JsonbProperty("logo_path")
	private String logoPath;

	@JsonbProperty("name")
	private String name;

	@JsonbProperty("origin_country")
	private String originCountry;

}
