package com.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.logic.Game_Elements;

public class Pause_Menu {
    //Pause Button
    SpriteBatch sb_pause;
    Texture tx_pause;
    Button button_pause;
    Boolean is_button_pause_pressed;  
    //Resume Button
    SpriteBatch sb_resume;
    Texture tx_resume;
    Button button_resume;
    Boolean is_resume_button_pressed;
    //Home Button
    SpriteBatch sb_home;
    Texture tx_home;
    Button button_home;
    public Boolean is_home_button_pressed;
    //Resume Button Background
    SpriteBatch sb_resume_background;
    Texture tx_resume_background;
    
    ShapeRenderer test_shape;
      
    //Camera and Viewport
    Stage stage_pause_menu;
    public Stage logic_stage_pause_menu;
    public Viewport viewport_pause_menu;
    OrthographicCamera camera_pause;     
	final float GAME_WORLD_WIDTH = 640;
	final float GAME_WORLD_HEIGHT = 480; 
	Vector2 screen_center;
	
	Vector2 disabled_position;
	Vector2 original_position_home;
	Vector2 original_position_resume;
	
    public Pause_Menu()
    {
    	this.button_pause = new Button();
		this.button_resume = new Button();
		this.button_home = new Button();
		this.is_button_pause_pressed = false;
		this.is_resume_button_pressed = false;
		this.is_home_button_pressed = false;
		
		camera_pause = new OrthographicCamera();		
		viewport_pause_menu = new StretchViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera_pause);
		viewport_pause_menu.apply();
		screen_center = new Vector2(viewport_pause_menu.getWorldWidth()/2, viewport_pause_menu.getWorldHeight()/2);
		
		//Pause Button Texture...
		this.sb_pause 	   = new SpriteBatch();
		this.tx_pause  	   = new Texture(Gdx.files.internal("Game_Controllers/pause_button.png"));
		//Resume Button Texture...
		this.sb_resume = new SpriteBatch();
		this.tx_resume =  new Texture(Gdx.files.internal("Game_Controllers/resume_button.png"));
		//Home Button Texture...
		this.sb_home = new SpriteBatch();
		this.tx_home =  new Texture(Gdx.files.internal("Game_Controllers/home_button.png"));
		//Resume Button Background Texture...
		this.sb_resume_background = new SpriteBatch();
		this.tx_resume_background = new Texture(Gdx.files.internal("Game_Controllers/resume_button_background.png"));	
	
		disabled_position = new Vector2(1000*1000, 1000*1000);
	    // button pause click event...
	    button_pause.addListener(new ClickListener()
				{
				@Override
				public void clicked(InputEvent event, float x, float y) {				
		    	   				    			
		    			is_button_pause_pressed = true; 		    	
		    			Game_Elements.game_paused = true;		    		
		    			is_resume_button_pressed = false;
		    			is_home_button_pressed = false;
		    		}			
				});	
	    // button resume click event...
	    button_resume.addListener(new ClickListener()
				{
				@Override
				public void clicked(InputEvent event, float x, float y) {				
		    	   				    			
		    			is_resume_button_pressed = true; 		    		
		    			Game_Elements.game_paused = false;		    	
		    			is_button_pause_pressed = false;
		    			is_home_button_pressed = false;
		    		}			
				});	
	    // button home click event...
	    button_home.addListener(new ClickListener()
				{
				@Override
				public void clicked(InputEvent event, float x, float y) {				
		    	   				    			
		    			is_home_button_pressed = true;  		    		
		    			is_button_pause_pressed = false;
		    			is_resume_button_pressed = false;
		    		}			
				});	  	
	    button_pause.setPosition(screen_center.x/50, screen_center.y*1.5f);
	    button_pause.setBounds(button_pause.getX(), button_pause.getY(), 58, 58); 
	    
	    button_resume.setPosition(screen_center.x-64, screen_center.y+45);
	    button_resume.setBounds(button_resume.getX(), button_resume.getY(),128,64);
	    original_position_resume = new Vector2(button_resume.getX(), button_resume.getY());
	    
