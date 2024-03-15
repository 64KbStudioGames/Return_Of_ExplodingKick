package com.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.xkick.Main_Unit;

public class UI
{
	public Stage stage_ui;
	public static Label label_energy;
	static Label level_label;
	static Label diamonds_label;
	static Label label_life;
	public Label label_stage_clear;
	public Label label_vs;
	private LabelStyle label_style;
	public LabelStyle style_label_stage_clear;
	public BitmapFont bitmap_font;
	String energy_level;
	public StretchViewport ui_viewport;	
	public static Float player_energy;
	
	SpriteBatch diamond_sb;
	Sprite sp_diamond,sp_character_icon;
	public Texture tx_diamond_img_sheet;
	
	TextureRegion[] frames_diamond_animation;
	Animation<TextureRegion> animation_diamond;
	float elapsedTime;
	TextureRegion currentFrameDiamond;
	private float game_world_width;
	private float game_world_height;
	
	public Texture tx_character_icon;
	SpriteBatch sb_character_icon;	
	static final Vector2 icon_size =  new Vector2(90,90);
	
	public Texture tx_boss_icon;
	static SpriteBatch sb_boss_icon;
	public Texture tx_life_icon;
	SpriteBatch sb_life_icon;
	
	static Vector2 common_label_position;
	Vector2 screen_center;
	
	public Texture[] tx_energies;
	Sprite sp_energy_bar;
	
	public UI(OrthographicCamera _cam, float _game_world_width, float _game_world_height)
	{		
		  this.ui_viewport = new StretchViewport(_game_world_width, _game_world_height);	
		  player_energy = 99f; 
		  this.game_world_width = _game_world_width;
		  this.game_world_height = _game_world_height;
		  screen_center = new Vector2(game_world_width/2, game_world_height/2);
		  
		  
		  stage_ui = new Stage(ui_viewport);
		  bitmap_font = new BitmapFont(Gdx.files.internal("Fonts/PowerFonts.fnt"),false);		 
	      label_style = new LabelStyle();
	      label_style.font = bitmap_font;
	      Fix_Gui();
	      style_label_stage_clear = new LabelStyle(bitmap_font, Color.CYAN);		  
	      // Label definitions...
	      label_energy = new Label("Energy full", label_style);	
	      label_vs = new Label("VS", label_style);
	      label_stage_clear = new Label("Stage Clear", style_label_stage_clear);
	      label_vs.setBounds(_game_world_width-280, _game_world_height-100, 120, 120);	   
	      label_stage_clear.setBounds(_game_world_width/2-200, game_world_height-300, 120, 120);
	      label_stage_clear.setFontScale(1.825f, 2f);	      
	      label_energy.setBounds(_game_world_width-560,_game_world_height-100, 120, 120);	    
	      level_label = new Label("0", label_style);
		  level_label.setBounds(_game_world_width/2.59f,_game_world_height-100, 120, 120);
		  label_vs.setVisible(false);
		  //level_label.setVisible(true);
		  //label_messageBox.setVisible(false);
		  //label_energy.setVisible(true);	
		  label_stage_clear.setVisible(false);
		  
		  //Diamonds
		  diamonds_label = new Label("0", label_style);
		  diamonds_label.setBounds(36, _game_world_height - 60, 60, 60);
		  diamonds_label.setVisible(true);
		  common_label_position = new Vector2(76,_game_world_height-98);
		  diamonds_label.setFontScale(0.65f, 1.055f);
		  //Life
		  label_life = new Label(" x "+ Game_Elements.GetLivesLeft(), label_style);		
		  label_life.setBounds(diamonds_label.getX()+100, _game_world_height - 60, 60, 60);
		  label_life.setVisible(true);
		  label_life.setFontScale(diamonds_label.getFontScaleX(), diamonds_label.getFontScaleY());
		  
		  tx_character_icon = new Texture(Gdx.files.internal("Game_Art/Character Sheets/Energy_Icons/" + Game_Elements.selected_character + ".png"));
		  sp_character_icon = new Sprite(tx_character_icon);
		  sp_character_icon.setPosition(0, 0);
		  sp_character_icon.setSize(56f, 56f);		  
		  sb_character_icon = new SpriteBatch();	
		  
		  tx_life_icon = new Texture(Gdx.files.internal("Game_Art/life.png"));
		  sb_life_icon = new SpriteBatch();
		  
		  
	        tx_energies = new Texture[11];			
			for(int i=0;i<tx_energies.length;i++)
			{
				tx_energies[i] = new Texture(Gdx.files.internal("Game_Art/energy bar/energy_" + i + ".png"));
			}
			sp_energy_bar = new Sprite(tx_energies[0]);
			   sp_energy_bar.setSize(50f,50f);
			   sp_energy_bar.setPosition(screen_center.x*1.38f, screen_center.y*1.77f);
		  
		  //stage_ui.addActor(label_energy);	   
		  stage_ui.addActor(label_vs);
		  stage_ui.addActor(diamonds_label);
		  stage_ui.addActor(label_life);
		  stage_ui.addActor(label_stage_clear);		  
		  diamond_sb = new SpriteBatch();		  
		  
		  Animate_Diamond_Img();
		  Set_Boss_Icons();
	}
	
