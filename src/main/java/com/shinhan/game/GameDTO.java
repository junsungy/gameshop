package com.shinhan.game;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {
	private int game_id;
	private String game_name;
	private String alternative_name;
	private int price;
	private double discount_rate;
	private String genre;
	private String maker;
	private Date release_date;
	private int downloads;
	private String thumbnail;
}
