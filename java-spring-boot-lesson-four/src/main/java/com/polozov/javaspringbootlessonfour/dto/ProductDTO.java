package com.polozov.javaspringbootlessonfour.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Long id;
	private String title;
	private String description;
	private BigDecimal price;
}
