package com.logic;

import com.badlogic.gdx.math.Vector2;

public class Game_Elements 
{
	public static int Level_data;	
	final public static int max_level = 5;
	public static Boolean game_paused;
	public static String error_message;
	public static String difficulty;	
	public static int total_diamonds;
	public static String player_name;
	public static String controller_type;	
	public static Boolean to_continue, boss_reached= false;
	public static Vector2 last_point;
	public static Boolean check_1_passed;
	public static Boolean check_2_passed;
	public static Boolean check_0_passed;
	public static Boolean check_none, is_just_started;  
	public static int lives_left;
	public static String selected_character; 	
	public static String[] characters;
 	
	public Game_Elements()
	{
		game_paused = false;		
		difficulty = "normal";		
		player_name = "your_name_please!";
		controller_type = "JoyPad";	
		total_diamonds = 0;
		error_message = "no error";	
		to_continue = false; boss_reached = false;
		last_point = new Vector2(0,0);
		check_1_passed = false;
		check_2_passed = false;
		check_0_passed = false;
		check_none = false;	
		selected_character = "not selected yet";	
		is_just_started = true;		
	}	
	public static  int GetLevelData()
	{
		return Level_data;
	}
	public static void SetLevelData(int _LevelData)
	{
	    Level_data = _LevelData;
		//Level_data = 2;
	}	
	public static void SetLivesLeft()
	{
		lives_left -= 1;
	}
	public static int GetLivesLeft()
	{
		return lives_left;
	}
	public static void SetLastPoint(Vector2 Last_Point)
	{
		last_point = Last_Point;
	}	
}

