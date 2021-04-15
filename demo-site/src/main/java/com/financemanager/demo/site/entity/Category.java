package com.financemanager.demo.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"category\"")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Category implements Comparable<Category>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Override
	public int compareTo(Category category) {
		// TODO Auto-generated method stub
		return this.id - category.getId();
	}
}
