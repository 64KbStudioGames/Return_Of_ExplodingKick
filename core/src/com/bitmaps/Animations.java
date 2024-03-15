package com.bitmaps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.logic.Game_Elements;

public class Animations {
	//Beginning of the Animations
			//Character Main Animation Starter...	
			public Texture character_animation_spread_sheet;
			TextureRegion[] frames;		
			//Kick right animation
			public Animation<TextureRegion>  animation_right_kick;
			TextureRegion[] frames_right_kick;
			public float stateTime_right_kick;
			public TextureRegion currentFrame_right_kick;	
			//Alternative Right Kick			
			public Animation<TextureRegion>  alt_animation_right_kick;
			TextureRegion[] alt_frames_right_kick;
			public float alt_stateTime_right_kick;
			public TextureRegion alt_currentFrame_right_kick;	
			//Kick left animation
			public Animation<TextureRegion>  animation_left_kick;
			TextureRegion[] frames_left_kick;
			public float stateTime_left_kick;
			public TextureRegion currentFrame_left_kick;			
			//Alternative Left Kick
			public Animation<TextureRegion>  alt_animation_left_kick;
			TextureRegion[] alt_frames_left_kick;
			public float alt_stateTime_left_kick;
			public TextureRegion alt_currentFrame_left_kick;		
			//Punch Right Animation
			public Animation<TextureRegion>  animation_right_punch;
			TextureRegion[] frames_right_punch;
			public float stateTime_right_punch;
			public TextureRegion currentFrame_right_punch;		
			//Punch Left Animation
			public Animation<TextureRegion>  animation_left_punch;
			TextureRegion[] frames_left_punch;
			public float stateTime_left_punch;
			public TextureRegion currentFrame_left_punch;				
			//Walk Right Animation
			public Animation<TextureRegion>  animation_walk_right;
			TextureRegion[] frames_walk_right;
			public float stateTime_walk_right;
			public TextureRegion currentFrame_walk_right;			
			//Walk Left Animation
			public Animation<TextureRegion>  animation_walk_left;
			TextureRegion[] frames_walk_left;
			public float stateTime_walk_left;
			public TextureRegion currentFrame_walk_left;		 
			//Getting Impulse From right Animation
			public Animation<TextureRegion>  animation_getting_impulse_from_right;
			TextureRegion[] frames_getting_impulse_from_right;
			public float stateTime_getting_impulse_from_right;
			public TextureRegion currentFrame_getting_impulse_from_right;		 
			//Getting impulse from left Animation
			public Animation<TextureRegion>  animation_getting_impulse_from_left;
			TextureRegion[] frames_getting_impulse_from_left;
			public float stateTime_getting_impulse_from_left;
			public TextureRegion currentFrame_getting_impulse_from_left;		 
			//Right Jumping animation
			public Animation<TextureRegion>  animation_jumping_right;
			TextureRegion[] frames_jumping_right;
			public float stateTime_jumping_right;
			public TextureRegion currentFrame_jumping_right;		 
			//Left Jumping animation
			public Animation<TextureRegion> animation_jumping_left;
			TextureRegion[] frames_jumping_left;
			public float stateTime_jumping_left;
			public TextureRegion currentFrame_jumping_left;
			// Right Lose Animation
			public Animation<TextureRegion>  animation_losing_right;
			TextureRegion[] frames_losing_right;
			public float stateTime_losing_right;
			public TextureRegion currentFrame_losing_right;
			// Left  Lose Animation
			public Animation<TextureRegion>  animation_losing_left;
			TextureRegion[] frames_losing_left;
			public float stateTime_losing_left;
			public TextureRegion currentFrame_losing_left;
			//Win animation 
			Texture sprite_sheet_win;
			public Animation<TextureRegion>  animation_win;
			TextureRegion[] frames_win;
			public float stateTime_win;
			public TextureRegion currentFrame_win;			
			//Edge Stand Animation Right
			Texture sprite_sheet_edge_stand_right;
			public Animation<TextureRegion>  animation_edge_stand_right;
			TextureRegion[] frames_edge_stand_right;
			public float stateTime_edge_stand_right;
			public TextureRegion currentFrame_edge_stand_right;
			//Edge Stand Animation Left
			Texture sprite_sheet_edge_stand_left;
			public Animation<TextureRegion>  animation_edge_stand_left;
			TextureRegion[] frames_edge_stand_left;
			public float stateTime_edge_stand_left;
			public TextureRegion currentFrame_edge_stand_left;
			
					
		