	    button_home.setPosition(button_resume.getX(), button_resume.getY()-75);
	    button_home.setBounds(button_home.getX(), button_home.getY(),128,64);
	    original_position_home = new Vector2(button_home.getX(), button_home.getY());
	    	
	    stage_pause_menu = new Stage(viewport_pause_menu);
	    logic_stage_pause_menu = new Stage(viewport_pause_menu);
	    
	    logic_stage_pause_menu.addActor(button_resume);
	    logic_stage_pause_menu.addActor(button_home);
	    logic_stage_pause_menu.addActor(button_pause);
	    
	    test_shape = new ShapeRenderer();
    }
    public void Draw_Pause_Menu()
    {    	
    	stage_pause_menu.act();
    	logic_stage_pause_menu.act();
    	camera_pause.update();
    	
    	test_shape.setProjectionMatrix(camera_pause.combined);
	    test_shape.begin(ShapeType.Line);
		test_shape.setColor(Color.RED);
		test_shape.rect(button_resume.getX(), button_resume.getY(),button_resume.getWidth(), button_resume.getHeight());
		test_shape.end();
		
		test_shape.setProjectionMatrix(camera_pause.combined);
	    test_shape.begin(ShapeType.Line);
		test_shape.setColor(Color.YELLOW);
		test_shape.rect(button_home.getX(), button_home.getY(),button_home.getWidth(), button_home.getHeight());
		test_shape.end();
		
    	  if(!is_button_pause_pressed)
    	  {  
    		sb_pause.setProjectionMatrix(camera_pause.combined);
    	    sb_pause.enableBlending();
    	    sb_pause.begin();
    	    sb_pause.draw(tx_pause, button_pause.getX(), button_pause.getY(), button_pause.getWidth(), button_pause.getHeight());
    	    sb_pause.end();
    	    button_home.setPosition(disabled_position.x, disabled_position.y);
    	    button_resume.setPosition(disabled_position.x, disabled_position.y);
    	  }     		  
    	    //Draw the resume background...
    	    if(is_button_pause_pressed && !is_resume_button_pressed && !is_home_button_pressed)
    	    {   	    
    	    	button_home.setPosition(original_position_home.x, original_position_home.y);
    	    	button_resume.setPosition(original_position_resume.x, original_position_resume.y);
    	    	sb_resume_background.setProjectionMatrix(camera_pause.combined);
    	       	sb_resume_background.enableBlending();
    	    	sb_resume_background.begin();
    	    	sb_resume_background.draw(tx_resume_background, button_resume.getX()-45, button_resume.getY()-140,button_resume.getWidth()*1.75f,button_resume.getHeight()*4.25f);
    	    	sb_resume_background.end();	   
    	    	
    	    	//Draw the resume button	    	
    	    	sb_resume.setProjectionMatrix(camera_pause.combined);	
    	   	    sb_resume.enableBlending();
    	       	sb_resume.begin();
    	       	sb_resume.draw(tx_resume, button_resume.getX(),button_resume.getY()-50, button_resume.getWidth(), button_resume.getHeight()*2.4f);
    	       	sb_resume.end();	    	       
    	       	
    	       	//Draw the home button    	
    	       	sb_home.setProjectionMatrix(camera_pause.combined);
    	   	    sb_home.enableBlending();
    	       	sb_home.begin();
    	       	sb_home.draw(tx_home, button_home.getX(),button_home.getY()-50,button_home.getWidth(),button_home.getHeight()*2.4f);
    	       	sb_home.end();	    	    	
    	    	}  	     	  
    	   }  
    public void DisposeEverything()
    {
    tx_home.dispose();
    tx_pause.dispose();
    tx_resume.dispose();
    tx_resume_background.dispose();
    stage_pause_menu.dispose();
    logic_stage_pause_menu.dispose();
    sb_home.dispose();
    sb_pause.dispose();
    sb_resume.dispose();
    }
}    