	 public void Animate_Diamond_Img()
	 {
	    	
	    	 	int row=1;
	    	 	int col=8;
			
	    	 	tx_diamond_img_sheet = new Texture(Gdx.files.internal("Game_Art/diamonds_ss.png"));
	    	 	TextureRegion[][] tmp = TextureRegion.split(tx_diamond_img_sheet, tx_diamond_img_sheet.getWidth()/col, tx_diamond_img_sheet.getHeight()/row);
	    	 	frames_diamond_animation= new TextureRegion[col*row];
					
	    	 	int index = 0;
	    	 	for(int i=0;i<row;i++)
	    	 	{
	    	 		for(int j=0;j<col;j++)
	    	 		{
	    	 			frames_diamond_animation[index++] = tmp[i][j];
	    	 		}
	    	 	}
	    	 	animation_diamond = new Animation<TextureRegion>(0.16f, frames_diamond_animation);
	    	 	animation_diamond.setPlayMode(PlayMode.LOOP);
	    	 
	    	 	currentFrameDiamond = frames_diamond_animation[0];
	    	 	sp_diamond = new Sprite(currentFrameDiamond);
	    	 	sp_diamond.setSize(32f,32f);
	    	 	sp_diamond.setPosition(diamonds_label.getX()-30,diamonds_label.getY()+13.5f);
    }  
	
	public void show_Level_Health_And_Diamonds( int _game_level, int number_of_diamonds)
	{  
       if(player_energy<0)
        	Main_Unit.is_player_alive = false;
       if(player_energy >0)
       {
    	   //label_energy.setText( "Energy: ");
    	   Main_Unit.is_player_alive = true;
       }       
   	   //level_label.setText("Level = " + _game_level); 
   	   diamonds_label.setText(String.valueOf(" = " + number_of_diamonds));  
   	   label_life.setText(" = "+ Game_Elements.GetLivesLeft());   	 
	}	
	
