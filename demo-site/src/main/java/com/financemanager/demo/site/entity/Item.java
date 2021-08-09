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
@Table(name = "\"item_table\"")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private int id;
	@Column(name="item_name")
	private String name;
	@Column(name="price")
	private double price;
	@JoinColumn(name="\"category_id\"")
	@OneToOne
	private Category category;
	@JoinColumn(name="\"user_id\"")
	@OneToOne
	private User user;
	@Column(name="date")
	private Date date;
	
}