			public void Animate_Character()
			{			    	 	
				 int row=16;
			     int col=4; 				    
					
			    	 	character_animation_spread_sheet= new Texture(
			    	 			Gdx.files.internal("Game_Art/Character Sheets/" + Game_Elements.selected_character + ".png"));
			    	 	TextureRegion[][] tmp = 
			    	 			TextureRegion.split(character_animation_spread_sheet, character_animation_spread_sheet.getWidth()/col, 
			    	 					character_animation_spread_sheet.getHeight()/row);
			    	 	frames= new TextureRegion[col*row];
							
			    	 	int index = 0;
			    	 	for(int i=0;i<row;i++)
			    	 	{
			    	 		for(int j=0;j<col;j++)
			    	 		{
			    	 			frames[index++] = tmp[i][j];
			    	 		}
			    	 	}    	
				} 			    
			    private void Animate_Right_Kick()
			    {		    
			    		frames_right_kick = new TextureRegion[4];      	
			    	
			    		frames_right_kick[0] = frames[12];			    	
			    		frames_right_kick[1] = frames[13];
			    		frames_right_kick[2] = frames[14];
			    		frames_right_kick[3] = frames[15];			    		     
			    		
			    		animation_right_kick = new Animation<TextureRegion>(0.108f,frames_right_kick);
			        
			    		animation_right_kick.setPlayMode(PlayMode.NORMAL);
			            stateTime_right_kick = 0f;
			            currentFrame_right_kick = frames_right_kick[0];
			    }
			    
			    private void alt_Animate_Right_Kick()
			    {
			    		alt_frames_right_kick = new TextureRegion[4];      	
			    	
			    		alt_frames_right_kick[0] = frames[48];			    	
			    		alt_frames_right_kick[1] = frames[49];
			    		alt_frames_right_kick[2] = frames[50];
			    		alt_frames_right_kick[3] = frames[51];		    		
			    		
			    			alt_animation_right_kick = new Animation<TextureRegion>(0.093f,alt_frames_right_kick);		
			    	
			        alt_animation_right_kick.setPlayMode(PlayMode.NORMAL);
			        alt_stateTime_right_kick = 0f;
			        alt_currentFrame_right_kick = alt_frames_right_kick[0];
			    }
			    
			    private void Animate_Left_Kick()
			    {
			
			    	
			    		frames_left_kick = new TextureRegion[4];      	
				    	
			    		frames_left_kick[0] = frames[0];
			    		frames_left_kick[1] = frames[1];
			    		frames_left_kick[2] = frames[2];
			    		frames_left_kick[3] = frames[3];			        
			    		
			    			animation_left_kick = new Animation<TextureRegion>(0.108f,frames_left_kick);		
			    		
			    	animation_left_kick.setPlayMode(PlayMode.NORMAL);
			        stateTime_left_kick = 0f;
			        currentFrame_left_kick = frames_left_kick[0];
			    }
			    
			    private void alt_Animate_Left_Kick()
			    {
			    		alt_frames_left_kick = new TextureRegion[4];      	
			    	
			    		alt_frames_left_kick[0] = frames[52];			    	
			    		alt_frames_left_kick[1] = frames[53];
			    		alt_frames_left_kick[2] = frames[54];
			    		alt_frames_left_kick[3] = frames[55];			    		
		
				    		alt_animation_left_kick = new Animation<TextureRegion>(0.093f,alt_frames_left_kick);		
			    	
			        alt_animation_left_kick.setPlayMode(PlayMode.NORMAL);
			        alt_stateTime_left_kick = 0f;
			        alt_currentFrame_left_kick = alt_frames_left_kick[0];
			    }
			    
			    private void Animate_Right_Combo()
			    {
			    	frames_right_punch = new TextureRegion[4];      	
			    	
			    	//right punch frames...
			    	frames_right_punch[0] = frames[40];
			        frames_right_punch[1] = frames[41];
			        frames_right_punch[2] = frames[42];
			        frames_right_punch[3] = frames[43];
			        
			        animation_right_punch = new Animation<TextureRegion>(0.1f,frames_right_punch);
			        animation_right_punch.setPlayMode(PlayMode.NORMAL);
			        stateTime_right_punch = 0f;
			        currentFrame_right_punch = frames_right_punch[0];
			    }
			    
			    private void Animate_Left_Combo()
			    {
			    	frames_left_punch = new TextureRegion[4];			    	
			    	//right punch frames...
			    	frames_left_punch[0] = frames[44];
			        frames_left_punch[1] = frames[45];
			        frames_left_punch[2] = frames[46];
			        frames_left_punch[3] = frames[47];
			        
			        animation_left_punch = new Animation<TextureRegion>(0.1f,frames_left_punch);
			        animation_left_punch.setPlayMode(PlayMode.NORMAL);
			        stateTime_left_punch = 0f;
			        currentFrame_left_punch = frames_left_punch[0];
			    }
			    