	private void Set_Boss_Icons()
	{
		  tx_boss_icon = new Texture(Gdx.files.internal("Game_Art/Enemy Sheets/boss_icons/boss_0" + Game_Elements.Level_data + "_icon.png"));
	      sb_boss_icon = new SpriteBatch();
	}
	public void Draw_Character_Icon()
	{	
		sb_character_icon.setProjectionMatrix(ui_viewport.getCamera().combined);
		sb_character_icon.begin(); 
		sb_character_icon.enableBlending();		
		sp_character_icon.setPosition(screen_center.x*1.38f+77f, screen_center.y*1.935f-40f);
 	   	sp_character_icon.draw(sb_character_icon);
 	    sb_character_icon.end();		
	}
	public void Draw_Energy_Bar()
	{	  
	   sp_energy_bar.setSize(game_world_width/5.25f,48f);
	   sp_energy_bar.setPosition(screen_center.x*1.38f+sp_energy_bar.getWidth()/1.685f, screen_center.y*1.77f+2f);
	    
		sb_character_icon.setProjectionMatrix(ui_viewport.getCamera().combined);
	    sb_character_icon.enableBlending();
 	    sb_character_icon.begin();
	   if(UI.player_energy<=100 && UI.player_energy>=90)	  
	   sp_energy_bar.setRegion(tx_energies[10]);
	    
	  if(UI.player_energy<=90 && UI.player_energy>=80)	   
		  sp_energy_bar.setRegion(tx_energies[9]);
	  
	  if(UI.player_energy<=80 && UI.player_energy>=70)	  
		  sp_energy_bar.setRegion(tx_energies[8]);
	  
	  if(UI.player_energy<=70 && UI.player_energy>=60)	  
		  sp_energy_bar.setRegion(tx_energies[7]);
	 
	  if(UI.player_energy<=60 && UI.player_energy>=50)	  
		  sp_energy_bar.setRegion(tx_energies[6]);
	  
	  if(UI.player_energy<=50 && UI.player_energy>=40)	 
		  sp_energy_bar.setRegion(tx_energies[5]);
	 
	  if(UI.player_energy<=40 && UI.player_energy>=30)	  
		  sp_energy_bar.setRegion(tx_energies[4]);
	 
	  if(UI.player_energy<=30 && UI.player_energy>=20)	  
		  sp_energy_bar.setRegion(tx_energies[3]);
	 
	  if(UI.player_energy<=20 && UI.player_energy>=10)	  	 
		  sp_energy_bar.setRegion(tx_energies[2]);	  
	  if(UI.player_energy<=10 && UI.player_energy>=5)	  
		  sp_energy_bar.setRegion(tx_energies[1]);	 
	  if(UI.player_energy<=5)	    
		  sp_energy_bar.setRegion(tx_energies[0]);
	
	  	sp_energy_bar.draw(sb_character_icon);	
	  	sb_character_icon.end();	
	}
	public void Draw_Boss_Icon()
	{
		sb_boss_icon.setProjectionMatrix(ui_viewport.getCamera().combined);
	    sb_boss_icon.enableBlending();
 	    sb_boss_icon.begin();
 	    sb_boss_icon.draw(tx_boss_icon, screen_center.x-78f, screen_center.y*1.785f,56f,56f);
 	    sb_boss_icon.end();	
 	}	
	public void Play_Diamond_Animation()
    {
		diamond_sb.setProjectionMatrix(ui_viewport.getCamera().combined);
		diamond_sb.enableBlending();
		diamond_sb.begin();		
    	elapsedTime +=Gdx.graphics.getDeltaTime();      
    	currentFrameDiamond = animation_diamond.getKeyFrame(elapsedTime); 	  
 	    sp_diamond.setRegion(currentFrameDiamond);
 	    sp_diamond.draw(diamond_sb);
 	    diamond_sb.end();
    }
    
   public void Show_Life()
   {  
	   	sb_life_icon.setProjectionMatrix(ui_viewport.getCamera().combined);
        sb_life_icon.enableBlending();
	    sb_life_icon.begin();
	    sb_life_icon.draw(tx_life_icon, label_life.getX()-30, label_life.getY()+12, 32f,32f);
	    sb_life_icon.end();  
   }
   private void Fix_Gui()
   {
	   if(Game_Elements.Level_data == 3 || Game_Elements.Level_data == 4 )	   
		   label_style.fontColor = Color.YELLOW;	   
	   else	   
		  label_style.fontColor = Color.FIREBRICK;  
   }
 
//   public void DisposeAll()
//   {
//	tx_boss_icon.dispose();
//	tx_character_icon.dispose();
//	tx_diamond_img_sheet.dispose();
//	for (Texture tx : tx_energies ) 
//	{
//		tx.dispose();
//	}
//	tx_diamond_img_sheet.dispose();
//	sb_boss_icon.dispose();
//	sb_character_icon.dispose();
//	sb_life_icon.dispose();
//	stage_ui.dispose();    
//   }   
   public void UpdateViewport(int x, int y)
   {
	  ui_viewport.update(x, y);
   }
}

