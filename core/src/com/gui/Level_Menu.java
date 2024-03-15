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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.logic.GameStarter;
import com.logic.Game_Elements;

public class Level_Menu implements Screen 
{

	    GameStarter game;
     	// Set the Game world and the camera
		OrthographicCamera m_camera;
	    public static float GAME_WORLD_WIDTH =  640;
		public static float GAME_WORLD_HEIGHT=   480;

		float asr_4x;
		float asr_4y;
		Vector2 scr_center;
		Viewport m_menu_viewport;
		Stage m_stage; 
		
		Texture tx_win_screen;
		Sprite sp_win_screen;
		SpriteBatch batch_win;
 
		  //Labels and fonts...
	      Label label_level_title;
	      Label label_level_name;  
	      LabelStyle label_style_level_title;
	      LabelStyle label_style_level_name;
		
		  BitmapFont bitmap_font;
		  
		  //Time switchers...
		  float wait_time;	
		  
	
    public Level_Menu(GameStarter Game)
    { 
    	this.game = Game;   
    }   
    
	@Override
	public void show ()
	{		
	    asr_4x = GAME_WORLD_WIDTH/Gdx.app.getGraphics().getWidth();
	    asr_4y = GAME_WORLD_HEIGHT/Gdx.app.getGraphics().getHeight();	
	    
	    //Camera and viewport settings...
		m_camera = new OrthographicCamera();	
		m_menu_viewport = new StretchViewport((GAME_WORLD_WIDTH),(GAME_WORLD_HEIGHT),m_camera);	  
		m_menu_viewport.apply();	  
		scr_center = new Vector2(m_menu_viewport.getWorldWidth()/2,m_menu_viewport.getWorldHeight()/2);   
	   
		Game_Elements.check_0_passed = false;
		Game_Elements.check_1_passed = false;
		Game_Elements.check_2_passed = false;		
		Game_Elements.check_none = false;
																	// Time switchers...
		wait_time = 0;		
			
        													//font and style definitions...
	    bitmap_font = new BitmapFont(Gdx.files.internal("Fonts/LoadingFont.fnt"),false);		 
	    label_style_level_name = new LabelStyle(bitmap_font, Color.PINK); 	    
	    label_style_level_title = new LabelStyle(bitmap_font, Color.PINK);
	    													//label definitions...

	   
	    label_level_title = new Label("LeveL", label_style_level_title);
	    label_level_title.setBounds(scr_center.x-80,scr_center.y+15, 50, 30);
	    label_level_title.setFontScale(2f, 1.5f);	       
	  
	    label_level_name = new Label(String.valueOf(Game_Elements.GetLevelData()), label_style_level_title);
	    label_level_name.setBounds(scr_center.x-5,scr_center.y-85, 50, 30);
	    label_level_name.setFontScale(1.75f, 1.25f);	 
	    
	    //stage definitions.
	    m_stage = new Stage(m_menu_viewport);
	    m_stage.addActor(label_level_title);  	
	    m_stage.addActor(label_level_name);	    
	   
	    Game_Elements.boss_reached = false;
	 
	    tx_win_screen = new Texture(Gdx.files.internal("Game_Art/win_screen.png"));
	    sp_win_screen = new Sprite(tx_win_screen);
	    sp_win_screen.setPosition(m_menu_viewport.getCamera().position.x-m_menu_viewport.getWorldWidth()/4, 
	    		m_menu_viewport.getCamera().position.y - m_menu_viewport.getWorldHeight()/4);
	    sp_win_screen.setSize(m_menu_viewport.getWorldWidth()/2f,m_menu_viewport.getWorldHeight()/2f);
	    batch_win = new SpriteBatch();
	}	
	
	@Override
	public void render (float delta) 
	{ 
		Gdx.gl.glClearColor(17/255f, 17/255f, 40/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		//Update Ratio
		asr_4x = GAME_WORLD_WIDTH/Gdx.app.getGraphics().getWidth();
		asr_4y = GAME_WORLD_HEIGHT/Gdx.app.getGraphics().getHeight();
		
		m_stage.act();
		m_camera.update();  	    
	    m_stage.draw();
	    
	   wait_time +=  delta;
	   
	   if(Game_Elements.GetLevelData() == Game_Elements.max_level)
	   {
		   label_level_title.setVisible(false);
		   label_level_name.setPosition(60, label_level_title.getY() );
		   label_level_name.setVisible(false);
		   //label_level_name.setText("Game Complete!");
		   batch_win.setProjectionMatrix(m_menu_viewport.getCamera().combined);
		   batch_win.begin();
		   batch_win.enableBlending();
		   sp_win_screen.draw(batch_win);
		   batch_win.end();
	   }
	   	   
	    
			
	    	if(Game_Elements.GetLevelData() < Game_Elements.max_level)
	    	{	
	    		if(wait_time > 1f)
	    		{
	    			m_stage.dispose();
	    			bitmap_font.dispose();	    
	    			game.setScreen(game.game_play);	    	
	    		}
	    	}
			if(Game_Elements.GetLevelData() == Game_Elements.max_level)
			{		
				if(wait_time >10f)
				{
					tx_win_screen.dispose();
					m_stage.dispose();
					bitmap_font.dispose();	    
					game.setScreen(game.start_menu);
				}
			}
		
	  
	}

	@Override
	public void resize(int width, int height)
	{
		m_menu_viewport.update(width, height);		
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
		m_stage.dispose();	
		bitmap_font.dispose();	    		
		game.setScreen(game.choose_character);    	
	}
}
