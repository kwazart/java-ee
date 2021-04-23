package com.polozov.javaspringbootlessonfour.repositories.specifications;

import com.polozov.javaspringbootlessonfour.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
	public static Specification<Product> trueLiteral() {
		return (root, query, builder) -> builder.isTrue(builder.literal(true));
	}

	public static Specification<Product> titleLike(String titleFilter) {
		return (root, query, builder) -> builder.like(root.get("title"), "%" + titleFilter + "%");
	}

	// TODO: 23.04.2021 Добавить спецификации согласно ДЗ
}
