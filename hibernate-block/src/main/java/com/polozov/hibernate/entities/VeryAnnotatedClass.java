package com.polozov.hibernate.entities;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "demo_annotated", indexes = {
		@Index(name = "name_idx", columnList = "name"),
		@Index(name = "id_name_idx", columnList = "id, name"),
		@Index(name = "unique_name_idx", columnList = "name", unique = true)
})
public class VeryAnnotatedClass {
	@Id
	@GeneratedValue
	@Column(name = "id")
	Long id;

	@Column(name = "name")
	String name;

	@Column(name = "weight")
	float weight;

	@Column(name = "short_str", nullable = false, length = 10)
	String shortString;

	@Column(name = "weight")
	@ColumnTransformer(
			read = "weight / 2.0",
			write = "? * 2.0"
	)
	float dividedWeight;

	@Formula("SELECT avg(p.cost) FROM Product p WHERE p.manufacturer_id = id")
	BigDecimal avgManufacturerProductCost;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	@CreationTimestamp
	LocalDateTime createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@UpdateTimestamp
	LocalDateTime updatedAt;

	@Column(
			name = "manual_def_str",
			columnDefinition = "VARCHAR(40) NOT NULL UNIQUE CHECK (NOT....)"
	)
	String manualDefinedString;

//	@ManyToOne
//	@JoinColumn(
//			name = "product_id",
//			nullable = false,
//			foreignKey = @ForeignKey(name = "FR_PRODUCT_ID")
//	)
//	Product product;

	@Version
	long version;
}
