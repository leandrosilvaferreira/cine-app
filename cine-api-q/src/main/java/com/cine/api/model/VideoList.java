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
import java.util.List;

@RegisterForReflection
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VideoList implements Serializable {

	private static final long serialVersionUID = -2122402919739881922L;

	@JsonbProperty("results")
	private List<Video> results;

}
