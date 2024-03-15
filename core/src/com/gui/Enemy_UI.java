package com.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.logic.Game_Elements;
import com.logic.UI;

public class Enemy_UI
{
	SpriteBatch sb_energy_bar;
	public Texture[] tx_energies;
    Vector2 energy_bar_position;
    Vector2 energy_bar_size;
    double life;
    Boolean is_enemy_dead;
    Boolean is_boss_talking;
    final float GAME_WORLD_WIDTH = 900;
    final float GAME_WORLD_HEIGHT= 540;
    OrthographicCamera camera_eui;
    public StretchViewport vp_eui;
    Stage stage_eui;
    //fake button to replace the bar
	
		public Enemy_UI(Boolean _is_boss)
		{
			 sb_energy_bar = new SpriteBatch();			 
		     tx_energies = new Texture[11];		   
		        for(int i=0;i<tx_energies.length;i++)
		        {		        	
		        	tx_energies[i] = new Texture(Gdx.files.internal("Game_Art/energy bar/energy_" + i + ".png"));
		        } 			      
		        camera_eui = new OrthographicCamera();
		        vp_eui = new StretchViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera_eui);
		        vp_eui.apply(true);	  		        
		        stage_eui = new Stage(vp_eui);		       
		        energy_bar_position = new Vector2(camera_eui.position.x-56f, camera_eui.position.y*1.775f);
		        energy_bar_size = new Vector2(GAME_WORLD_WIDTH/4.75f,48f);
		        //energy_bar_position = new Vector2(camera_eui.viewportWidth/2*1.38f, camera_eui.viewportHeight/2*1.77f);
			    //   energy_bar_size = new Vector2(50f,50f);
		}
		public void Draw_Enemy_EnergyBar(double _life, Boolean _is_enemy_dead, Boolean _is_boss_talking)
		{			
			camera_eui.update();		
			is_boss_talking = _is_boss_talking;
			is_enemy_dead = _is_enemy_dead;
			life = _life;			
			
			//*****the code below i wrote is for making the energy bar move dynamically over the head of the boss enemy. 
			//*****if you change your mind later and wanna use just comment out. and modify the unproject codes...
//			if(e_movement.contains("right"))
//			 energy_bar_position_boss = new Vector2(enemy_position.x+3f/10f,enemy_position.y+12.5f/10f);
//			if(e_movement.contains("left"))		
//	        energy_bar_position_boss = new Vector2(enemy_position.x+4.2f/10f,enemy_position.y+12.5f/10f);
			
			 sb_energy_bar.setProjectionMatrix(camera_eui.combined);
			 sb_energy_bar.enableBlending();
			 sb_energy_bar.begin();		 
	
					 
			 if(!is_enemy_dead && !is_boss_talking)
			 {
			 if(life<=250)
			 	   {	 
				   sb_energy_bar.draw(tx_energies[10], energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
			 	   }
				  if(life<=225)
				  {	 
				   sb_energy_bar.draw(tx_energies[9],energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
				  }
				  if(life<=175)
				  {	 
				   sb_energy_bar.draw(tx_energies[8], energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
				  }
				  if(life<=150)
				  {	 
				   sb_energy_bar.draw(tx_energies[7], energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
				  }
				  if(life<=125)
				  {	 
				   sb_energy_bar.draw(tx_energies[6], energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
				  }
				  if(life<=100)
				  {	 
				   sb_energy_bar.draw(tx_energies[5], energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
				  }
				  if(life<=75)
				  {	 
				   sb_energy_bar.draw(tx_energies[4], energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
				  }
				  if(life<=50)
				  {	 
				   sb_energy_bar.draw(tx_energies[3], energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
				  }
				  if(life<=25)
				  {	 
				   sb_energy_bar.draw(tx_energies[2],energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y );
				  }
				  if(life<=10)
				  {	 
				   sb_energy_bar.draw(tx_energies[1],energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y );
				  }
				  if(life<=5 && life >=1.75f)
				  {	  
				   sb_energy_bar.draw(tx_energies[1], energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
				  }		
				  if(life<1.75f)
				  {
					  sb_energy_bar.draw(tx_energies[0], energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
				  }			  
			 }
			 if(is_enemy_dead)
			 {
				 sb_energy_bar.draw(tx_energies[0], energy_bar_position.x,energy_bar_position.y, energy_bar_size.x,energy_bar_size.y);
				 UI.label_energy.setText(Game_Elements.selected_character + ": ");
			 } 
			 sb_energy_bar.end();	
		}
}
