//Coded by Doruk Atasoy, 05:35 06.12.2015
//Updated at 03/27/2018...

package com.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.logic.GameStarter;
import com.logic.Game_Elements;

public class Settings_Menu implements Screen 
{

	    GameStarter game;
     	// Set the Game world and the camera
		OrthographicCamera s_camera;
	    public static float GAME_WORLD_WIDTH =  640;
		public static float GAME_WORLD_HEIGHT=   480;
		Color my_custom_color;

		float asr_4x;
		float asr_4y;
		Vector2 scr_center;
		Viewport s_menu_viewport;
		Stage s_stage;
	    Stage logic_stage;
		
		 //Sprite and Textures
		  SpriteBatch difficulty_title_sprite;
		  Texture difficulty_title_texture;
		 
		  SpriteBatch back_button_sprite;
		  Texture back_button_texture;	
		
		 
		  SpriteBatch sb_normal_level;
		  Texture tx_normal_level;
		  SpriteBatch sb_hard_level;
		  Texture tx_hard_level;
		  
		  SpriteBatch menu_background_sprite;
		  Texture menu_background_texture;
		 
		  //Logic Buttons...
		  Button difficulty_normal_button;Boolean is_difficulty_normal_button_clicked;
		  Button difficulty_hard_button; Boolean is_difficulty_hard_button_clicked;
		 
		  Button back_button;Boolean is_back_button_clicked;	
 
		  //Labels and fonts...
		  Label settings_menu_title;
		  Label normal_level_title;
		  Label hard_level_title;
		  LabelStyle label_style;		  
		  BitmapFont bitmap_font;
		  
		  //Time switchers...
		  float waiting_time;	  
		  
		  ShapeRenderer test_shape;
	
    public Settings_Menu(GameStarter Game)
    { 
    	this.game = Game;   
    	test_shape = new ShapeRenderer();
    }   
    
	@Override
	public void show ()
	{   	
	    asr_4x = GAME_WORLD_WIDTH/Gdx.app.getGraphics().getWidth();
	    asr_4y = GAME_WORLD_HEIGHT/Gdx.app.getGraphics().getHeight();	
	    
	    //Camera and viewport settings...
		s_camera = new OrthographicCamera();	
		s_menu_viewport = new StretchViewport((GAME_WORLD_WIDTH),(GAME_WORLD_HEIGHT),s_camera);	  
		s_menu_viewport.apply();	  
		scr_center = new Vector2(s_menu_viewport.getWorldWidth()/2,s_menu_viewport.getWorldHeight()/2);   
	   
																	// Time switchers...
		waiting_time = 0;
		my_custom_color = new Color(236f/255, 168f/255, 191f/255, 255/255f);
		//Button definitions...
		//Difficulty Title
		difficulty_title_sprite = new SpriteBatch();
		difficulty_title_texture = new Texture(Gdx.files.internal("Settings_Menu_Items/settings_menu_difficulty_title.png"));
		//Difficulty Normal Button...
		difficulty_normal_button = new Button();
		difficulty_normal_button.setPosition(scr_center.x -42, scr_center.y+43f);
		difficulty_normal_button.setBounds(difficulty_normal_button.getX()+50, difficulty_normal_button.getY()-100,100,120);		
        is_difficulty_normal_button_clicked = false;		
        difficulty_normal_button.addListener(new ClickListener()
		{
		@Override
		public void clicked(InputEvent event, float x, float y) {
			is_difficulty_normal_button_clicked = true;			
			is_difficulty_hard_button_clicked = false;
		}
		});	         
        
    	//Difficulty Hard Button...
		difficulty_hard_button = new Button();
		difficulty_hard_button.setPosition(scr_center.x+128, scr_center.y+43);
		difficulty_hard_button.setBounds(difficulty_hard_button.getX()+50, difficulty_hard_button.getY()-100,100,120);		
        is_difficulty_hard_button_clicked = false;		
        difficulty_hard_button.addListener(new ClickListener()
		{
		@Override
		public void clicked(InputEvent event, float x, float y) {
			is_difficulty_hard_button_clicked = true;		
			is_difficulty_normal_button_clicked = false;
		}
		});	     
       
      	    
      	//Back Button
    		back_button_sprite = new SpriteBatch();
    		back_button_texture = new Texture(Gdx.files.internal("Settings_Menu_Items/settings_menu_back_button.png"));
    		back_button = new Button();
    		back_button.setPosition(scr_center.x-122, scr_center.y-220);
    		back_button.setBounds(back_button.getX(), back_button.getY(),240,70);		
            is_back_button_clicked = false;		
            back_button.addListener(new ClickListener()
    		{
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
    			is_back_button_clicked = true;	
    		}
    		});	          
            
            //Normal level choice...
            sb_normal_level = new SpriteBatch();
            tx_normal_level= new Texture(Gdx.files.internal("Settings_Menu_Items/Normal_level.png"));
            //Hard Level choice...
            sb_hard_level = new SpriteBatch();
            tx_hard_level= new Texture(Gdx.files.internal("Settings_Menu_Items/Hard_level.png"));
            
        													//font and style definitions...
	    bitmap_font = new BitmapFont(Gdx.files.internal("Fonts/LoadingFont.fnt"),false);		 
	    label_style = new LabelStyle(bitmap_font, my_custom_color); 	    
	 
	    													//label definitions...
	  
	    settings_menu_title = new Label("SETTINGS", label_style);
	    settings_menu_title.setBounds(scr_center.x/2.4f+75f,scr_center.y+190, 50, 30);
	    settings_menu_title.setFontScale(1.25f, 1f);	    
	    
