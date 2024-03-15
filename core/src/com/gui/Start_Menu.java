package com.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.bitmaps.Animations;
import com.logic.GameController;
import com.logic.GameStarter;
import com.logic.Game_Elements;
import com.xkick.Main_Unit;

public class Start_Menu implements Screen
{
   GameStarter game_starter;
   final float GameWorldWidth  = 640;
   final float GameWorldHeight =  480;
   Stage stage_start_menu;
   SpriteBatch sb_start_menu;
   StretchViewport svp;
   OrthographicCamera camera;
   GameController d_joypad;
   //int manually_chosen_level; InputProcessor input_processor;
   //InputMultiplexer input_multiplexer;
   
   Texture tx_for_start_up,tx_for_start_down, 
   tx_for_settings_up,tx_for_settings_down,
   tx_for_load_up, tx_for_load_down;
   Drawable drw_start_up,drw_start_down, 
   drw_settings_up,drw_settings_down,
   drw_load_up, drw_load_down;
   Button start_button, settings_button, load_button;   

   Texture spread_sheet_menu, tx_tipler, tx_menu;
   TextureRegion[] frames_logo;
   TextureRegion currentFrame_logo;
   float stateTime_logo;
   Sprite sp_logo, sp_tipler, sp_menu;
   Animation<TextureRegion> anime_logo;
   Sound sfx_hover, sfx_click;
   long id_sfx_hover, id_sfx_click;
   public static Boolean is_start_button_clicked, 
   is_settings_clicked, is_load_clicked;
   int counter_start, counter_settings, counter_load;
   
