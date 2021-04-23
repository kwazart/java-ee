package com.polozov.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "big_items")
public class BigItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@Column
	int val;

	@Column(name = "testCol")
	int testCol;

	@Version
	long version;

	public BigItem() {
	}

	public BigItem(int val) {
		this.val = val;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public int getTestCol() {
		return testCol;
	}

	public void setTestCol(int testCol) {
		this.testCol = testCol;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "BigItem{" +
				"id=" + id +
				", val=" + val +
				", testCol=" + testCol +
				", version=" + version +
				'}';
	}
}
