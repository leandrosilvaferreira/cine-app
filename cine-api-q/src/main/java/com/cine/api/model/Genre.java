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
public class Genre implements Serializable {

	private static final long serialVersionUID = -6353901893406381002L;

	@JsonbProperty("id")
	private Integer id;

	@JsonbProperty("name")
	private String name;

}

