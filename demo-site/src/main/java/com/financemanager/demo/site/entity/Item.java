package com.financemanager.demo.site.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_table")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="price")
	private double price;
	@JoinColumn(name="category")
	@OneToOne
	private Category category;
	@JoinColumn(name="user")
	@OneToOne
	private User user;
	@Column(name="date")
	private Date date;
}
