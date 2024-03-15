//Coded by Doruk Atasoy, 05:35 06.12.2015

package com.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.gui.Level_Menu;
import com.gui.Lost_Screen;
import com.gui.Settings_Menu;
import com.gui.Start_Menu;
import com.gui.State_Load_Menu;
import com.xkick.Main_Unit;

public class GameStarter extends Game 
{
	public Level_Menu level_menu;
	public Main_Unit game_play;
	public Character_Choice choose_character;
	public Lost_Screen lost_menu;
	public Settings_Menu settings_menu;
	public Start_Menu start_menu;
	public State_Load_Menu state_load_menu;
	
	@Override
	public void create ()
	{
		level_menu = new Level_Menu(this);
		game_play = new Main_Unit(this);	
		choose_character = new Character_Choice(this);
		lost_menu = new Lost_Screen(this);
		settings_menu =  new Settings_Menu(this);
		start_menu = new Start_Menu(this);
		state_load_menu = new State_Load_Menu(this);
		
		setScreen(start_menu);		
		super.dispose();
	}
	
	@Override
	public void render () 
	{    
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		super.render();	
	}
}
