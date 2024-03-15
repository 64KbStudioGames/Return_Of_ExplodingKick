//Coded by Doruk Atasoy, 05:35 06.12.2015
//Updated at 03/27/2018...
//Updated at 17/09/2021

package com.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.logic.GameStarter;
import com.logic.Game_Elements;
import com.logic.UI;

public class Lost_Screen implements Screen 
{

	    GameStarter game;
     	// Set the Game world and the camera
		OrthographicCamera camera_lost;
	    public static float GAME_WORLD_WIDTH =  640;
		public static float GAME_WORLD_HEIGHT=   480;	
		Viewport vp_lost;
		Stage stage_lost; 		  
 
		  //Labels and fonts...		
		  BitmapFont bitmap_font;
		  
		  //Time switchers...
		  float wait_game_over_change_screen;	
		  Boolean is_game_over;			  
		  Texture tx_continue_up, tx_continue_down;
		  Texture tx_end_up, tx_end_down;
		  Drawable drw_continue_up, drw_continue_down, drw_end_up, drw_end_down;
		  Button button_continue, button_end;
		  Label label_game_over,label_game_over_shadow,label_score;
		  LabelStyle label_style_game_over,label_style_game_over_shadow;
		  Boolean is_continue_button_clicked, is_end_button_clicked;
		  
    public Lost_Screen(GameStarter Game)
    { 
    	this.game = Game;   
    }   
    private void Initialize_Labels()
    {
    	label_style_game_over = new LabelStyle(bitmap_font, new Color(40/255f, 255/255f, 255/255f,1f));
    	label_style_game_over_shadow = new LabelStyle(bitmap_font,new Color(55/255f, 127/255f,255/255f, 0.5f)); 
    	label_game_over = new Label("GAME OVER",label_style_game_over);
    	label_game_over.setBounds(button_continue.getX()-button_continue.getWidth()/2,
    			button_continue.getY()-button_continue.getHeight()/2, 
    			button_continue.getWidth()/2, button_continue.getHeight()/2);
    	label_game_over.setFontScale(2f);
    	label_game_over_shadow = new Label(label_game_over.getText(), label_style_game_over_shadow);
    	label_game_over_shadow.setBounds(label_game_over.getX()-label_game_over.getWidth()/14.5f, 
    			label_game_over.getY(), 
    			label_game_over.getWidth(),label_game_over.getHeight());
    	label_game_over_shadow.setFontScale(label_game_over.getFontScaleX());
    }

