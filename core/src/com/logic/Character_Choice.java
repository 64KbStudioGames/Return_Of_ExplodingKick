package com.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Character_Choice implements Screen
{
   GameStarter game_starter;
   final float GameWorldWidth  = 640;
   final float GameWorldHeight =  480;
   Stage stage_start_menu;
   SpriteBatch sb_start_menu;
   StretchViewport svp;
   OrthographicCamera camera;
   
   Texture tx_for_charA_up,tx_for_charA_down, 
   tx_for_charB_up,tx_for_charB_down,
   tx_for_charC_up, tx_for_charC_down,
   tx_background;
   Sprite sp_background;
   Drawable drw_charA_up,drw_charA_down, 
   drw_charB_up,drw_charB_down,
   drw_charC_up, drw_charC_down;
   Button charA_button, charB_button, charC_button;
   
   Sound sfx_hover, sfx_click;
   long id_sfx_hover, id_sfx_click;
   public static Boolean is_charA_button_clicked, 
   is_charB_button_clicked, is_charC_button_clicked;
   int counter_start, counter_settings, counter_load;
   
   public Character_Choice (GameStarter _game_starter)
   {
	   game_starter = _game_starter;		 
   }   
   
   private void Initialize_charA_Button()
   {
	   counter_start = 0;
	   tx_for_charA_up = new Texture(Gdx.files.internal("Main_Menu_UI_Items/choice_00_button_up.png"));
	   tx_for_charA_down = new Texture(Gdx.files.internal("Main_Menu_UI_Items/choice_00_button_down.png"));
	   TextureRegion tr_charA_up = new TextureRegion(tx_for_charA_up);
	   TextureRegion tr_charA_down = new TextureRegion(tx_for_charA_down);
	   drw_charA_up = new TextureRegionDrawable(tr_charA_up);
	   drw_charA_down = new TextureRegionDrawable(tr_charA_down);
	   
	   ButtonStyle style_charA_button = new ButtonStyle();
	   style_charA_button.up = drw_charA_up;
	   style_charA_button.down = drw_charA_down;
	   style_charA_button.over = drw_charA_down;	  
	   charA_button =new Button(style_charA_button);
	   charA_button.setSize(200,240);
	   charA_button.setPosition(20.0f, camera.position.y-160);
	   is_charA_button_clicked = false;
	   //Button Events
	   charA_button.addListener(new ClickListener() {
		 @Override
		public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
			if(sfx_hover !=null)
			id_sfx_hover = sfx_hover.play();
			super.enter(event, x, y, pointer, fromActor);
		}
		 @Override
		public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {			
			 charA_button.setColor(Color.WHITE);
			super.exit(event, x, y, pointer, toActor);
		}	
		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
		{			
				sfx_hover.stop();;
				sfx_click.stop();
				sfx_click.play(1f);			
				charA_button.setColor(Color.PINK);
				is_charA_button_clicked = true;
					
			return super.touchDown(event, x, y, pointer, button);
		}
		  @Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {			
			  charA_button.setColor(Color.WHITE);			
			super.touchUp(event, x, y, pointer, button);
		} 
			  });   
   }
   private void Initialize_charB_Button()
   {
	   is_charB_button_clicked = false;
	   counter_settings = 0;
	   tx_for_charB_up = new Texture(Gdx.files.internal("Main_Menu_UI_Items/choice_01_button_up.png"));   
	   tx_for_charB_down = new Texture(Gdx.files.internal("Main_Menu_UI_Items/choice_01_button_down.png"));  
	   TextureRegion tr_charB_up = new TextureRegion(tx_for_charB_up);
	   TextureRegion tr_charB_down = new TextureRegion(tx_for_charB_down);
	   drw_charB_up = new TextureRegionDrawable(tr_charB_up);
	   drw_charB_down = new TextureRegionDrawable(tr_charB_down);

	   
	   ButtonStyle style_charB_button = new ButtonStyle();
	   style_charB_button.up = drw_charB_up;
	   style_charB_button.down = drw_charB_down;
	   style_charB_button.over = drw_charB_down;	  
	   charB_button =new Button(style_charB_button);
	   
	   charB_button.setSize(charA_button.getWidth(), charA_button.getHeight());
	   charB_button.setPosition(charA_button.getX()+188, charA_button.getY()+7);
	 
	   //button events
	   charB_button.addListener(new ClickListener() {
			 @Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				if(sfx_hover !=null)
				id_sfx_hover = sfx_hover.play();
				super.enter(event, x, y, pointer, fromActor);
			}
			 @Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {			
				 charB_button.setColor(Color.WHITE);
				super.exit(event, x, y, pointer, toActor);
			}	
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
			{			
					sfx_hover.stop();;
					sfx_click.stop();
					sfx_click.play(1f);			
					charB_button.setColor(Color.PINK);
					is_charB_button_clicked = true;
						
				return super.touchDown(event, x, y, pointer, button);
			}
			  @Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {			
				  charB_button.setColor(Color.WHITE);			
				super.touchUp(event, x, y, pointer, button);
			} 
				  });   
   }
   private void Initialize_charC_Button()
   {
	   is_charC_button_clicked = false;
	   counter_load = 0;
	   tx_for_charC_up = new Texture(Gdx.files.internal("Main_Menu_UI_Items/choice_02_button_up.png"));   
	   tx_for_charC_down = new Texture(Gdx.files.internal("Main_Menu_UI_Items/choice_02_button_down.png"));  
	   TextureRegion trd_charC_up = new TextureRegion(tx_for_charC_up);
	   TextureRegion trd_charC_down = new TextureRegion(tx_for_charC_down);
	   drw_charC_up = new TextureRegionDrawable(trd_charC_up);
	   drw_charC_down = new TextureRegionDrawable(trd_charC_down);
	   
	   ButtonStyle style_charC_button = new ButtonStyle();
	   style_charC_button.up = drw_charC_up;
	   style_charC_button.down = drw_charC_down;
	   style_charC_button.over = drw_charC_down;	  
	   charC_button =new Button(style_charC_button);
	   
	   charC_button.setSize(charB_button.getWidth(), charB_button.getHeight());
	   charC_button.setPosition(charB_button.getX()+192, charB_button.getY());	 
	 //button events
	   charC_button.addListener(new ClickListener() {
			 @Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				if(sfx_hover !=null)
				id_sfx_hover = sfx_hover.play();
				super.enter(event, x, y, pointer, fromActor);
			}
			 @Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {			
				 charC_button.setColor(Color.WHITE);
				super.exit(event, x, y, pointer, toActor);
			}	
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
			{			
					sfx_hover.stop();;
					sfx_click.stop();
					sfx_click.play(1f);			
					charC_button.setColor(Color.PINK);
					is_charC_button_clicked = true;
						
				return super.touchDown(event, x, y, pointer, button);
			}
			  @Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {			
				  charC_button.setColor(Color.WHITE);			
				super.touchUp(event, x, y, pointer, button);
			} 
				  });   
   }     
   
	@Override
	public void show() 
	{
		   camera = new OrthographicCamera();
		   svp = new StretchViewport(GameWorldWidth, GameWorldHeight);		
		   svp.setCamera(camera);
		   svp.apply(true);
		   
		   sb_start_menu = new SpriteBatch();
		   tx_background=new Texture(Gdx.files.internal("Main_Menu_UI_Items/choice_menu_background.png"));
		   sp_background = new Sprite(tx_background);
		   sp_background.setSize(camera.viewportWidth, camera.viewportHeight);
		   sp_background.setPosition(camera.position.x-sp_background.getWidth()/2, 
				   camera.position.y-sp_background.getHeight()/2);		   
		   
		   sfx_hover = Gdx.audio.newSound(Gdx.files.internal("Audio/choice_02.wav"));
		   id_sfx_hover = 0;
		   sfx_click = Gdx.audio.newSound(Gdx.files.internal("Audio/choice_done_02.wav"));
		   id_sfx_click = 0;		   
		   
		   Initialize_charA_Button();
		   Initialize_charB_Button();			
		   Initialize_charC_Button();		
		   stage_start_menu = new Stage(svp);		   
		   stage_start_menu.addActor(charA_button);
		   stage_start_menu.addActor(charB_button);
		   stage_start_menu.addActor(charC_button);	
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(55/255f, 58/255f, 66/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		camera.update();		
		sb_start_menu.setProjectionMatrix(camera.combined);
		sb_start_menu.begin();
		sp_background.draw(sb_start_menu);
		sb_start_menu.end();
		stage_start_menu.draw();	
		stage_start_menu.act();		
		Gdx.input.setInputProcessor(stage_start_menu);
		  if(is_charA_button_clicked)		 
			 Game_Elements.selected_character = Game_Elements.characters[0];		 
		  if(is_charB_button_clicked)	
			  Game_Elements.selected_character = Game_Elements.characters[1];
		  if(is_charC_button_clicked)
			  Game_Elements.selected_character =Game_Elements.characters[2];		  
		  if(is_charA_button_clicked || is_charB_button_clicked || is_charC_button_clicked)
		  {			
				game_starter.setScreen(game_starter.game_play);
				MyDispose();
		  }		
	}
	private void MyDispose()
	{
		stage_start_menu.dispose();
		game_starter.dispose();	
	}

	@Override
	public void resize(int width, int height) {
		svp.update(width, height);
	}

	@Override
	public void pause() {
	
		
	}

	@Override
	public void resume() {
	
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
		
	}

}
