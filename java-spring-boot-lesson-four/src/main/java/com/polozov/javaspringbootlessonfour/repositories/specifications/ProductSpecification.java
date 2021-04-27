package com.polozov.javaspringbootlessonfour.repositories.specifications;

import com.polozov.javaspringbootlessonfour.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
	public static Specification<Product> trueLiteral() {
		return (root, query, builder) -> builder.isTrue(builder.literal(true));
	}

	public static Specification<Product> titleLike(String titleFilter) {
		return (root, query, builder) -> builder.like(root.get("title"), "%" + titleFilter + "%");
	}

	public static Specification<Product> ge(BigDecimal min) {
		return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), min);
	}

	public static Specification<Product> le(BigDecimal max) {
		return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), max);
	}
}