			    private void Animate_Walk_Right()
			    {
			    	frames_walk_right = new TextureRegion[4];      	
			    	
			    	frames_walk_right[0] = frames[8];
			        frames_walk_right[1] = frames[9];
			        frames_walk_right[2] = frames[10];
			        frames_walk_right[3] = frames[11];
			        
			        animation_walk_right = new Animation<TextureRegion>(0.132f,frames_walk_right);
			        animation_walk_right.setPlayMode(PlayMode.LOOP);
			        stateTime_walk_right = 0f;
			        currentFrame_walk_right = frames_walk_right[0];
			    }
			    
			    private void Animate_Walk_Left()
			    {
			    	frames_walk_left = new TextureRegion[4]; 
			    	
			    	frames_walk_left[0] = frames[4];
			        frames_walk_left[1] = frames[5];
			        frames_walk_left[2] = frames[6];
			        frames_walk_left[3] = frames[7];
			        
			        animation_walk_left = new Animation<TextureRegion>(0.132f,frames_walk_left);
			        animation_walk_left.setPlayMode(PlayMode.LOOP);
			        stateTime_walk_left = 0f;
			        currentFrame_walk_left = frames_walk_left[0];
			    }
			    
			    private void Animate_Getting_Impulse_From_Right()
			    {
			    	 frames_getting_impulse_from_left = new TextureRegion[4]; 
			     	
			     	 frames_getting_impulse_from_left[0] = frames[16];
			         frames_getting_impulse_from_left[1] = frames[17];
			         frames_getting_impulse_from_left[2] = frames[18];
			         frames_getting_impulse_from_left[3] = frames[19];
			         
			         animation_getting_impulse_from_left = new Animation<TextureRegion>(0.108f,frames_getting_impulse_from_left);
			         animation_getting_impulse_from_left.setPlayMode(PlayMode.NORMAL);
			         stateTime_getting_impulse_from_left = 0f;
			         currentFrame_getting_impulse_from_left = frames_getting_impulse_from_left[0];
			    }
			    
			    private void Animate_Getting_Impulse_From_Left()
			    {
			        frames_getting_impulse_from_right = new TextureRegion[4]; 
			    	
			    	frames_getting_impulse_from_right[0] = frames[20];
			        frames_getting_impulse_from_right[1] = frames[21];
			        frames_getting_impulse_from_right[2] = frames[22];
			        frames_getting_impulse_from_right[3] = frames[23];
			        
			        animation_getting_impulse_from_right = new Animation<TextureRegion>(0.108f,frames_getting_impulse_from_right);
			        animation_getting_impulse_from_right.setPlayMode(PlayMode.NORMAL);
			        stateTime_getting_impulse_from_right = 0f;
			        currentFrame_getting_impulse_from_right = frames_getting_impulse_from_left[0];
			    }
			   
			    private void Animate_Jumping_Right()
			    {   
			    		
			    	    	frames_jumping_right = new TextureRegion[4];      	
			    	    	
			    	    	frames_jumping_right[0] = frames[24];
			    	        frames_jumping_right[1] = frames[25];
			    	        frames_jumping_right[2] = frames[26];
			    	        frames_jumping_right[3] = frames[27];
			    	        
			    	        animation_jumping_right = new Animation<TextureRegion>(0.08125f,frames_jumping_right);
			    	        animation_jumping_right.setPlayMode(PlayMode.LOOP);
			    	        stateTime_jumping_right = 0f;
			    	        currentFrame_jumping_right = frames_jumping_right[0];
			   }
			    private void Animate_Jumping_Left()
			    {
			    	frames_jumping_left = new TextureRegion[4];
			    	
			    	frames_jumping_left[0] = frames[28];
			    	frames_jumping_left[1] = frames[29];
			    	frames_jumping_left[2] = frames[30];
			    	frames_jumping_left[3] = frames[31];
			    	
			    	animation_jumping_left = new Animation<TextureRegion>(0.08125f,frames_jumping_left);
			    	animation_jumping_left.setPlayMode(PlayMode.LOOP);
			    	stateTime_jumping_left = 0;
			    	currentFrame_jumping_left = frames_jumping_left[0];
			    }
			    
