package org.URLModule.dto;

import org.URLModule.model.URLDB;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class URLShortenDTO {

	private String URL;
	private String shortenURL;
	
}