	    normal_level_title = new Label("Normal", label_style);
	    normal_level_title.setBounds(difficulty_normal_button.getX()+18, difficulty_normal_button.getY()-30,50,30);
	    normal_level_title.setFontScale(0.5f, 0.5f);
	    
	    hard_level_title= new Label("Hard", label_style);
	    hard_level_title.setBounds(difficulty_hard_button.getX()+32, difficulty_normal_button.getY()-30,50,30);
	    hard_level_title.setFontScale(0.5f, 0.5f);	    
	      
	    //stage definitions.
	    s_stage = new Stage(s_menu_viewport);
	    logic_stage = new Stage(s_menu_viewport);
	    Gdx.input.setInputProcessor(logic_stage);
	    s_stage.addActor(settings_menu_title);
	    s_stage.addActor(normal_level_title);
	    s_stage.addActor(hard_level_title);
	    logic_stage.addActor(difficulty_normal_button);	  	
	    logic_stage.addActor(difficulty_hard_button);
	 
	    logic_stage.addActor(back_button);
  	    
  	    menu_background_sprite = new SpriteBatch();
	    menu_background_texture = new Texture(Gdx.files.internal("Main_Menu_UI_Items/settings_menu_background.png"));
	}	
	
	public void Draw_TestShapes(float position_X,float position_Y,float _width, float _height)
	{
	    test_shape.setAutoShapeType(true);
     	test_shape.begin();
	   	test_shape.rect(position_X,position_Y,_width,_height);	   	
	   	test_shape.set(ShapeType.Filled);	 
	   	test_shape.setColor(Color.PURPLE);
	   	test_shape.setProjectionMatrix(s_camera.combined);
	    test_shape.end();  
	}
	
	@Override
	public void render (float delta) 
	{ 
		Gdx.gl.glClearColor(0/255f, 0/255f, 0/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		//Update Ratio
		asr_4x = GAME_WORLD_WIDTH/Gdx.app.getGraphics().getWidth();
		asr_4y = GAME_WORLD_HEIGHT/Gdx.app.getGraphics().getHeight();
		
		s_stage.act();
		logic_stage.act();	

		s_camera.update();  
		   
		menu_background_sprite.enableBlending();
	    menu_background_sprite.begin();
	    menu_background_sprite.setProjectionMatrix(s_camera.combined);
        menu_background_sprite.draw(menu_background_texture, 0, 0,s_menu_viewport.getWorldWidth(),s_menu_viewport.getWorldHeight()/1.025f);
	    menu_background_sprite.end();	 	
	    
	    s_stage.draw();   
	    
					///Draw the difficulty title...	
	    difficulty_title_sprite.enableBlending();
	    difficulty_title_sprite.begin();
	    difficulty_title_sprite.setProjectionMatrix(s_camera.combined);
        difficulty_title_sprite.draw(difficulty_title_texture, 16, scr_center.y-81,270,160);
	    difficulty_title_sprite.end();
	
		/// Draw the back button...
	    back_button_sprite.enableBlending();
	    back_button_sprite.begin();
	    back_button_sprite.setProjectionMatrix(s_camera.combined);
	    back_button_sprite.draw(back_button_texture, scr_center.x-122, scr_center.y-290,240,200);
	    back_button_sprite.end();		

	    //Normal level choice...
	    sb_normal_level.enableBlending();
	    sb_normal_level.begin();
	    sb_normal_level.setProjectionMatrix(s_camera.combined);
	    sb_normal_level.draw(tx_normal_level, back_button.getX()+130 , scr_center.y-61,100,120);
	    sb_normal_level.end();	
	    
	    //Hard level choice...
	    sb_hard_level.enableBlending();
	    sb_hard_level.begin();
	    sb_hard_level.setProjectionMatrix(s_camera.combined);
	    sb_hard_level.draw(tx_hard_level, back_button.getX()+300, scr_center.y-61,100,120);
	    sb_hard_level.end();	
	    
	  
	    if(is_difficulty_normal_button_clicked)
	    Draw_TestShapes(difficulty_normal_button.getX(), difficulty_normal_button.getY(), difficulty_normal_button.getWidth(), difficulty_normal_button.getHeight());
	    if(is_difficulty_hard_button_clicked)
	    Draw_TestShapes(difficulty_hard_button.getX(),difficulty_hard_button.getY(), difficulty_hard_button.getWidth(), difficulty_hard_button.getHeight());
	
	     if(is_difficulty_normal_button_clicked)
	    	{
	    		Game_Elements.difficulty = "normal";   		
	    	} 	
	    	if(is_difficulty_hard_button_clicked)
	    	{
	    		Game_Elements.difficulty = "hard";   		
	    	} 
	    	
	    if(is_back_button_clicked)
	    {
	    	back_button_sprite.dispose();
	    	back_button_texture.dispose();
	    	difficulty_title_sprite.dispose();
	    	difficulty_title_texture.dispose();	    	
	    	menu_background_sprite.dispose();
	    	menu_background_texture.dispose();	    	
	    	sb_hard_level.dispose();
	    	sb_normal_level.dispose();	 
	    	tx_hard_level.dispose();
	    	tx_normal_level.dispose();	   
	    	game.setScreen(game.start_menu);
	    	game.dispose();
	    }
	}

	@Override
	public void resize(int width, int height)
	{
		s_menu_viewport.update(width, height);		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {		
		
	}
	@Override
	public void dispose() {
		s_stage.dispose();
		logic_stage.dispose();
		difficulty_title_sprite.dispose();
		difficulty_title_texture.dispose();			
		bitmap_font.dispose();	    		
		//game.setScreen(game.start_menu);
		game.dispose();
	}
}

