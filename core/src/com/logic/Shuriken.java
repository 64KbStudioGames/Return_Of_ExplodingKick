package com.logic;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Shuriken {

	//Set the shuriken
	
	//Main animation maker...
	public Texture shuriken_animation_spread_sheet;
	TextureRegion[] frames;
	
	//Rotate Animation...
	Animation<TextureRegion>  animation_shuriken_rotate;
	TextureRegion[] frames_shuriken_rotate;
	float stateTime_shuriken_rotate;
	TextureRegion currentFrame_shuriken_rotate;	
	
		public SpriteBatch sb_shuriken;
		public Vector2 shuriken_position;
		public Rectangle border_of_shuriken;
		public String shuriken_direction;
		//ShapeRenderer test_shape;
	    
	    public Shuriken()
	    {	    	
	       sb_shuriken = new SpriteBatch();		  
	       shuriken_position = new Vector2(100/100f,100/100f);		
	       border_of_shuriken = new Rectangle(0,0,0,0); 	 
	       shuriken_direction ="one_direction";
	       //test_shape = new ShapeRenderer();
	    }	
	    
	    public void animate_shuriken()
		{			    	 	
			 int row =1;
		     int col   =8; 				    
				
		    	 	shuriken_animation_spread_sheet= new Texture(Gdx.files.internal("Game_Art/shuriken_spread_sheet.png"));
		    	 	TextureRegion[][] tmp = TextureRegion.split(shuriken_animation_spread_sheet,shuriken_animation_spread_sheet.getWidth()/col,
		    	 			shuriken_animation_spread_sheet.getHeight()/row);
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
	    
	    public void Animate_Shuriken_Rotate()
	    {
	    	frames_shuriken_rotate = new TextureRegion[8];      	
	    	
	    	for(int i=0;i<frames.length;i++)
	    	{
	    		frames_shuriken_rotate[i] =frames[i];
	    	}
	    	
	        animation_shuriken_rotate = new Animation<TextureRegion>(0.025f,frames_shuriken_rotate);
	        animation_shuriken_rotate.setPlayMode(PlayMode.LOOP);
	        stateTime_shuriken_rotate = 0f;
	        currentFrame_shuriken_rotate = frames_shuriken_rotate[0];
	    }
	    
	    public void Draw_Shuriken_Animation(OrthographicCamera shuriken_camera)
	    {	    
	    	
	        currentFrame_shuriken_rotate = animation_shuriken_rotate.getKeyFrame(stateTime_shuriken_rotate+0.12f,false);
			
	        sb_shuriken.enableBlending();
			sb_shuriken.begin(); 
			sb_shuriken.setProjectionMatrix(shuriken_camera.combined);
			sb_shuriken.draw(currentFrame_shuriken_rotate, shuriken_position.x, shuriken_position.y+25/100f,82/100f,82/100f);	
			sb_shuriken.end();			
			
			border_of_shuriken.set(shuriken_position.x+20/100f,shuriken_position.y+40/100f,41.5f/100f,41f/100f);
			
//	    	test_shape.setAutoShapeType(true);
//	     	test_shape.begin();
//		   	test_shape.rect(border_of_shuriken.x,border_of_shuriken.y,border_of_shuriken.width,border_of_shuriken.height);	   	
//		   	test_shape.set(ShapeType.Filled);	 
//		   	test_shape.setColor(Color.PURPLE);
//		   	test_shape.setProjectionMatrix(shuriken_camera.combined);
//		    test_shape.end();  
	
			stateTime_shuriken_rotate +=Gdx.graphics.getDeltaTime();			
			
			if(animation_shuriken_rotate.isAnimationFinished(stateTime_shuriken_rotate))
			{						
				stateTime_shuriken_rotate = 0;			
			}
	    }
	    
}
