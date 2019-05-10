package com.cine.api.filter;

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.info.Contact;
import org.eclipse.microprofile.openapi.models.info.Info;

public class OpenApiOASFilter implements OASFilter {

	@Override
	public void filterOpenAPI(final OpenAPI openAPI) {

		openAPI.setInfo(
				OASFactory.createObject(Info.class)
						.title("The Movie Database (TMDb) API")
						.version("1.0.0")
						.description("API to search about movies!")
						.contact(OASFactory.createObject(Contact.class)
								.name("Leandro Silva Ferreira")
								.email("leandrosilvaferreira@gmail.com"))
		               );
	}

}
