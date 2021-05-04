package com.polozov.javaspringbootlessonfour.dto;

import lombok.*;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String login;
	private String nickname;
	private List<ProductDTO> productDTOS;
}
