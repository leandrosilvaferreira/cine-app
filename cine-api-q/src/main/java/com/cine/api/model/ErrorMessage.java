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

@RegisterForReflection
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = 7491361002261015072L;

	@JsonbProperty("status_message")
	private String statusMessage;

	@JsonbProperty("status_code")
	private Integer statusCode;

	@JsonbProperty("success")
	private boolean success = true;

	@JsonbTransient
	public boolean isValid() {

		return this.statusMessage != null && !this.statusMessage.isEmpty() && this.statusCode != null;
	}

}