			    private void Animate_Losing_Right()
			    {
			    	frames_losing_right = new TextureRegion[4];      	
	    	    	
	    	    	frames_losing_right[0] = frames[32];
	    	        frames_losing_right[1] = frames[33];
	    	        frames_losing_right[2] = frames[34];
	    	        frames_losing_right[3] = frames[35];
	    	        
	    	        animation_losing_right = new Animation<TextureRegion>(1/4f,frames_losing_right);
	    	        animation_losing_right.setPlayMode(PlayMode.NORMAL);
	    	        stateTime_losing_right = 0f;
	    	        currentFrame_losing_right = frames_losing_right[0];
			    }
			    private void Animate_Losing_Left()
			    {
			    	frames_losing_left = new TextureRegion[4];      	
	    	    	
	    	    	frames_losing_left[0] = frames[36];
	    	        frames_losing_left[1] = frames[37];
	    	        frames_losing_left[2] = frames[38];
	    	        frames_losing_left[3] = frames[39];
	    	        
	    	        animation_losing_left = new Animation<TextureRegion>(1/4f,frames_losing_left);
	    	        animation_losing_left.setPlayMode(PlayMode.NORMAL);
	    	        stateTime_losing_left = 0f;
	    	        currentFrame_losing_left = frames_losing_left[0];
			    }		
			    
			    private void Animate_Edge_Stand_Right()
			    {
			    	frames_edge_stand_right = new TextureRegion[2];      	
	    	    
	    	    	frames_edge_stand_right[0] = frames[56];
	    	        frames_edge_stand_right[1] = frames[57];	   
	    	        
	    	        animation_edge_stand_right = new Animation<TextureRegion>(1/4f,frames_edge_stand_right);
	    	        animation_edge_stand_right.setPlayMode(PlayMode.LOOP);
	    	        stateTime_edge_stand_right = 0f;
	    	        currentFrame_edge_stand_right = frames_edge_stand_right[0];
			    }	
			    
			    private void Animate_Edge_Stand_Left()
			    {
			    	frames_edge_stand_left = new TextureRegion[2];      	
	    	    
	    	    	frames_edge_stand_left[0] = frames[58];
	    	        frames_edge_stand_left[1] = frames[59];	    	     
	    	        
	    	        animation_edge_stand_left = new Animation<TextureRegion>(1/4f,frames_edge_stand_left);
	    	        animation_edge_stand_left.setPlayMode(PlayMode.LOOP);
	    	        stateTime_edge_stand_left = 0f;
	    	        currentFrame_edge_stand_left = frames_edge_stand_left[0];
			    }	
			    
				public void Animate_Win()
				{			    	 	
					 int row=1;
				     int col=4; 				    
						
				     if(Game_Elements.selected_character.equals("characterSheet00"))
				    	 	sprite_sheet_win = new Texture(Gdx.files.internal("Game_Art/Character Sheets/player_win_sprite_sheets/character01_win.png"));
				     if(Game_Elements.selected_character.equals("characterSheet01"))
				    	 	sprite_sheet_win = new Texture(Gdx.files.internal("Game_Art/Character Sheets/player_win_sprite_sheets/character01_win.png"));
				     if(Game_Elements.selected_character.equals("characterSheet02"))
				    	 	sprite_sheet_win = new Texture(Gdx.files.internal("Game_Art/Character Sheets/player_win_sprite_sheets/character01_win.png"));
				    	 	
				    	 	TextureRegion[][] tmp = TextureRegion.split(sprite_sheet_win, sprite_sheet_win.getWidth()/col, sprite_sheet_win.getHeight()/row);
				    	 	frames_win = new TextureRegion[col*row];
								
				    	 	int index = 0;
				    	 	for(int i=0;i<row;i++)
				    	 	{
				    	 		for(int j=0;j<col;j++)
				    	 		{
				    	 			frames_win[index++] = tmp[i][j];
				    	 		}
				    	 	} 				    	 	
				    	     animation_win = new Animation<TextureRegion>(0.16f,frames_win);
						     animation_win.setPlayMode(PlayMode.LOOP);
						     stateTime_win = 0f;
						     currentFrame_win = frames_win[0]; 			    	 	
					} 	
			    public void Animate_All()
			    {
			    	Animate_Character();
			    	Animate_Right_Kick();
			    	alt_Animate_Right_Kick();
				    Animate_Left_Kick();	   
				    alt_Animate_Left_Kick();
				    Animate_Walk_Right();
				    Animate_Walk_Left();
				    Animate_Getting_Impulse_From_Right();
				    Animate_Getting_Impulse_From_Left();
				    Animate_Jumping_Right();
				    Animate_Jumping_Left();
				    Animate_Losing_Right();
				    Animate_Losing_Left();
				    Animate_Right_Combo();
				    Animate_Left_Combo();
				    Animate_Win();
				    Animate_Edge_Stand_Right();
				    Animate_Edge_Stand_Left();
			    }

}