    private void Initialize_ContinueButton()
    { 	   
 	   tx_continue_up = new Texture(Gdx.files.internal("Main_Menu_UI_Items/continue_button_up.png"));
 	   tx_continue_down = new Texture(Gdx.files.internal("Main_Menu_UI_Items/continue_button_down.png"));
 	   TextureRegion tr_continue_up = new TextureRegion(tx_continue_up);
 	   TextureRegion tr_continue_down = new TextureRegion(tx_continue_down);
 	   drw_continue_up = new TextureRegionDrawable(tr_continue_up);
 	   drw_continue_down = new TextureRegionDrawable(tr_continue_down);
 	   
 	   ButtonStyle style_continue_button = new ButtonStyle();
 	   style_continue_button.up = drw_continue_up;
 	   style_continue_button.down = drw_continue_down;
 	   style_continue_button.over = drw_continue_down;
 	  
 	   button_continue =new Button(style_continue_button);
 	   button_continue.setBounds(camera_lost.position.x+vp_lost.getWorldWidth()/2.105f-button_continue.getWidth()/1.5f
 			   ,camera_lost.position.y+vp_lost.getWorldHeight()/1.575f-button_continue.getHeight()/2,196,128);
 	   is_continue_button_clicked = false;
 	   //Button Events
 	   	button_continue.addListener(new ClickListener() 
 	   	{ 
 	   		@Override
 			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
 			{ 	
 	   			if(!is_continue_button_clicked)
 	   			{
 	   				button_continue.setSize(button_continue.getWidth()/1.5f, button_continue.getHeight()/1.5f);
 	   				button_continue.setPosition(button_continue.getX() + button_continue.getWidth()/4.5f, 
 						button_continue.getY()+button_continue.getHeight()/4.5f);
 	   				button_continue.setColor(Color.LIGHT_GRAY);
 	   				button_continue.setDisabled(true);
 	   			}
 				is_continue_button_clicked = true;
 				return super.touchDown(event, x, y, pointer, button);
 			}
 		});   
    }
    private void Initialize_EndButton()
    { 	   
 	   tx_end_up = new Texture(Gdx.files.internal("Main_Menu_UI_Items/end_button_up.png"));
 	   tx_end_down = new Texture(Gdx.files.internal("Main_Menu_UI_Items/end_button_down.png"));
 	   TextureRegion tr_end_up = new TextureRegion(tx_end_up);
 	   TextureRegion tr_end_down = new TextureRegion(tx_end_down);
 	   drw_end_up = new TextureRegionDrawable(tr_end_up);
 	   drw_end_down = new TextureRegionDrawable(tr_end_down);
 	   
 	   ButtonStyle style_end_button = new ButtonStyle();
 	   style_end_button.up = drw_end_up;
 	   style_end_button.down = drw_end_down;
 	   style_end_button.over = drw_end_down;
 	  
 	   button_end =new Button(style_end_button);
 	   button_end.setBounds(camera_lost.position.x+vp_lost.getWorldWidth()/2.105f-button_continue.getWidth()/2.225f
 			   ,camera_lost.position.y+button_continue.getHeight()/2,196,128);
 	   is_end_button_clicked = false;
 	   //Button Events
 	   	button_end.addListener(new ClickListener() 
 	   	{ 
 	   		@Override
 			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
 			{ 	
 	   			if(!is_end_button_clicked)
 	   			{
 	   				button_end.setSize(button_end.getWidth()/1.5f, button_end.getHeight()/1.5f);
 	   				button_end.setPosition(button_end.getX() + button_end.getWidth()/4.5f, 
 						button_end.getY()+button_end.getHeight()/4.5f);
 	   				button_end.setColor(Color.LIGHT_GRAY);
 	   				button_end.setDisabled(true);
 	   			}
 				is_end_button_clicked = true;
 				return super.touchDown(event, x, y, pointer, button);
 			}
 		});   
    }
    private void HideLabels()
    {
    	label_game_over.setVisible(false);
		label_game_over_shadow.setVisible(false);
		button_continue.setVisible(true);
		button_continue.setDisabled(false);
		button_end.setVisible(true);
		button_end.setDisabled(false);	
    }
    private void HideButtons()
    {
    	button_continue.setVisible(false);
		button_continue.setDisabled(true);
		button_end.setVisible(false);
		button_end.setDisabled(true);			
		label_game_over.setVisible(true);
		label_game_over_shadow.setVisible(true);
		wait_game_over_change_screen++;
    }
	@Override
	public void show ()
	{    
	    //Camera and viewport settings...
		camera_lost = new OrthographicCamera();	
		vp_lost = new StretchViewport((GAME_WORLD_WIDTH),(GAME_WORLD_HEIGHT),camera_lost);	  
		vp_lost.apply();
	    wait_game_over_change_screen=0;
	    bitmap_font = new BitmapFont(Gdx.files.internal("Fonts/PowerFonts.fnt"),false);	
	    
	    if(Game_Elements.GetLivesLeft()>0)
	    {
	    	is_game_over = false;	    	
	    }
	    if(Game_Elements.GetLivesLeft()<=0)
	    {	    	 
	    	is_game_over = true;	    		
	    }  
	    Initialize_ContinueButton();
	    Initialize_EndButton();
	    Initialize_Labels();
	    //stage definitions.
	    stage_lost = new Stage(vp_lost);
	    stage_lost.addActor(button_continue);
	    stage_lost.addActor(button_end);
	    stage_lost.addActor(label_game_over_shadow);
	    stage_lost.addActor(label_game_over);	   	   
	}	
	
	@Override
	public void render (float delta) 
	{ 
		Gdx.gl.glClearColor(17/255f, 17/255f, 40/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		   
		stage_lost.act();
		stage_lost.draw();
		camera_lost.update();	
		 Gdx.input.setInputProcessor(stage_lost);			
		if(!is_game_over)
		{
			if(is_continue_button_clicked)
			{	
				game.setScreen(game.game_play);				
				try {
				//game.dispose();
				DisposeEverything();
				this.dispose();					
				//System.out.println("hi Bobi");
				}
				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
				
			}
			if(is_end_button_clicked)
			{
				game.setScreen(game.start_menu);
				try {
				//game.dispose();
				DisposeEverything();
				this.dispose();
				//System.out.println("Hello from Tobi");
				}
				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
			}
		}
		if(is_game_over)
			HideButtons();
		else 
			HideLabels();		
		if(wait_game_over_change_screen>300)
		{		
			game.setScreen(game.start_menu);
			DisposeEverything();
		}
	}

	private void DisposeEverything()
	{
		Gdx.input.setInputProcessor(null);
		tx_continue_down.dispose();
		tx_continue_up.dispose();
		tx_end_down.dispose();
		tx_end_up.dispose();
		bitmap_font.dispose();
		//stage_lost.dispose();
	}
	@Override
	public void resize(int width, int height)
	{
		vp_lost.update(width, height);		
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
			//System.out.println("hello from the Lost Screen Dispose Method!");
	}
}

