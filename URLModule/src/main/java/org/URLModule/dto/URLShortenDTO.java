package org.URLModule.dto;

import org.URLModule.model.URLDB;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class URLShortenDTO {

	private String mainURL;
	private String shortenURL;
	
	public URLDB to() {
		return URLDB.builder().mainURL(this.mainURL).shortenedURL(this.shortenURL).clicksCount(0).build();
	}
}
