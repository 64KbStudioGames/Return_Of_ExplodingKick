package com.bitmaps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Background
{
		Sprite sp_background, sp_moon;	
		Texture tx_background, tx_moon;
		Vector2 pos_background, size_background, pos_satellite;	
		Vector3 real_screen_coordinate;
		
		Animation<TextureRegion> anime_cloud;
		Texture spread_sheet_cloud;	
		TextureRegion[] frames_cloud;
		TextureRegion currentFrame_cloud;
		float stateTime_cloud;		
		Sprite sp_dynamic_background;
		
		int bg_index;		
		public Background(int _level_index, OrthographicCamera _camera)
		{
			this.bg_index = _level_index;			
			if(bg_index == 1 || bg_index == 6 || bg_index == 7 || bg_index == 8 || bg_index == 9 || bg_index ==2)
				tx_background = new Texture(Gdx.files.internal("Level_maps/Level_Backgrounds/background_0" + bg_index +".png"));		
			if(bg_index == 3)
				tx_background = new Texture(Gdx.files.internal("Level_maps/Level_Backgrounds/background_04.png"));
			if(bg_index == 4)
				tx_background = new Texture(Gdx.files.internal("Level_maps/Level_Backgrounds/background_09.png"));
		
			if(bg_index!=5)
			{
				sp_background = new Sprite(tx_background);
				pos_background = new Vector2(_camera.position.x, _camera.position.y);
				size_background = new Vector2(_camera.viewportWidth*2, _camera.viewportHeight*2);
				sp_background.setPosition(pos_background.x, pos_background.y);
				sp_background.setSize(size_background.x,size_background.y);				
			}			
			
			real_screen_coordinate = new Vector3(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),0);
			_camera.unproject(real_screen_coordinate);
			//System.out.println(" rpx: " + real_screen_coordinate.x + " rpy: " + real_screen_coordinate.y);		
			tx_moon = new  Texture(Gdx.files.internal("Level_maps/Level_Backgrounds/moon.png"));		
			sp_moon = new Sprite(tx_moon);
			pos_satellite = new Vector2();
			sp_moon.setSize(200/100f, 200/100f);
		}		
		
		public void SetCloudAnimation()
		{
			if(bg_index !=4 && bg_index!=5)
			{
			sp_dynamic_background = new Sprite();
			
			 int row=8;
		     int col=16; 				    
				
		    	 	spread_sheet_cloud = new Texture(Gdx.files.internal("Level_maps/Level_Backgrounds/cloud_sheet.png"));
		    	 	TextureRegion[][] tmp = TextureRegion.split(spread_sheet_cloud, spread_sheet_cloud.getWidth()/col, spread_sheet_cloud.getHeight()/row);
		    	 	frames_cloud = new TextureRegion[col*row];
						
		    	 	int index = 0;
		    	 	for(int i=0;i<row;i++)
		    	 	{
		    	 		for(int j=0;j<col;j++)
		    	 		{
		    	 			frames_cloud[index++] = tmp[i][j];
		    	 		}
		    	 	}    	 	
		    	 	//System.out.println("number of cloud frames: " + frames_cloud.length);		    	 	
		    	 currentFrame_cloud = frames_cloud[0];
		    	 anime_cloud = new Animation<TextureRegion> (0.124f, frames_cloud);
		    	 anime_cloud.setPlayMode(Animation.PlayMode.LOOP);
		 
		    	
		    		 sp_dynamic_background = new Sprite(currentFrame_cloud);
		    		 sp_dynamic_background.setSize(size_background.x/1.272f, size_background.y/3.5f);
		    		 sp_dynamic_background.setPosition(sp_background.getX()+sp_dynamic_background.getWidth()/6.725f, 
		    				 sp_background.getY()+sp_background.getHeight()/1.85f);
			}		    	 
		}
		
		public void DrawBackground(SpriteBatch _sb, Vector2 _camera_position, char _player_direction)
		{	
			if(bg_index!=5)
			{
				pos_background.set(_camera_position.x, _camera_position.y);
				sp_background.setPosition(pos_background.x-size_background.x/2, pos_background.y-size_background.y/2);	
				if(bg_index == 3)
					pos_satellite.set(pos_background.x-125/100f, pos_background.y+150/100f);
				if(bg_index ==2)
					pos_satellite.set(pos_background.x-125/100f, pos_background.y+150/100f);
			}
			if(bg_index!=5)				
				sp_background.draw(_sb);			
			DrawClouds(_sb);
		}
		private void DrawClouds(SpriteBatch _batch)
		{
			if(bg_index ==1)
			{
				if(anime_cloud.isAnimationFinished(stateTime_cloud))
				{
					stateTime_cloud = 0f;
					currentFrame_cloud = frames_cloud[0];
				}			
				stateTime_cloud+= Gdx.graphics.getDeltaTime();
				currentFrame_cloud = anime_cloud.getKeyFrame(stateTime_cloud);		
				sp_dynamic_background.setRegion(currentFrame_cloud);			
				sp_dynamic_background.draw(_batch);			
				//fix position
				sp_dynamic_background.setPosition(sp_background.getX()+sp_dynamic_background.getWidth()/6.725f, 
    				 sp_background.getY()+sp_background.getHeight()/1.85f);
			}
			sp_moon.setPosition(pos_satellite.x, pos_satellite.y);			
			if(bg_index ==3)
				sp_moon.draw(_batch);
			//if(bg_index ==2)
				//sp_sun.draw(_batch);
		}
		public void DisposeAll()
		{
			tx_background.dispose(); 
			tx_moon.dispose();			
		}
		
}