   public Start_Menu(GameStarter _game_starter)
   {
	   game_starter = _game_starter;	
	   d_joypad = new GameController(GameWorldWidth, GameWorldHeight);
	   //manually_chosen_level = 1;
	   
   }   
   public void AnimateMenu()
	{			    	 	
		 int row=1;
	     int col=4; 				    
			
	    	 	spread_sheet_menu= new Texture(Gdx.files.internal("Main_Menu_UI_Items/logo_sheet.png"));
	    	 	spread_sheet_menu.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	    	 	TextureRegion[][] tmp = TextureRegion.split(spread_sheet_menu, spread_sheet_menu.getWidth()/col, spread_sheet_menu.getHeight()/row);
	    	 	frames_logo= new TextureRegion[col*row];
					
	    	 	int index = 0;
	    	 	for(int i=0;i<row;i++)
	    	 	{
	    	 		for(int j=0;j<col;j++)
	    	 		{
	    	 			frames_logo[index++] = tmp[i][j];
	    	 		}
	    	 	}
	    	 	anime_logo = new Animation<TextureRegion>(0.100f,frames_logo);
	    	    anime_logo.setPlayMode(PlayMode.LOOP);
	    	    stateTime_logo= 0f;
	    	    currentFrame_logo = frames_logo[0];
	    	 	
	    	 	sp_logo = new Sprite(currentFrame_logo);
	    	 	sp_logo.setSize(250,35);
	    	 	sp_logo.setPosition(camera.position.x-svp.getWorldWidth()/4.75f, camera.position.y-svp.getWorldHeight()/2.05f);
	    	 	sb_start_menu = new SpriteBatch();	    	 	
	    	 	tx_tipler = new Texture(Gdx.files.internal("Main_Menu_UI_Items/tipler.png"));
	    	 	tx_tipler.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	    	 	sp_tipler = new Sprite(tx_tipler);
	    	 	sp_tipler.setSize(175, 220);
	    	 	sp_tipler.setPosition(camera.position.x, camera.position.y-180);
	    	 	
	    	 	tx_menu = new Texture(Gdx.files.internal("Main_Menu_UI_Items/menu_sheet.png"));
	    	 	sp_menu = new Sprite(tx_menu);
	    	 	sp_menu.setSize(svp.getWorldWidth(),svp.getWorldHeight());
	    	 	sp_menu.setPosition(camera.position.x-svp.getWorldWidth()/2.0125f, camera.position.y-svp.getWorldHeight()/2);
		}   
   private void Initialize_StartButton()
   {
	   counter_start = 0;
	   tx_for_start_up = new Texture(Gdx.files.internal("Main_Menu_UI_Items/new_game_button_up.png"));
	   tx_for_start_down = new Texture(Gdx.files.internal("Main_Menu_UI_Items/new_game_button_down.png"));
	   TextureRegion tr_start_up = new TextureRegion(tx_for_start_up);
	   TextureRegion tr_start_down = new TextureRegion(tx_for_start_down);
	   drw_start_up = new TextureRegionDrawable(tr_start_up);
	   drw_start_down = new TextureRegionDrawable(tr_start_down);
	   
	   ButtonStyle style_start_button = new ButtonStyle();
	   style_start_button.up = drw_start_up;
	   style_start_button.down = drw_start_down;
	   style_start_button.over = drw_start_down;
	  
	   start_button =new Button(style_start_button);
	   start_button.setSize(190, 58);
	   start_button.setPosition(13.0f, camera.position.y-16);
	   is_start_button_clicked = false;
	   //Button Events
	   start_button.addListener(new ClickListener() {
		 @Override
		public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
			if(sfx_hover !=null)
			id_sfx_hover = sfx_hover.play();
			super.enter(event, x, y, pointer, fromActor);
		}
		 @Override
		public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
			 start_button.setSize(190,start_button.getHeight());
			 start_button.setColor(Color.WHITE);
			super.exit(event, x, y, pointer, toActor);
		}	
		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
		{			
				sfx_hover = null;
				sfx_click.stop();
				sfx_click.play(1f);
				start_button.setSize(start_button.getWidth()*1.105f,start_button.getHeight());
				start_button.setColor(Color.CYAN);
				is_start_button_clicked = true;
			
				Game_Elements.difficulty = "normal";
				Game_Elements.check_0_passed = false;
				Game_Elements.check_1_passed = false;
				Game_Elements.check_2_passed = false;		
				Game_Elements.check_none = false;
				Game_Elements.SetLevelData(1);
				Main_Unit.in_level_collected_diamonds = 0;
				Game_Elements.last_point = new Vector2(2,2);
				Game_Elements.total_diamonds = 0;
				Game_Elements.to_continue = false;
				Game_Elements.boss_reached = false;
				Game_Elements.lives_left = 4;
				if(Game_Elements.difficulty == null)
					Game_Elements.difficulty = "normal";	
				
				Game_Elements.characters = new String[3];
				Game_Elements.characters[0]="characterSheet00";
				Game_Elements.characters[1]="characterSheet01";
				Game_Elements.characters[2]="characterSheet02";
			
				Game_Elements.selected_character = Game_Elements.characters[1];
				
			return super.touchDown(event, x, y, pointer, button);
		}
		  @Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			  start_button.setSize(190,start_button.getHeight());
			  start_button.setColor(Color.WHITE);			
			super.touchUp(event, x, y, pointer, button);
		} 
			  });   
   }
   private void Initialize_SettingsButton()
   {
	   is_settings_clicked = false;
	   counter_settings = 0;
	   tx_for_settings_up = new Texture(Gdx.files.internal("Main_Menu_UI_Items/settings_button_up.png"));   
	   tx_for_settings_down = new Texture(Gdx.files.internal("Main_Menu_UI_Items/settings_button_down.png"));  
	   TextureRegion tr_settings_up = new TextureRegion(tx_for_settings_up);
	   TextureRegion tr_settings_down = new TextureRegion(tx_for_settings_down);
	   drw_settings_up = new TextureRegionDrawable(tr_settings_up);
	   drw_settings_down = new TextureRegionDrawable(tr_settings_down);

	   
	   ButtonStyle style_settings_button = new ButtonStyle();
	   style_settings_button.up = drw_settings_up;
	   style_settings_button.down = drw_settings_down;
	   style_settings_button.over = drw_settings_down;	  
	   settings_button =new Button(style_settings_button);
	   
	   settings_button.setSize(start_button.getWidth(), start_button.getHeight());
	   settings_button.setPosition(start_button.getX(), start_button.getY()-start_button.getHeight()*1.125f);
	 
	   //button events
	   settings_button.addListener(new ClickListener() 
	   {
		  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) 
		  {
			  if(sfx_hover !=null)
			  id_sfx_hover = sfx_hover.play(1f);
		  }; 
		  public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) 
		  {
			  if(sfx_hover !=null)
			  sfx_hover.stop();
		  };
		 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
		 {
			 is_settings_clicked = true;
				settings_button.setSize(settings_button.getWidth()*1.105f,settings_button.getHeight());
				settings_button.setColor(Color.CYAN);
				id_sfx_click = sfx_click.play();
			 if(sfx_hover !=null)
				{
				  sfx_hover.setVolume(id_sfx_hover, 0);
				  sfx_hover = null;
				}				
			return false;
			};
	   }
	   );	   
   }
   private void Initialize_LoadButton()
   {
	   is_load_clicked = false;
	   counter_load = 0;
	   tx_for_load_up = new Texture(Gdx.files.internal("Main_Menu_UI_Items/load_button_up.png"));   
	   tx_for_load_down = new Texture(Gdx.files.internal("Main_Menu_UI_Items/load_button_down.png"));  
	   TextureRegion trd_load_up = new TextureRegion(tx_for_load_up);
	   TextureRegion trd_load_down = new TextureRegion(tx_for_load_down);
	   drw_load_up = new TextureRegionDrawable(trd_load_up);
	   drw_load_down = new TextureRegionDrawable(trd_load_down);
	   
	   ButtonStyle style_load_button = new ButtonStyle();
	   style_load_button.up = drw_load_up;
	   style_load_button.down = drw_load_down;
	   style_load_button.over = drw_load_down;	  
	   load_button =new Button(style_load_button);
	   
	   load_button.setSize(load_button.getWidth(), load_button.getHeight());
	   load_button.setPosition(settings_button.getX(), settings_button.getY()-settings_button.getHeight()*1.125f);
	 
	   //button events
	   load_button.addListener(new ClickListener() 
	   {
		  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) 
		  {
			  if(sfx_hover !=null)
			  id_sfx_hover = sfx_hover.play();
		  }; 
		  public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) 
		  {
			  if(sfx_hover !=null)
			  sfx_hover.stop();
		  };
		 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
		 {
			 is_load_clicked = true;
				load_button.setSize(load_button.getWidth()*1.105f,load_button.getHeight());
				load_button.setColor(Color.CYAN);
				id_sfx_click = sfx_click.play();
			 if(sfx_hover !=null)
				{
				  sfx_hover.setVolume(id_sfx_hover, 0);
				  sfx_hover = null;
				}				
			return false;
			};
	   }
	   );	   
   }
   private void Draw_Menu(float _delta)
   {
		  sb_start_menu.begin();
		  sp_menu.draw(sb_start_menu);
		  sb_start_menu.end();
		  
	   sb_start_menu.begin();
	   sb_start_menu.enableBlending();
	   sb_start_menu.setProjectionMatrix(camera.combined);
	  stateTime_logo +=_delta;
	  currentFrame_logo = anime_logo.getKeyFrame(stateTime_logo);
	  sp_logo.setRegion(currentFrame_logo);
	  sp_logo.draw(sb_start_menu);
	  if(anime_logo.isAnimationFinished(stateTime_logo))
	  {
		  stateTime_logo = 0;
		  currentFrame_logo = frames_logo[0];
	  }
	  sb_start_menu.end();
	  sb_start_menu.begin();	
	  sp_tipler.draw(sb_start_menu);
	  sb_start_menu.end();	

   }
	@Override
	public void show() 
	{
		   camera = new OrthographicCamera();
		   svp = new StretchViewport(GameWorldWidth, GameWorldHeight);		
		   svp.setCamera(camera);
		   svp.apply(true);
		   
		   sfx_hover = Gdx.audio.newSound(Gdx.files.internal("Audio/choice_02.wav"));
		   id_sfx_hover = 0;
		   sfx_click = Gdx.audio.newSound(Gdx.files.internal("Audio/choice_done_02.wav"));
		   id_sfx_click = 0;		   
		   Initialize_StartButton();
		   Initialize_SettingsButton();			
		   Initialize_LoadButton();		
		   stage_start_menu = new Stage(svp);		   
		   stage_start_menu.addActor(start_button);
		   stage_start_menu.addActor(settings_button);
		   stage_start_menu.addActor(load_button);		   	   
		   AnimateMenu();
		   
//			input_processor = new InputProcessor() {
//				
//				@Override
//				public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean touchDragged(int screenX, int screenY, int pointer) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean scrolled(float amountX, float amountY) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean mouseMoved(int screenX, int screenY) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean keyUp(int keycode) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean keyTyped(char character) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean keyDown(int keycode) {
//					manually_chosen_level = keycode;
//					System.out.println("the key pressed is: " + manually_chosen_level);
//					return false;
//				}
//			};
//			input_multiplexer = new InputMultiplexer();
//			input_multiplexer.addProcessor(input_processor);
//			input_multiplexer.addProcessor(stage_start_menu);	
		   Gdx.input.setInputProcessor(stage_start_menu);	
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(17/255f, 17/255f, 40/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		camera.update();		
		Draw_Menu(delta);		
		stage_start_menu.draw();	
		stage_start_menu.act();	
		//Gdx.input.setInputProcessor(input_multiplexer);	
		Gdx.input.setInputProcessor(stage_start_menu);	

		//Game_Level_Class.Level_data = manually_chosen_level - 7;
//		if(Game_Level_Class.GetLevelData() <=0)
//			Game_Level_Class.Level_data = 1;
		//System.out.println("set level data: " + Game_Level_Class.GetLevelData());
		  if(is_start_button_clicked)	
		  {
			  Game_Elements.is_just_started = true;
			  counter_start++;		  
			  Gdx.input.setInputProcessor(null);		 
		  }
			if(counter_start>75)
			{
				if(Game_Elements.GetLevelData()<10)
				game_starter.setScreen(game_starter.choose_character);		
				else 
					game_starter.setScreen(game_starter.level_menu);
				game_starter.dispose();
			}	
			if(is_settings_clicked)
			{
				counter_settings++;
				Gdx.input.setInputProcessor(null);	
			}
			if(counter_settings>75)
			{
				game_starter.setScreen(game_starter.settings_menu);				
				game_starter.dispose();
			}
			if(is_load_clicked)
			{
				counter_load++;
				Gdx.input.setInputProcessor(null);
			}
			if(counter_load >75)
			{
				game_starter.setScreen(game_starter.state_load_menu);
				MyDispose();
			}
			//d_joypad.Menu_Selections_With_Physical_Joypad();
	}
	private void MyDispose()
	{
		stage_start_menu.dispose();
		game_starter.dispose();
		tx_tipler.dispose();
		tx_for_load_down.dispose();
		tx_for_load_up.dispose();
		tx_for_settings_down.dispose();
		tx_for_settings_up.dispose();
		tx_for_start_up.dispose();
		tx_for_start_down.dispose();
		tx_menu.dispose();
		tx_tipler.dispose();		
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
