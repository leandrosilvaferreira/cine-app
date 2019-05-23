package com.cine.api.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.List;

@RegisterForReflection
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorGenericList implements Serializable {

	private static final long serialVersionUID = -2728427436381827367L;

	@JsonbProperty("errors")
	private List<String> errors;

	@JsonbTransient
	public boolean isValid() {

		return this.errors != null && !this.errors.isEmpty();
	}

}
