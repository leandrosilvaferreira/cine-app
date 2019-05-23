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
public class MovieIntervalDates implements Serializable {

	private static final long serialVersionUID = 4782036290726313315L;

	@JsonbProperty("maximum")
	private String maximum;

	@JsonbProperty("minimum")
	private String minimum;

}
